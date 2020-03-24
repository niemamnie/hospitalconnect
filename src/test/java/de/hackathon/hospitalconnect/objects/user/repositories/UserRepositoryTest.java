package de.hackathon.hospitalconnect.objects.user.repositories;

import de.hackathon.hospitalconnect.objects.enums.ResourceStatus;
import de.hackathon.hospitalconnect.objects.resource.HumanResource;
import de.hackathon.hospitalconnect.objects.user.User;
import org.junit.jupiter.api.Test;
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

    @Test
    @Transactional
    void saveUserWithHumanResource() {
        User user = new User();
        List<HumanResource> humanResources = new ArrayList<>();
        HumanResource resource = new HumanResource();

        resource.setStatus(ResourceStatus.RED);
        humanResources.add(resource);
        user.setHuman_resources(humanResources);

        User saved = userRepository.saveAndFlush(user);

        assertEquals(user.getHuman_resources().size(), saved.getHuman_resources().size());

        User loadedUser = userRepository.getOne(saved.getId());
        assertEquals(user.getHuman_resources().size(), loadedUser.getHuman_resources().size());
    }
}