package org.example.profile_service.mapping;


import org.example.profile_service.DTO.ArtistDTO;
import org.example.profile_service.entity.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface ArtistMapper {

    @Mapping(target = "userId", source = "artist.id")
    @Mapping(target = "displayName", source = "artist.displayName")
    @Mapping(target = "description", source = "artist.description")
    @Mapping(target = "genre", source = "artist.genre")
    @Mapping(target = "city", source = "artist.city")
    @Mapping(target = "reputationScore", source = "artist.reputationScore")
    @Mapping(target = "contactPhone", source = "artist.contactPhone")
    @Mapping(target = "contactEmail", source = "artist.contactEmail")
    @Mapping(target = "profilePictureUrl", source = "artist.profilePictureUrl")
    @Mapping(target = "videoSamples", source = "artist.videoSamples")
    @Mapping(target = "audioSamples", source = "artist.audioSamples")
    @Mapping(target = "socialLinks", source = "artist.socialLinks")
    ArtistDTO toArtistDTO(Artist artist);

    @Mapping(target = "id", source = "dto.userId")
    @Mapping(target = "displayName", source = "dto.displayName")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "genre", source = "dto.genre")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "reputationScore", source = "dto.reputationScore")
    @Mapping(target = "contactPhone", source = "dto.contactPhone")
    @Mapping(target = "contactEmail", source = "dto.contactEmail")
    @Mapping(target = "profilePictureUrl", source = "dto.profilePictureUrl")
    @Mapping(target = "videoSamples", source = "dto.videoSamples")
    @Mapping(target = "audioSamples", source = "dto.audioSamples")
    @Mapping(target = "socialLinks", source = "dto.socialLinks")
    Artist toArtist(ArtistDTO dto);
}
