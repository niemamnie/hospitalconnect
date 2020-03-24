package de.hackathon.hospitalconnect.rest.coordinates;


import de.hackathon.hospitalconnect.objects.user.Coordinates;
import de.hackathon.hospitalconnect.objects.user.repositories.CoordinatesRepository;
import de.hackathon.hospitalconnect.rest.exceptions.InternException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CoordinatesService {


    private final CoordinatesRepository coordinatesRepository;

    public CoordinatesService(CoordinatesRepository coordinatesRepository) {
        this.coordinatesRepository = coordinatesRepository;
    }


    public Coordinates getCoordinates(Long id) {
        Optional<Coordinates> anyCoordinates = coordinatesRepository.getById(id);
        if (anyCoordinates.isPresent()) {
            return anyCoordinates.get();
        } else {
            throw new InternException("Unable to find coordinates in database", HttpStatus.NOT_FOUND);
        }
    }

    public void updateCoordinates(Long id, Coordinates coordinates) {
        try {
            if (!coordinates.getId().equals(id)) {
                coordinates.setId(id);
            }
            Optional<Coordinates> anyCoordinates = coordinatesRepository.getById(id);
            if (anyCoordinates.isPresent()) {
                coordinates.setAddress(anyCoordinates.get().getAddress());
            }
            coordinatesRepository.saveAndFlush(coordinates);
        } catch (RuntimeException exception) {
            throw new InternException("Unable to find coordinates in database", HttpStatus.NOT_FOUND);
        }
    }
}
