package org.example.profile_service.controller;

import org.example.profile_service.DTO.ArtistDTO;
import org.example.profile_service.DTO.ArtistDisplayNameDTO;
import org.example.profile_service.DTO.ArtistRequest;
import org.example.profile_service.DTO.AvatarDTO;
import org.example.profile_service.service.ArtistService;
import org.example.profile_service.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {

    private final ArtistService artistService;


    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }
    @PostMapping("/create")
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistRequest artistRequest) {

        return new ResponseEntity<>(artistService.createArtist(
                artistRequest.getUserId(),
                artistRequest.getDisplayName(),
                artistRequest.getGenre(),
                artistRequest.getDescription(),
                artistRequest.getCity(),
                artistRequest.getReputationScore(),
                artistRequest.getContactPhone(),
                artistRequest.getContactEmail(),
                artistRequest.getProfilePictureUrl(),
                artistRequest.getVideoSamples(),
                artistRequest.getAudioSamples(),
                artistRequest.getSocialLinks()),
                HttpStatus.CREATED);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable UUID userId) {
        return new ResponseEntity<>(artistService.getArtistById(userId), HttpStatus.OK);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<ArtistDTO> editArtist(@PathVariable UUID userId,
                                                @RequestBody ArtistRequest artistRequest) {
        return new ResponseEntity<>(artistService.updateArtist(userId,
                artistRequest.getDisplayName(),
                artistRequest.getGenre(),
                artistRequest.getDescription(),
                artistRequest.getCity(),
                artistRequest.getReputationScore(),
                artistRequest.getContactPhone(),
                artistRequest.getContactEmail(),
                artistRequest.getProfilePictureUrl(),
                artistRequest.getVideoSamples(),
                artistRequest.getAudioSamples(),
                artistRequest.getSocialLinks()), HttpStatus.OK);
    }
    @GetMapping("/name/{userId}")
    public ResponseEntity<ArtistDisplayNameDTO> getDisplayNameById(@PathVariable UUID userId) {
        return new ResponseEntity<>(artistService.getDisplayName(userId), HttpStatus.OK);
    }

    @PostMapping("/avatar/{userId}")
    public ResponseEntity<AvatarDTO> uploadArtistAvatar(@RequestParam MultipartFile avatarFile,
                                                        @PathVariable UUID userId) {
        return new ResponseEntity<>(artistService.uploadAvatar(avatarFile, userId), HttpStatus.OK);
    }
    @GetMapping("/avatar/{userId}")
    public ResponseEntity<AvatarDTO> getArtistAvatar(@PathVariable UUID userId) {
        return new ResponseEntity<AvatarDTO>(artistService.getAvatar(userId), HttpStatus.OK);
    }

}
