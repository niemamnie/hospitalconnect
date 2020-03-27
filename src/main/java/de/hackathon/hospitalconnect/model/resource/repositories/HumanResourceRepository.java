package de.hackathon.hospitalconnect.model.resource.repositories;

import de.hackathon.hospitalconnect.model.resource.HumanResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface HumanResourceRepository extends JpaRepository<HumanResource, Long> {
    Optional<HumanResource> getById(Long id);
}
