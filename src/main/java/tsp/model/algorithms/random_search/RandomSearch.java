package tsp.model.algorithms.random_search;

import tsp.controller.Controller;
import tsp.model.Location;
import tsp.model.Route;
import tsp.model.algorithms.TSPAlgorithm;
import tsp.model.algorithms.helpers.RandomRouteGenerator;
import tsp.model.algorithms.helpers.StoppingCondition;

import java.util.Random;
import java.util.Set;

import static tsp.model.algorithms.helpers.BusyWaiting.busyWaiting;

public class RandomSearch extends TSPAlgorithm {

    public RandomSearch(Controller controller, Set<Location> problem, StoppingCondition stoppingCondition, Random random, boolean slow) {
        super(controller, problem, stoppingCondition, random, slow);
    }

    public Route searchRoute() {

        Route shortestRoute = null;
        double shortestRouteLength = Double.MAX_VALUE;

        while (stoppingCondition.isNotTriggered()) {

            if (slow) {
                busyWaiting(2); // slows the algorithm down, better for visualization
            }

            Route candidate = RandomRouteGenerator.generate(random, problem);
            notifyNewRouteTested(candidate);

            if (candidate.getLength() < shortestRouteLength) {
                shortestRoute = candidate;
                shortestRouteLength = candidate.getLength();
                notifyBetterRouteFound(candidate);
            }
        }

        return shortestRoute;
    }

}
