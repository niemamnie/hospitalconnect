package de.hackathon.hospitalconnect.objects.resources.repositories;

import de.hackathon.hospitalconnect.objects.resources.MaterialResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MaterialResourceRepository extends JpaRepository<MaterialResource, UUID> {
}
