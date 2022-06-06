package tsp.model.algorithms.helpers;

import org.junit.jupiter.api.Test;
import tsp.model.Location;
import tsp.model.Route;

import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomRouteGeneratorTest {

    private final Set<Location> locations = Set.of(
            new Location("CityA", 5, 10),
            new Location("CityB", 21, 2),
            new Location("CityC", 35, 47),
            new Location("CityD", 4, 12)
    );

    @Test
    void generateRoute() {

        Route randomRoute = RandomRouteGenerator.generate(new Random(), locations);

        assertEquals(4, randomRoute.getLocations().size());
        assertTrue(randomRoute.getLength() > 0);
    }

}
