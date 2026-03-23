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
    private final String bucketName = "artist";

    public String uploadAvatar(MultipartFile file) {
        log.info("upload avatar {}", file.getOriginalFilename());
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
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
