package de.hackathon.hospitalconnect.objects;

import de.hackathon.hospitalconnect.objects.enums.NeedStage;
import de.hackathon.hospitalconnect.objects.hospitals.Hospital;
import de.hackathon.hospitalconnect.objects.hospitals.HospitalLocation;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.HospitalLocationRepository;
import de.hackathon.hospitalconnect.objects.hospitals.repositories.HospitalRepository;
import de.hackathon.hospitalconnect.objects.resources.MaterialResource;
import de.hackathon.hospitalconnect.objects.resources.PersonalResource;
import de.hackathon.hospitalconnect.objects.resources.repositories.MaterialResourceRepository;
import de.hackathon.hospitalconnect.objects.resources.repositories.PersonalResourceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class repositoriesTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Autowired
    HospitalLocationRepository hospitalLocationRepository;

    @Autowired
    MaterialResourceRepository materialResourceRepository;

    @Autowired
    PersonalResourceRepository personalResourceRepository;


    @Test
    public void jpaTest() {
        Hospital hospital = new Hospital();
        HospitalLocation location = new HospitalLocation();
        PersonalResource personalResource = new PersonalResource();
        MaterialResource materialResource = new MaterialResource();

        location.setLat(12.22);
        location.setLng(14.22);

        personalResource.setNeedStage(NeedStage.GREEN);

        materialResource.setNeedStage(NeedStage.RED);

        hospital.setName("test hospital");
        hospital.setLocation(location);
        hospital.setMaterialResource(materialResource);
        hospital.setPersonalResource(personalResource);

        personalResourceRepository.save(personalResource);
        materialResourceRepository.save(materialResource);
        hospitalLocationRepository.save(location);
        Hospital savedHospital = hospitalRepository.saveAndFlush(hospital);

        Assertions.assertEquals(hospital, savedHospital);
    }
}
