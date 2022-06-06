package tsp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {

    @Test
    void testGetters() {
        Location location = new Location("someCity", 10, 20);
        assertEquals("someCity", location.getName());
        assertEquals(10, location.getX());
        assertEquals(20, location.getY());
        assertEquals("someCity(10,20)", location.toString());
    }

}
