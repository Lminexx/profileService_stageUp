package org.example.profile_service.service;

import lombok.extern.slf4j.Slf4j;
import org.example.profile_service.DTO.OrganizationDTO;
import org.example.profile_service.entity.Organization;
import org.example.profile_service.mapping.OrganizationMapper;
import org.example.profile_service.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    public OrganizationService(OrganizationRepository organizationRepository,
                               OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    public OrganizationDTO createOrganization(UUID userId,
                                              String name,
                                              String description,
                                              String address,
                                              String city,
                                              String contact_phone,
                                              String contact_email,
                                              String website,
                                              String logo_url) {

        log.info("Create org {}", name);
        Organization organization = new Organization();
        organization.setUser_id(userId);
        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setCity(city);
        organization.setContact_phone(contact_phone);
        organization.setContact_email(contact_email);
        organization.setWebsite(website);
        organization.setLogo_url(logo_url);
        organizationRepository.save(organization);

        return organizationMapper.toOrganizationDTO(organization);

    }

    public OrganizationDTO getOrganization(UUID userId) {
        log.info("Get org {}", userId);
        Organization organization = organizationRepository.findById(userId).orElse(null);
        return organizationMapper.toOrganizationDTO(organization);
    }

    public OrganizationDTO updateOrganization(UUID userId,
                                              String name,
                                              String description,
                                              String address,
                                              String city,
                                              String contact_phone,
                                              String contact_email,
                                              String website,
                                              String logo_url){
        log.info("Update org {}", name);
        Organization organization = organizationRepository.findById(userId)
                .orElseThrow(()->{
            return new RuntimeException("Organization not found");
        });
        organization.setName(name);
        organization.setDescription(description);
        organization.setAddress(address);
        organization.setCity(city);
        organization.setContact_phone(contact_phone);
        organization.setContact_email(contact_email);
        organization.setWebsite(website);
        organization.setLogo_url(logo_url);
        organizationRepository.save(organization);
        return organizationMapper.toOrganizationDTO(organization);
    }

}
