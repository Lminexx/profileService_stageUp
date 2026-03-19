package org.example.profile_service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.profile_service.DTO.ArtistDTO;
import org.example.profile_service.DTO.ArtistDisplayNameDTO;
import org.example.profile_service.entity.Artist;
import org.example.profile_service.mapping.ArtistMapper;
import org.example.profile_service.repository.ArtistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@Slf4j
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;


    public ArtistService(ArtistRepository artistRepository,
                         ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }


    public ArtistDTO createArtist(UUID userID,
                                  String displayName,
                                  String genre,
                                  String description,
                                  String city,
                                  BigDecimal reputationScore,
                                  String contactPhone,
                                  String contactEmail,
                                  String profilePictureUrl,
                                  List<String> videoSamples,
                                  List<String> audioSamples,
                                  Map<String, String> socialLinks) {
        log.info("Creating artist {}", displayName);
        Artist artist = new Artist();
        artist.setId(userID);
        artist.setDisplayName(displayName);
        artist.setGenre(genre);
        artist.setDescription(description);
        artist.setCity(city);
        artist.setReputationScore(reputationScore);
        artist.setContactPhone(contactPhone);
        artist.setContactEmail(contactEmail);
        artist.setProfilePictureUrl(profilePictureUrl);
        artist.setVideoSamples(videoSamples);
        artist.setAudioSamples(audioSamples);
        artist.setSocialLinks(socialLinks);

        artistRepository.save(artist);
        return artistMapper.toArtistDTO(artist);
    }
    @Transactional(readOnly = true)
    public ArtistDTO getArtistById(UUID userID) {
        log.info("get artist {}", userID);
        Artist artist = artistRepository.findById(userID).orElse(null);
        return artistMapper.toArtistDTO(artist);
    }

    @Transactional
    public ArtistDTO updateArtist(UUID userID,
                                  String displayName,
                                  String genre,
                                  String description,
                                  String city,
                                  BigDecimal reputationScore,
                                  String contactPhone,
                                  String contactEmail,
                                  String profilePictureUrl,
                                  List<String> videoSamples,
                                  List<String> audioSamples,
                                  Map<String, String> socialLinks){
        log.info("Updating artist {}", displayName);
        Artist artist = artistRepository.findById(userID).orElse(null);
        artist.setDisplayName(displayName);
        artist.setGenre(genre);
        artist.setDescription(description);
        artist.setCity(city);
        artist.setReputationScore(reputationScore);
        artist.setContactPhone(contactPhone);
        artist.setContactEmail(contactEmail);
        artist.setProfilePictureUrl(profilePictureUrl);
        artist.setVideoSamples(videoSamples);
        artist.setAudioSamples(audioSamples);
        artist.setSocialLinks(socialLinks);
        artistRepository.save(artist);
        return artistMapper.toArtistDTO(artist);
    }

    public ArtistDisplayNameDTO getDisplayName(UUID userID) {
        log.info("get displayName {}", userID);
        Artist artist = artistRepository.findById(userID).orElseThrow(()-> new RuntimeException("Артиста с таким айди не существует"));
        ArtistDisplayNameDTO artistDisplayNameDTO = new ArtistDisplayNameDTO();
        artistDisplayNameDTO.setArtistName(artist.getDisplayName());
        return artistDisplayNameDTO;
    }


}
