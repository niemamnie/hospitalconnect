package de.hackathon.hospitalconnect.objects.repositories;

import de.hackathon.hospitalconnect.objects.User;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> getByEmailAndPassword(String email, String password);
}
