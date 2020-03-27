package de.hackathon.hospitalconnect.model.user.repositories;

import de.hackathon.hospitalconnect.model.user.Coordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CoordinatesRepository extends JpaRepository<Coordinates, Long> {
    Optional<Coordinates> getById(Long id);
}
