package de.hackathon.hospitalconnect.distance;

import de.hackathon.hospitalconnect.model.user.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class HarversineDistanceCalculator implements DistanceCalculator {
    private final Double earthRadiusInMeters = 6378137.0;

    private Double degreesToRadians(Double degrees) {
        return degrees * Math.PI / 180;
    }

    public Double getDistanceBetweenPointsInMeters(Coordinates startPoint, Coordinates targetPoint) {
        Double radiasLatDiff = degreesToRadians(startPoint.getLat() - targetPoint.getLat());
        Double radiasLngDiff = degreesToRadians(targetPoint.getLng() - startPoint.getLng());
        Double numberA = Math.sin(radiasLatDiff / 2)
                * Math.sin(radiasLatDiff / 2)
                + Math.cos(degreesToRadians(startPoint.getLat()))
                * Math.cos(degreesToRadians(startPoint.getLat()))
                * Math.sin(radiasLngDiff / 2)
                * Math.sin(radiasLngDiff / 2);
        Double atan2 = 2 * Math.atan2(Math.sqrt(numberA), Math.sqrt(1 - numberA));
        Double distanceInMeters = earthRadiusInMeters * atan2;
        return distanceInMeters;
    }


    @Override
    public Double calculateDistanceBetweenPointsInKm(Coordinates startPoint, Coordinates targetPoint) {
        return getDistanceBetweenPointsInMeters(startPoint, targetPoint) * 0.001;
    }
}
