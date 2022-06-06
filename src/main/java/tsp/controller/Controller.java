package tsp.controller;

import tsp.model.Location;
import tsp.model.Route;
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

    private static final int DEFAULT_NUMBER_OF_LOCATIONS = 20;

    private final List<TSPView> observers = new ArrayList<>();

    private StoppingCondition stoppingCondition;
    private Thread searchThread;
    private TSPProblemGenerator problemGenerator;
    private int numberOfLocations;
    private TSPAlgorithm searchAlgorithm;
    private Route bestFoundRoute;

    public void initialize(String pProblemGenerator, int pNumberOfLocations, String pSearchAlgorithm) {

        stoppingCondition = new StoppingCondition();

        if (pNumberOfLocations == -1) {
            numberOfLocations = DEFAULT_NUMBER_OF_LOCATIONS;
        } else {
            numberOfLocations = pNumberOfLocations;
        }

        switch (pProblemGenerator) {
            case "random":
            default:
                problemGenerator = new RandomTSPProblemGenerator(new Random(), numberOfLocations, 500, 500);
                break;
            case "circular":
                problemGenerator = new CircularTSPProblemGenerator(numberOfLocations, 250);
                break;
        }

        Set<Location> problem = problemGenerator.generateProblem();

        switch (pSearchAlgorithm) {
            case "RandomSearch":
            default:
                searchAlgorithm = new RandomSearch(this, problem, stoppingCondition, new Random());
                break;
            case "GeneticSearch":
                searchAlgorithm = new GeneticSearch(this, problem, stoppingCondition, new Random());
                break;
        }

        searchThread = new Thread(() -> bestFoundRoute = searchAlgorithm.searchRoute());

        for (TSPView view : observers) {
            view.notifyInitialized("gen:" + problemGenerator.getClass().getSimpleName() + "; #locations:" + numberOfLocations + "; algo:" + searchAlgorithm.getClass().getSimpleName());
        }
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

        stoppingCondition.trigger();

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
        // TODO create separate Configuration class
        view.notifyRegistered("gen:" + problemGenerator.getClass().getSimpleName() + "; #locations:" + numberOfLocations + "; algo:" + searchAlgorithm.getClass().getSimpleName());
    }

    public void unregisterView(TSPView view) {

        if (observers.size() == 1 && observers.contains(view)) {
            stopSearch();
        }

        observers.remove(view);
    }

}
