package org.example.profile_service.DTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistRequest {

    private UUID userId;
    private String displayName;
    private String genre;
    private String city;

    private String description;
    private String contactPhone;
    private String contactEmail;
    private String profilePictureUrl;
    private BigDecimal reputationScore;
    private List<String> videoSamples;
    private List<String> audioSamples;
    private Map<String, String> socialLinks;
}
