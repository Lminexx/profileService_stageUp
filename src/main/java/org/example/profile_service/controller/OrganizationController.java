package org.example.profile_service.controller;

import org.example.profile_service.DTO.OrganizationDTO;
import org.example.profile_service.DTO.OrganizationRequest;
import org.example.profile_service.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }
    @PostMapping("/create")
    public ResponseEntity<OrganizationDTO> createOrganization(@RequestBody OrganizationRequest organizationRequest) {
        return new ResponseEntity<>(
                organizationService.createOrganization(
                        organizationRequest.getUser_id(),
                        organizationRequest.getName(),
                        organizationRequest.getDescription(),
                        organizationRequest.getAddress(),
                        organizationRequest.getCity(),
                        organizationRequest.getContact_phone(),
                        organizationRequest.getContact_email(),
                        organizationRequest.getWebsite(),
                        organizationRequest.getLogo_url()
                ),
                HttpStatus.CREATED);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable UUID userId) {
        return new ResponseEntity<>(organizationService.getOrganization(userId), HttpStatus.OK);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(
            @PathVariable UUID userId,
            @RequestBody OrganizationRequest organizationRequest) {
        return new ResponseEntity<>(organizationService.updateOrganization(
                userId,
                organizationRequest.getName(),
                organizationRequest.getDescription(),
                organizationRequest.getAddress(),
                organizationRequest.getCity(),
                organizationRequest.getContact_phone(),
                organizationRequest.getContact_email(),
                organizationRequest.getWebsite(),
                organizationRequest.getLogo_url()
        ), HttpStatus.OK);
    }
}
