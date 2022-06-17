package tsp.controller;

import tsp.model.Location;
import tsp.model.Route;
import tsp.model.TSPConfiguration;
import tsp.model.algorithms.TSPAlgorithm;
import tsp.model.algorithms.genetic_search.GeneticSearch;
import tsp.model.algorithms.helpers.StoppingCondition;
import tsp.model.algorithms.random_search.RandomSearch;
import tsp.model.problem_generators.CircularTSPProblemGenerator;
import tsp.model.problem_generators.RandomTSPProblemGenerator;
import tsp.model.problem_generators.TSPProblemGenerator;
import tsp.view.TSPView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Controller {

    public static final String DEFAULT_PROBLEM_GENERATOR = "random";
    public static final int DEFAULT_NUMBER_OF_LOCATIONS = 100;
    public static final String DEFAULT_ALGORITHM = "genetic";

    private final TSPConfiguration configuration;
    private final List<TSPView> observers = new ArrayList<>();
    private final Thread searchThread;

    private Route bestFoundRoute;

    public Controller(String pProblemGenerator, int pNumberOfLocations, String pSearchAlgorithm, boolean slow) {

        TSPProblemGenerator problemGenerator;
        int numberOfLocations;
        TSPAlgorithm algorithm;
        StoppingCondition stoppingCondition = new StoppingCondition();

        if (pNumberOfLocations < 10000 && pNumberOfLocations > 3) {
            numberOfLocations = pNumberOfLocations;
        } else {
            numberOfLocations = DEFAULT_NUMBER_OF_LOCATIONS;
        }

        switch (pProblemGenerator.toLowerCase()) {
            case "circular":
                problemGenerator = new CircularTSPProblemGenerator(numberOfLocations, 250);
                break;
            case "random":
                problemGenerator = new RandomTSPProblemGenerator(new Random(), numberOfLocations, 500, 500);
                break;
            default:
                throw new IllegalArgumentException("Bad problem generator type specified.");
        }

        Set<Location> problem = problemGenerator.generateProblem();

        switch (pSearchAlgorithm.toLowerCase()) {
            case "genetic":
                algorithm = new GeneticSearch(this, problem, stoppingCondition, new Random(), slow);
                break;
            case "random":
                algorithm = new RandomSearch(this, problem, stoppingCondition, new Random(), slow);
                break;
            default:
                throw new IllegalArgumentException("Bad algorithm specified.");
        }

        configuration = new TSPConfiguration(problemGenerator, numberOfLocations, algorithm, stoppingCondition, slow);

        searchThread = new Thread(() -> bestFoundRoute = algorithm.searchRoute());
    }

    public void startSearch() {

        if (searchThread.isAlive()) {
            return;
        }

        searchThread.start();

        for (TSPView view : observers) {
            view.notifySearchStarted();
        }
    }

    public void newRouteTested(Route newRoute) {
        for (TSPView view : observers) {
            view.notifyNewRouteTested(newRoute);
        }
    }

    public void betterRouteFound(Route betterRoute) {
        for (TSPView view : observers) {
            view.notifyBetterRouteFound(betterRoute);
        }
    }

    public void stopSearch() {

        if (!searchThread.isAlive()) {
            return;
        }

        configuration.getStoppingCondition().trigger();

        for (TSPView view : observers) {
            view.notifyStopSignalReceived();
        }

        try {
            searchThread.join();
        } catch (InterruptedException ignored) {
        }

        for (TSPView view : observers) {
            view.notifyFinished(bestFoundRoute);
        }
    }

    public void registerView(TSPView view) {
        observers.add(view);
        view.notifyRegistered(configuration);
    }

    public void unregisterView(TSPView view) {

        if (observers.size() == 1 && observers.contains(view)) {
            stopSearch();
        }

        observers.remove(view);
    }

}
