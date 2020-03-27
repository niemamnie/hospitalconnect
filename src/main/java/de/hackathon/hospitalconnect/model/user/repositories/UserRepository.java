package de.hackathon.hospitalconnect.model.user.repositories;

import de.hackathon.hospitalconnect.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getByCredentials_EmailAndCredentials_Password(String email, String password);

    Optional<User> getById(Long id);
}
