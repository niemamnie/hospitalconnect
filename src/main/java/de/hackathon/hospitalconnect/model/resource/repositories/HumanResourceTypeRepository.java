package de.hackathon.hospitalconnect.model.resource.repositories;

import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface HumanResourceTypeRepository extends JpaRepository<HumanResourceType, Long> {
    Optional<HumanResourceType> getById(Long id);

    Optional<HumanResourceType> findByHumanName(String name);
}
