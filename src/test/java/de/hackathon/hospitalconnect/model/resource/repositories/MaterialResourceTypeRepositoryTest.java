package de.hackathon.hospitalconnect.model.resource.repositories;

import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureDataJpa
class MaterialResourceTypeRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MaterialResourceRepository materialResourceRepository;

    @Autowired
    MaterialResourceTypeRepository materialResourceTypeRepository;

    @BeforeEach
    void setupData() {
        MaterialResourceType name = new MaterialResourceType("Schuhe");
        materialResourceTypeRepository.saveAndFlush(name);
    }

    void savetest() {
        //MaterialResource resource = new MaterialResource();
        MaterialResourceType materialResourceType = new MaterialResourceType();
        materialResourceType.setMaterialName("Schuhe");
        materialResourceTypeRepository.saveAndFlush(materialResourceType);
        Optional<MaterialResourceType> loaded = materialResourceTypeRepository.findByMaterialName("Schuhe");
        assertTrue(loaded.isPresent());
        MaterialResourceType newOne = new MaterialResourceType();
        newOne.setMaterialName("Schuhe");
        Assertions.assertThrows(Exception.class, () -> {
            materialResourceTypeRepository.saveAndFlush(newOne);
        });
        Optional<MaterialResourceType> loadedagain = materialResourceTypeRepository.findByMaterialName("Schuhe");
        assertTrue(loadedagain.isPresent());

        MaterialResource materialResource = new MaterialResource();
        //materialResource.setName(materialResourceName);
        materialResourceTypeRepository.saveAndFlush(materialResourceType);
        materialResourceRepository.saveAndFlush(materialResource);
        Optional<MaterialResourceType> lastloaded = materialResourceTypeRepository.findByMaterialName("Schuhe");
        assertTrue(lastloaded.isPresent());

    }

    void materialtest() throws Exception {
        MaterialResourceType anyName = materialResourceTypeRepository.findByMaterialName("Schuhe")
                .orElseThrow(Exception::new);
        Assertions.assertThrows(Exception.class, () -> {
            materialResourceTypeRepository.saveAndFlush(
                    new MaterialResourceType("Schuhe")
            );
        });
        MaterialResource materialResource = new MaterialResource();
        materialResource.setMaterialType(anyName);
        materialResourceRepository.saveAndFlush(materialResource);
    }


}