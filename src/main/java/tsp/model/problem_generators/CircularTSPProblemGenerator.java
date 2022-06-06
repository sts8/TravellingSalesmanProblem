package tsp.model.problem_generators;

import tsp.model.Location;

import java.util.HashSet;
import java.util.Set;

public class CircularTSPProblemGenerator implements TSPProblemGenerator {

    private final int numberOfLocations;
    private final int radius;

    public CircularTSPProblemGenerator(int numberOfLocations, int radius) {
        this.numberOfLocations = numberOfLocations;
        this.radius = radius;
    }

    public Set<Location> generateProblem() {

        Set<Location> locations = new HashSet<>();

        for (int i = 0; i < numberOfLocations; i++) {

            int x = 250 + (int) Math.round(radius * Math.sin(((double) i / numberOfLocations) * 2 * Math.PI));
            int y = 250 + (int) Math.round(radius * Math.cos(((double) i / numberOfLocations) * 2 * Math.PI));

            locations.add(new Location("City" + i, x, y));
        }

        return locations;
    }

}
