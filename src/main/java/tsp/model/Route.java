package tsp.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Route {

    private final List<Location> locations;
    private final double length;

    public Route(List<Location> locations) {
        this.locations = Objects.requireNonNull(locations);
        this.length = calculateLength();
    }

    private double calculateLength() {

        double length = 0.0;

        for (int i = 0; i < locations.size(); i++) {

            Location start = locations.get(i);
            Location stop = locations.get((i + 1) % locations.size());

            int deltaX = Math.abs(start.getX() - stop.getX());
            int deltaY = Math.abs(start.getY() - stop.getY());

            length += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        }

        return length;
    }

    public List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }


    public double getLength() {
        return length;
    }

    @Override
    public String toString() {

        StringJoiner route = new StringJoiner(" -> ");

        for (Location l : locations) {
            route.add(l.getName());
        }

        return "Length: " + length + " [" + route + "]";
    }

}
