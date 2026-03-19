package org.example.profile_service.mapping;


import org.example.profile_service.DTO.OrganizationDTO;
import org.example.profile_service.entity.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {
    @Mapping(target = "user_id", source = "organization.user_id")
    @Mapping(target = "name", source = "organization.name")
    @Mapping(target = "description", source = "organization.description")
    @Mapping(target = "address", source = "organization.address")
    @Mapping(target = "city", source = "organization.city")
    @Mapping(target = "contact_phone", source = "organization.contact_phone")
    @Mapping(target = "contact_email", source = "organization.contact_email")
    @Mapping(target = "website", source = "organization.website")
    @Mapping(target = "logo_url", source = "organization.logo_url")
    OrganizationDTO toOrganizationDTO(Organization organization);

    @Mapping(target = "user_id", source = "dto.user_id")
    @Mapping(target = "name", source = "dto.name")
    @Mapping(target = "description", source = "dto.description")
    @Mapping(target = "address", source = "dto.address")
    @Mapping(target = "city", source = "dto.city")
    @Mapping(target = "contact_phone", source = "dto.contact_phone")
    @Mapping(target = "contact_email", source = "dto.contact_email")
    @Mapping(target = "website", source = "dto.website")
    @Mapping(target = "logo_url", source = "dto.logo_url")
    Organization toOrganization(OrganizationDTO dto);
}

