package de.hackathon.hospitalconnect.model.resource.repositories;

import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface MaterialResourceRepository extends JpaRepository<MaterialResource, Long> {
    Optional<MaterialResource> getById(Long id);
}
