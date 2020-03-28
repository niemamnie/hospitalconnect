package de.hackathon.hospitalconnect.rest.search;

import de.hackathon.hospitalconnect.distance.DistanceCalculator;
import de.hackathon.hospitalconnect.exceptions.intern.NotFoundException;
import de.hackathon.hospitalconnect.model.enums.ResourceStatus;
import de.hackathon.hospitalconnect.model.enums.UserType;
import de.hackathon.hospitalconnect.model.resource.HumanResourceType;
import de.hackathon.hospitalconnect.model.resource.MaterialResourceType;
import de.hackathon.hospitalconnect.model.user.Coordinates;
import de.hackathon.hospitalconnect.model.user.User;
import de.hackathon.hospitalconnect.model.user.repositories.UserRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchForHospitalsService {

    private final UserRepository userRepository;
    private final DistanceCalculator distanceCalculator;

    public SearchForHospitalsService(UserRepository userRepository, DistanceCalculator distanceCalculator) {
        this.userRepository = userRepository;
        this.distanceCalculator = distanceCalculator;
    }

    @Transactional
    public List<User> findHospitalsInRadius(Double radiusInKm, Coordinates startCoordinates) {
        List<User> users = userRepository.findAllByType(UserType.HOSPITAL);
        users.removeIf((user) ->
                distanceCalculator.calculateDistanceBetweenPointsInKm(
                        startCoordinates,
                        user.getAddress().getCoordinates())
                        <= radiusInKm
        );
        return users;
    }

    @Transactional
    public List<User> findHospitalsInRadiusDefault(HttpServletRequest request) {
        List<HumanResourceType> searchedHumanResources = new ArrayList<>();
        List<MaterialResourceType> searchedMaterialResources = new ArrayList<>();
        List<User> resultList = new ArrayList<>();
        String email = request.getUserPrincipal().getName();
        User loggedInUser = userRepository.findByCredentials_Email(email)
                .orElseThrow(() -> new NotFoundException("User"));
        List<User> userList = userRepository.findAllByType(UserType.HOSPITAL);
        loggedInUser.getMaterialResources().forEach(materialResource -> {
            if (materialResource.getStatus().equals(ResourceStatus.NEED)) {
                searchedMaterialResources.add(materialResource.getMaterialType());
            }
        });
        loggedInUser.getHumanResources().forEach(humanResource -> {
            if (humanResource.getStatus().equals(ResourceStatus.NEED)) {
                searchedHumanResources.add(humanResource.getHumanType());
            }
        });
        userList.forEach(user -> {
            if (user.getCredentials().getEmail().equals(email)) {
                user.getHumanResources().forEach(humanResource -> {
                    if (humanResource.getStatus().equals(ResourceStatus.RICH) || humanResource.getStatus().equals(ResourceStatus.NEUTRAL)) {
                        if (searchedHumanResources.contains(humanResource.getHumanType()) && !resultList.contains(user)) {
                            resultList.add(user);
                        }
                    }
                });
                user.getMaterialResources().forEach(materialResource -> {
                    if (materialResource.getStatus().equals(ResourceStatus.RICH) || materialResource.getStatus().equals(ResourceStatus.NEUTRAL)) {
                        if (searchedMaterialResources.contains(materialResource.getMaterialType()) && !resultList.contains(user)) {
                            resultList.add(user);
                        }
                    }
                });
            }
        });

        return resultList;
    }
}
