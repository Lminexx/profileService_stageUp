package org.example.profile_service.entity;

import jakarta.persistence.*;
import org.example.profile_service.mapping.StringListConverter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Artist {

    @Id
    @Column(name = "user_id")
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "genre")
    private String genre;

    @Column(name = "city")
    private String city;

    @Column(name = "reputation_score")
    private BigDecimal reputationScore;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;

    @Convert(converter = StringListConverter.class)
    @Column(name = "video_samples", columnDefinition = "text[]")
    private List<String> videoSamples;

    @Convert(converter = StringListConverter.class)
    @Column(name = "audio_samples", columnDefinition = "text[]")
    private List<String> audioSamples;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "social_links", columnDefinition = "jsonb")
    private Map<String, String> socialLinks;

    public Artist(UUID userID, String displayName, String genre, String city) {
        this.id = userID;
        this.displayName = displayName;
        this.genre = genre;
        this.city = city;
    }
}