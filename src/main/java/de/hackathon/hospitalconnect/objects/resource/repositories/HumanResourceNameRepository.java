package de.hackathon.hospitalconnect.objects.resource.repositories;

import de.hackathon.hospitalconnect.objects.resource.HumanResourceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface HumanResourceNameRepository extends JpaRepository<HumanResourceName, Long> {
    Optional<HumanResourceName> getById(Long id);
}
