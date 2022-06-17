package tsp.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouteTest {

    Location location1 = new Location("locationA", 10, 20);
    Location location2 = new Location("locationB", 50, 1);
    Location location3 = new Location("locationC", 123, 321);

    Route route = new Route(List.of(location1, location2, location3));

    @Test
    void getLocations() {
        assertEquals(List.of(location1, location2, location3), route.getLocations());
    }

    @Test
    void getLength() {
        assertEquals(694.0161944603046, route.getLength());
    }

    @Test
    void testToString() {
        assertEquals("Length: 694.0161944603046 [locationA -> locationB -> locationC]", route.toString());
    }

}
