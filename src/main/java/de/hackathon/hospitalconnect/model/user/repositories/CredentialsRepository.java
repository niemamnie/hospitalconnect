package de.hackathon.hospitalconnect.model.user.repositories;

import de.hackathon.hospitalconnect.model.user.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CredentialsRepository extends JpaRepository<Credentials, Long> {
}
