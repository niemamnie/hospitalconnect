package de.hackathon.hospitalconnect.rest.search;

import de.hackathon.hospitalconnect.model.user.Coordinates;
import de.hackathon.hospitalconnect.model.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class SearchForHospitalsController {

    private final SearchForHospitalsService searchForHospitalsService;

    public SearchForHospitalsController(SearchForHospitalsService searchForHospitalsService) {
        this.searchForHospitalsService = searchForHospitalsService;
    }

    @PostMapping("/post/hospital/search/{radiusInKm}")
    public ResponseEntity<List<User>> searchForHospitalsInRadius(
            @PathVariable Double radiusInKm,
            @RequestBody Coordinates startCoordinates) {
        List<User> hospitalList = searchForHospitalsService.findHospitalsInRadius(radiusInKm, startCoordinates);
        return new ResponseEntity<>(hospitalList, HttpStatus.OK);
    }


    @GetMapping("/get/hospital/search")
    public ResponseEntity<List<User>> searchForHospitalsInRadiusDefault(HttpServletRequest request) {
        List<User> hospitalList = searchForHospitalsService.findHospitalsInRadiusDefault(request);
        return new ResponseEntity<>(hospitalList, HttpStatus.OK);
    }

}
