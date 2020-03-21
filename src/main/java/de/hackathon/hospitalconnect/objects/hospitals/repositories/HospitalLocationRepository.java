package de.hackathon.hospitalconnect.objects.hospitals.repositories;

import de.hackathon.hospitalconnect.objects.hospitals.HospitalLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface HospitalLocationRepository extends JpaRepository<HospitalLocation, Long> {
}
