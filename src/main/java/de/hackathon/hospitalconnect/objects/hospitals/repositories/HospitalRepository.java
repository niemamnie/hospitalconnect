package de.hackathon.hospitalconnect.objects.hospitals.repositories;

import de.hackathon.hospitalconnect.objects.hospitals.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface HospitalRepository extends JpaRepository<Hospital, UUID> {
}
