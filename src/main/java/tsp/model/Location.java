package tsp.model;

public class Location {

    private final String name;
    private final int x;
    private final int y;

    public Location(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return name + "(" + x + "," + y + ")";
    }

}
