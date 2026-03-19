package org.example.profile_service.repository;

import org.example.profile_service.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ArtistRepository extends JpaRepository<Artist, UUID> {


}
