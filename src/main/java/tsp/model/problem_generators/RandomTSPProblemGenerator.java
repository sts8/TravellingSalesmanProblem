package tsp.model.problem_generators;

import tsp.model.Location;

import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class RandomTSPProblemGenerator implements TSPProblemGenerator {

    private final Random random;
    private final int numberOfLocations;
    private final int maxX;
    private final int maxY;

    public RandomTSPProblemGenerator(Random random, int numberOfLocations, int maxX, int maxY) {

        if (maxX < 0 || maxY < 0) {
            throw new IllegalArgumentException("maxX and maxY must be positive!");
        }

        if (numberOfLocations > (maxX + 1) * (maxX + 1)) {
            throw new IllegalArgumentException("More locations than possible unique coordinates requested!");
        }

        this.random = Objects.requireNonNull(random);
        this.numberOfLocations = numberOfLocations;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Set<Location> generateProblem() {

        Set<Location> locations = new HashSet<>();

        for (int i = 0; i < numberOfLocations; i++) {
            locations.add(new Location("City" + i, random.nextInt(maxX), random.nextInt(maxY)));
        }

        return locations;
    }

}
