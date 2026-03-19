package org.example.profile_service.repository;

import org.example.profile_service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {


}
