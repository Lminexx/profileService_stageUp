package org.example.profile_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Organization {
    @Id
    @Column(name = "user_id")
    private UUID user_id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "contact_phone")
    private String contact_phone;
    @Column(name = "contact_email")
    private String contact_email;
    @Column(name = "website")
    private String website;
    @Column(name = "logo_url")
    private String logo_url;
}
