package de.hackathon.hospitalconnect.objects.user.repositories;

import de.hackathon.hospitalconnect.objects.user.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> getById(Long id);
}
