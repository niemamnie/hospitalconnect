package de.hackathon.hospitalconnect.objects.user.repositories;

import de.hackathon.hospitalconnect.objects.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> getById(Long id);
}
