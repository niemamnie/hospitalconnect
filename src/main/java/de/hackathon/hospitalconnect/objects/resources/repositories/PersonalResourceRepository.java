package de.hackathon.hospitalconnect.objects.resources.repositories;

import de.hackathon.hospitalconnect.objects.resources.PersonalResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface PersonalResourceRepository extends JpaRepository<PersonalResource, UUID> {
}
