package de.hackathon.hospitalconnect.model.user.repositories;

import de.hackathon.hospitalconnect.model.enums.ResourceStatus;
import de.hackathon.hospitalconnect.model.resource.HumanResource;
import de.hackathon.hospitalconnect.model.user.User;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Transactional
    void saveUserWithHumanResource() {
        User user = new User();
        List<HumanResource> humanResources = new ArrayList<>();
        HumanResource resource = new HumanResource();

        resource.setStatus(ResourceStatus.RED);
        humanResources.add(resource);
        user.setHumanResources(humanResources);

        User saved = userRepository.saveAndFlush(user);

        assertEquals(user.getMaterialResources().size(), saved.getMaterialResources().size());

        User loadedUser = userRepository.getOne(saved.getId());
        assertEquals(user.getHumanResources().size(), loadedUser.getHumanResources().size());
    }
}