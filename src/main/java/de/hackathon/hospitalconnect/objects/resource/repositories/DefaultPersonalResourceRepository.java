package de.hackathon.hospitalconnect.objects.resource.repositories;

import de.hackathon.hospitalconnect.objects.resource.PersonalResourceName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DefaultPersonalResourceRepository extends JpaRepository<PersonalResourceName, Long> {
}
