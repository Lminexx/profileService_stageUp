package org.example.profile_service.service;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.example.profile_service.DTO.AvatarDTO;
import org.example.profile_service.DTO.OrganizationDTO;
import org.example.profile_service.entity.Artist;
import org.example.profile_service.entity.Organization;
import org.example.profile_service.mapping.OrganizationMapper;
import org.example.profile_service.repository.OrganizationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;
    private final AvatarService avatarService;

    public OrganizationService(OrganizationRepository organizationRepository,
                               OrganizationMapper organizationMapper, AvatarService avatarService) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.avatarService = avatarService;
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


    @Transactional
    public AvatarDTO uploadAvatar(MultipartFile file, UUID organizerId) {
        String avatar = avatarService.uploadAvatar(file, "Organizer");
        Organization organizer = organizationRepository.findById(organizerId).orElseThrow(()->
                new RuntimeException("Организатора с таким айди не существует"));

        organizer.setLogo_url(avatar);
        organizationRepository.save(organizer);
        AvatarDTO avatarDTO = new AvatarDTO();
        avatarDTO.setAvatarUrl(avatar);
        return avatarDTO;
    }

    public AvatarDTO getAvatar(UUID organizerId) {
        log.info("get avatar {}", organizerId);
        AvatarDTO avatarDTO = new AvatarDTO();
        Organization organization = organizationRepository.findById(organizerId).orElseThrow(()->
                new RuntimeException("Артиста с таким айди не существует"));
        avatarDTO.setAvatarUrl(organization.getLogo_url());
        return avatarDTO;
    }

}
