package tsp.view.gui;

import tsp.model.TSPConfiguration;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

public class InfoPanel extends JPanel {

    private final JLabel problemLabel = new JLabel();
    private final JLabel numberOfLocationsLabel = new JLabel();
    private final JLabel algorithmLabel = new JLabel();

    private final JLabel currentIterationLabel = new JLabel();
    private final JLabel bestIterationLabel = new JLabel();
    private final JLabel bestLengthLabel = new JLabel();

    public InfoPanel() {

        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setLayout(new GridLayout(0, 2));

        add(new JLabel("Problem:"));
        add(problemLabel);
        add(new JLabel("Number of Locations:"));
        add(numberOfLocationsLabel);
        add(new JLabel("Algorithm:"));
        add(algorithmLabel);

        add(new JLabel("Current Iteration:"));
        add(currentIterationLabel);
        add(new JLabel("Best Iteration:"));
        add(bestIterationLabel);
        add(new JLabel("Best Length:"));
        add(bestLengthLabel);
    }

    public void updateCurrentIteration(int newCurrentIteration) {
        currentIterationLabel.setText(String.valueOf(newCurrentIteration));
    }

    public void updateBestIteration(int newBestIteration) {
        bestIterationLabel.setText(String.valueOf(newBestIteration));
    }

    public void updateBestLength(double newBestLength) {
        bestLengthLabel.setText(String.valueOf(newBestLength));
    }

    public void displayConfiguration(TSPConfiguration configuration) {
        problemLabel.setText(configuration.getProblemGenerator().getClass().getSimpleName());
        numberOfLocationsLabel.setText(String.valueOf(configuration.getNumberOfLocations()));
        algorithmLabel.setText(configuration.getAlgorithm().getClass().getSimpleName());
    }

}
