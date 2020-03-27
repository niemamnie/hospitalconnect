package de.hackathon.hospitalconnect.model.resource.repositories;

import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface MaterialResourceTypeRepository extends JpaRepository<MaterialResourceType, Long> {
    Optional<MaterialResourceType> findByMaterialName(String name);
}
