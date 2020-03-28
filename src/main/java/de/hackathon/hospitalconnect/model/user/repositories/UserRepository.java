package de.hackathon.hospitalconnect.model.user.repositories;

import de.hackathon.hospitalconnect.model.enums.UserType;
import de.hackathon.hospitalconnect.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByType(UserType userType);

    Optional<User> findByCredentials_Email(String email);

    Optional<User> getById(Long id);

}
