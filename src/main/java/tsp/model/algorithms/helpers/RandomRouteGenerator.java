package tsp.model.algorithms.helpers;

import tsp.model.Location;
import tsp.model.Route;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class RandomRouteGenerator {

    private RandomRouteGenerator() {
    }

    public static Route generate(Random random, Set<Location> locations) {

        LinkedList<Location> locationsList = new LinkedList<>(Objects.requireNonNull(locations));
        List<Location> randomRoute = new ArrayList<>();

        while (!locationsList.isEmpty()) {
            int randomLocation = random.nextInt(locationsList.size());
            randomRoute.add(locationsList.remove(randomLocation));
        }

        return new Route(randomRoute);
    }

}
