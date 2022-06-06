package tsp.view.gui;

import tsp.model.Location;
import tsp.model.Route;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;

public class Graph extends JPanel {

    private Route currentlyVisualizedRoute;

    public Graph() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(500, 500));
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        if (currentlyVisualizedRoute == null) {
            return;
        }

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        for (Location l : currentlyVisualizedRoute.getLocations()) {
            g2d.fillOval(l.getX() - 5, l.getY() - 5, 10, 10);
        }

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < currentlyVisualizedRoute.getLocations().size(); i++) {

            Location start = currentlyVisualizedRoute.getLocations().get(i);
            Location stop = currentlyVisualizedRoute.getLocations().get((i + 1) % currentlyVisualizedRoute.getLocations().size());

            g2d.drawLine(start.getX(), start.getY(), stop.getX(), stop.getY());

        }
    }

    public void visualizeRoute(Route route) {
        currentlyVisualizedRoute = Objects.requireNonNull(route);
        repaint();
    }

}
