package de.hackathon.hospitalconnect.distance;

import de.hackathon.hospitalconnect.model.user.Coordinates;

public interface DistanceCalculator {

    Double calculateDistanceBetweenPointsInKm(Coordinates startPoint, Coordinates targetPoint);
}
