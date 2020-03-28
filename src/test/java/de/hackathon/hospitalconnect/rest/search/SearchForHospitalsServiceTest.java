package de.hackathon.hospitalconnect.rest.search;

import de.hackathon.hospitalconnect.model.enums.ResourceStatus;
import de.hackathon.hospitalconnect.model.enums.UserType;
import de.hackathon.hospitalconnect.model.resource.HumanResource;
import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import de.hackathon.hospitalconnect.model.resource.MaterialResource;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import de.hackathon.hospitalconnect.model.resource.repositories.HumanResourceTypeRepository;
import de.hackathon.hospitalconnect.model.resource.repositories.MaterialResourceTypeRepository;
import de.hackathon.hospitalconnect.model.user.Address;
import de.hackathon.hospitalconnect.model.user.Coordinates;
import de.hackathon.hospitalconnect.model.user.Credentials;
import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureDataJpa
class SearchForHospitalsServiceTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    HumanResourceTypeRepository humanTypeRepository;
    @Autowired
    MaterialResourceTypeRepository materialTypeRepository;

    @Autowired
    SearchForHospitalsService searchForHospitalsService;

    @MockBean
    HttpServletRequest httpServletRequest;
    @Mock
    Principal principal;

    static double lngFind = 10.0;
    static double latFind = 10.0;
    static List<HumanResourceType> humanResourceTypes = new ArrayList<>();
    static List<MaterialResourceType> materialResourceTypes = new ArrayList<>();

    @BeforeEach
    void order() {
        initResources();
        init();
        init2();
        initme();
    }

    void initResources() {
        HumanResourceType type = new HumanResourceType();
        type.setHumanName("Artzt");
        humanTypeRepository.saveAndFlush(type);
        humanResourceTypes.add(type);

        MaterialResourceType material = new MaterialResourceType();
        material.setMaterialName("Schuhe");
        materialTypeRepository.saveAndFlush(material);
        materialResourceTypes.add(material);

        MaterialResourceType materialType = new MaterialResourceType();
        materialType.setMaterialName("Maske");
        materialTypeRepository.saveAndFlush(materialType);
        materialResourceTypes.add(materialType);
    }

    void init() {
        User user = new User();
        user.setType(UserType.HOSPITAL);
        user.setName("name");
        user.setTel("tel");
        Address address = new Address();
        Coordinates coordinates = new Coordinates();
        coordinates.setLng(lngFind);
        coordinates.setLat(latFind);
        address.setCoordinates(coordinates);

        Credentials credentials = new Credentials();
        credentials.setPassword("pass");
        credentials.setEmail("email@email.de");
        user.setCredentials(credentials);

        List<MaterialResource> materialResources = new ArrayList<>();
        materialResources.add(createMaterialResource(ResourceStatus.NEED, 0));
        materialResources.add(createMaterialResource(ResourceStatus.RICH, 1));
        user.setMaterialResources(materialResources);

        List<HumanResource> humanResources = new ArrayList<>();
        humanResources.add(createHumanResource(ResourceStatus.NEED));
        user.setHumanResources(humanResources);

        userRepository.saveAndFlush(user);
    }

    void init2() {
        User user = new User();
        user.setType(UserType.HOSPITAL);
        user.setName("name");
        user.setTel("tel");
        Address address = new Address();
        Coordinates coordinates = new Coordinates();
        coordinates.setLng(lngFind);
        coordinates.setLat(latFind);
        address.setCoordinates(coordinates);

        Credentials credentials = new Credentials();
        credentials.setPassword("pass");
        credentials.setEmail("re@re.re");
        user.setCredentials(credentials);

        List<MaterialResource> materialResources = new ArrayList<>();
        materialResources.add(createMaterialResource(ResourceStatus.RICH, 0));
        materialResources.add(createMaterialResource(ResourceStatus.NEED, 1));
        user.setMaterialResources(materialResources);

        List<HumanResource> humanResources = new ArrayList<>();
        user.setHumanResources(humanResources);
        humanResources.add(createHumanResource(ResourceStatus.NEUTRAL));
        user.setHumanResources(humanResources);
        userRepository.saveAndFlush(user);
    }

    void initme() {
        User user = new User();
        user.setType(UserType.HOSPITAL);
        user.setName("name");
        user.setTel("tel");
        Address address = new Address();
        Coordinates coordinates = new Coordinates();
        coordinates.setLng(lngFind);
        coordinates.setLat(latFind);
        address.setCoordinates(coordinates);

        Credentials credentials = new Credentials();
        credentials.setPassword("pass");
        credentials.setEmail("me@me.me");
        user.setCredentials(credentials);

        List<MaterialResource> materialResources = new ArrayList<>();
        materialResources.add(createMaterialResource(ResourceStatus.NEED, 1));
        user.setMaterialResources(materialResources);

        List<HumanResource> humanResources = new ArrayList<>();
        humanResources.add(createHumanResource(ResourceStatus.NEED));
        user.setHumanResources(humanResources);
        userRepository.saveAndFlush(user);
    }


    @Test
    void test() {
        Mockito.doReturn(principal).when(httpServletRequest).getUserPrincipal();
        Mockito.doReturn("me@me.me").when(principal).getName();
        List<User> result = searchForHospitalsService.findHospitalsInRadiusDefault(httpServletRequest);
        Assertions.assertEquals(2, result.size());
    }


    static MaterialResource createMaterialResource(ResourceStatus status, int listindex) {
        MaterialResource materialResource = new MaterialResource();
        materialResource.setMaterialType(materialResourceTypes.get(listindex));
        materialResource.setStatus(status);
        return materialResource;
    }

    static HumanResource createHumanResource(ResourceStatus status) {
        HumanResource humanResource = new HumanResource();
        humanResource.setStatus(status);
        humanResource.setHumanType(humanResourceTypes.get(0));
        return humanResource;
    }
}