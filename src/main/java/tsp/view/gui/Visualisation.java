package tsp.view.gui;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import tsp.controller.Controller;
import tsp.model.Route;
import tsp.model.TSPConfiguration;
import tsp.view.TSPView;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

public class Visualisation extends JFrame implements TSPView {

    private final Controller controller;

    private final Graph currentGraph = new Graph();
    private final Graph bestGraph = new Graph();
    private final InfoPanel infoPanel = new InfoPanel();

    private final XYChart chart = new XYChartBuilder().xAxisTitle("Iteration").yAxisTitle("Route Length").width(500).height(500).build();
    private final XChartPanel<XYChart> chartPanel = new XChartPanel<>(chart);
    private double[] chartMinimalLengths = new double[0];
    private double[] chartIterations = new double[0];

    private int routeNumber = 0;

    public Visualisation(Controller controller) {

        this.controller = controller;

        chart.addSeries("RL", new double[]{0.0}, new double[]{0.0});
        chart.getStyler().setYAxisMin(0.0);

        setTitle("Travelling Salesman Search");
        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(currentGraph);
        add(bestGraph);
        add(chartPanel);
        add(infoPanel);

        pack();
        setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        setVisible(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quitGUI();
            }
        });
    }

    public void quitGUI() {
        controller.unregisterView(this);
        dispose();
    }

    public void visualizeCandidate(Route candidate) {
        currentGraph.visualizeRoute(candidate);
    }

    public void visualizeBest(Route candidate) {
        bestGraph.visualizeRoute(candidate);
    }

    @Override
    public void notifyRegistered(TSPConfiguration configuration) {
        infoPanel.displayConfiguration(configuration);
    }

    @Override
    public void notifySearchStarted() {
    }

    @Override
    public void notifyNewRouteTested(Route newRoute) {

        infoPanel.updateCurrentIteration(routeNumber);
        visualizeCandidate(newRoute);

        routeNumber++;
    }

    @Override
    public void notifyBetterRouteFound(Route betterRoute) {

        infoPanel.updateBestIteration(routeNumber);
        infoPanel.updateBestLength(betterRoute.getLength());

        chartMinimalLengths = Arrays.copyOf(chartMinimalLengths, chartMinimalLengths.length + 1);
        chartMinimalLengths[chartMinimalLengths.length - 1] = betterRoute.getLength();

        chartIterations = Arrays.copyOf(chartIterations, chartIterations.length + 1);
        chartIterations[chartIterations.length - 1] = routeNumber;

        chart.updateXYSeries("RL", chartIterations, chartMinimalLengths, null);
        chartPanel.repaint();

        visualizeBest(betterRoute);
    }

    @Override
    public void notifyStopSignalReceived() {
    }

    @Override
    public void notifyFinished(Route bestFoundRoute) {
    }

}
