package de.hackathon.hospitalconnect.objects.resource.repositories;

import de.hackathon.hospitalconnect.objects.resource.MaterialResourceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DefaultMaterialResourceRepository extends JpaRepository<MaterialResourceName, Long> {
}
