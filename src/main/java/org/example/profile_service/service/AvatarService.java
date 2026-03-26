package org.example.profile_service.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarService {

    private final MinioClient minioClient;

    public String uploadAvatar(MultipartFile file, String ArOrOrg) {
        log.info("upload avatar {}", file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String bucketName = "";
        if(ArOrOrg.equals("Artist")){
            bucketName = "artist";
        }else if(ArOrOrg.equals("Organizer")){
            bucketName = "organizer";
        }

        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());

            return fileName;
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке в MinIO", e);
        }
    }
}
