package tsp.model.algorithms.genetic_search;

import tsp.controller.Controller;
import tsp.model.Location;
import tsp.model.Route;
import tsp.model.algorithms.TSPAlgorithm;
import tsp.model.algorithms.helpers.RandomRouteGenerator;
import tsp.model.algorithms.helpers.StoppingCondition;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static tsp.model.algorithms.helpers.BusyWaiting.busyWaiting;

public class GeneticSearch extends TSPAlgorithm {

    private static final int POPULATION_SIZE = 10;
    private static final double CROSSOVER_KEEP_LOCATIONS = 0.7;

    private Route shortestRoute;
    private double shortestRouteLength;

    public GeneticSearch(Controller controller, Set<Location> problem, StoppingCondition stoppingCondition, Random random, boolean busyWaiting) {
        super(controller, problem, stoppingCondition, random, busyWaiting);
    }

    @Override
    public Route searchRoute() {

        // reset state
        shortestRoute = null;
        shortestRouteLength = Double.MAX_VALUE;

        List<Route> population = new ArrayList<>();

        // generate initial population
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(RandomRouteGenerator.generate(random, problem));
        }

        evaluateRoutes(population);

        while (stoppingCondition.isNotTriggered()) {

            if (slow) {
                busyWaiting(20); // slows the algorithm down, better for visualization
            }

            population.addAll(generateOffspring(population)); // create next generation and add it to the population

            // sort and keep the better half
            population.sort(Comparator.comparingDouble(Route::getLength));
            population = population.subList(0, POPULATION_SIZE);
        }

        return shortestRoute;
    }

    private void evaluateRoutes(List<Route> routes) {

        for (Route r : routes) {
            notifyNewRouteTested(r);

            if (r.getLength() < shortestRouteLength) {
                shortestRoute = r;
                shortestRouteLength = r.getLength();

                notifyBetterRouteFound(r);
            }
        }
    }

    private List<Route> generateOffspring(List<Route> parents) {

        List<Route> offspring = new ArrayList<>();

        while (offspring.size() < POPULATION_SIZE) {

            Route parentA = deterministicBinaryTournamentSelection(parents);
            Route parentB = deterministicBinaryTournamentSelection(parents);

            // example (10 locations):
            // parent A: 0 1 [2 3 4 5 6 7 8] 9
            // parent B: 7 3 [1 5 9 2 6 4 0] 8

            List<Location> childALocations = new ArrayList<>();
            List<Location> childBLocations = new ArrayList<>();

            int totalNumberOfLocations = parentA.getLocations().size(); // e.g. 10
            int numberOfLocationsToKeepFromParent = (int) Math.floor(parentA.getLocations().size() * CROSSOVER_KEEP_LOCATIONS); // e.g. 7
            int numberOfPrefixLocations = random.nextInt(totalNumberOfLocations - numberOfLocationsToKeepFromParent); // e.g. [0-2]

            List<Location> crossoverLocationsA = parentA.getLocations().subList(numberOfPrefixLocations, numberOfPrefixLocations + numberOfLocationsToKeepFromParent);
            List<Location> crossoverLocationsB = parentB.getLocations().subList(numberOfPrefixLocations, numberOfPrefixLocations + numberOfLocationsToKeepFromParent);

            List<Location> restA = new ArrayList<>(parentB.getLocations());
            restA.removeAll(crossoverLocationsB);
//            Collections.shuffle(restA);

            List<Location> restB = new ArrayList<>(parentA.getLocations());
            restB.removeAll(crossoverLocationsA);
//            Collections.shuffle(restB);

            // prefix locations
            for (int i = 0; i < numberOfPrefixLocations; i++) {
                childALocations.add(restA.get(i));
                childBLocations.add(restB.get(i));
            }

            // crossover locations
            childALocations.addAll(crossoverLocationsB);
            childBLocations.addAll(crossoverLocationsA);

            // suffix locations
            for (int i = numberOfPrefixLocations; i < restA.size(); i++) {
                childALocations.add(restA.get(i));
                childBLocations.add(restB.get(i));
            }

            mutate(childALocations);
            mutate(childBLocations);

            offspring.add(new Route(childALocations));
            offspring.add(new Route(childBLocations));
        }

        evaluateRoutes(offspring);

        return offspring;
    }

    private Route deterministicBinaryTournamentSelection(List<Route> parents) {

        Route firstRoute = parents.get(random.nextInt(parents.size()));
        double firstLength = firstRoute.getLength();

        Route secondRoute = parents.get(random.nextInt(parents.size()));
        double secondLength = firstRoute.getLength();

        return firstLength < secondLength ? firstRoute : secondRoute;
    }

    private void mutate(List<Location> original) {

        int positionA = random.nextInt(original.size());
        int positionB = random.nextInt(original.size());

        Location locationA = original.get(positionA);
        Location locationB = original.get(positionB);

        original.set(positionA, locationB);
        original.set(positionB, locationA);
    }

}
