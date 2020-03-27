package de.hackathon.hospitalconnect.rest.user;

import de.hackathon.hospitalconnect.model.resource.HumanResource;
import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import de.hackathon.hospitalconnect.model.resource.repositories.HumanResourceTypeRepository;
import de.hackathon.hospitalconnect.model.resource.repositories.MaterialResourceTypeRepository;
import de.hackathon.hospitalconnect.model.user.Role;
import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import de.hackathon.hospitalconnect.service.CopyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    private final UserRepository userRepository;
    private final HumanResourceTypeRepository humanResourceTypeRepository;
    private final MaterialResourceTypeRepository materialResourceTypeRepository;
    private final CopyService copyService;
    private boolean isFirstUser = false;

    public UserService(UserRepository userRepository, HumanResourceTypeRepository humanResourceTypeRepository, MaterialResourceTypeRepository materialResourceTypeRepository, CopyService copyService) {
        this.userRepository = userRepository;
        isFirstUser = userRepository.count() <= 0;
        this.humanResourceTypeRepository = humanResourceTypeRepository;
        this.materialResourceTypeRepository = materialResourceTypeRepository;
        this.copyService = copyService;
    }

    public Long saveNewUser(User user) {
        setHumanResourceNames(user);
        setMaterialResourceNames(user);
        if (isFirstUser) {
            user.addRole(Role.ADMIN);
        }
        user.addRole(Role.USER);
        User saved = userRepository.saveAndFlush(user);
        return saved.getId();
    }

    public User getUser(Long id) {
        try {
            Optional<User> anyHospital = userRepository.findById(id);
            if (anyHospital.isPresent()) {
                return anyHospital.get();
            }
        } catch (EntityNotFoundException e) {
            throw new InternException("Could not Found", HttpStatus.NOT_FOUND);
        }
        throw new InternException("Could not Found", HttpStatus.NOT_FOUND);
    }

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();
        for (User userItem : users) {
            userItem.getHumanResources().size();
            userItem.getMaterialResources().size();
        }
        return users;
    }


    private void setHumanResourceNames(User user) {
        List<HumanResource> humanResources = user.getHumanResources();
        List<HumanResource> humanResourceList = new ArrayList<>();
        for (HumanResource humanResource : humanResources) {
            Optional<HumanResourceType> anyHumanResourceType =
                    humanResourceTypeRepository.findByHumanName(humanResource.getHumanType().getHumanName());
            if (anyHumanResourceType.isPresent()) {
                humanResource.setHumanType(anyHumanResourceType.get());
                humanResourceList.add(humanResource);
            }
        }
        user.setHumanResources(humanResourceList);
    }

    private void setMaterialResourceNames(User user) {
        List<MaterialResource> materialResources = user.getMaterialResources();
        List<MaterialResource> materialResourceList = new ArrayList<>();
        for (MaterialResource materialResource : materialResources) {
            Optional<MaterialResourceType> anyMaterialResourceName =
                    materialResourceTypeRepository.findByMaterialName(materialResource.getMaterialType().getMaterialName());
            if (anyMaterialResourceName.isPresent()) {
                materialResource.setMaterialType(anyMaterialResourceName.get());
                materialResourceList.add(materialResource);
            }
        }
        user.setMaterialResources(materialResourceList);
    }

    public void patchUser(Long id, User userWithChanges) {
        User savedUser = userRepository.getById(id)
                .orElseThrow(() -> new InternException(
                        "User not Found", HttpStatus.NOT_FOUND));
        copyService.map(userWithChanges, savedUser);
    }
}
