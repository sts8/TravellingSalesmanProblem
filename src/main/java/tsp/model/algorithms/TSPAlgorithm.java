package tsp.model.algorithms;

import tsp.controller.Controller;
import tsp.model.Location;
import tsp.model.Route;
import tsp.model.algorithms.helpers.StoppingCondition;

import java.util.Objects;
import java.util.Random;
import java.util.Set;

public abstract class TSPAlgorithm {

    protected final Controller controller;
    protected final StoppingCondition stoppingCondition;
    protected final Set<Location> problem;
    protected final Random random;
    protected final boolean slow;

    public TSPAlgorithm(Controller controller, Set<Location> problem, StoppingCondition stoppingCondition, Random random, boolean slow) {
        this.controller = Objects.requireNonNull(controller);
        this.stoppingCondition = Objects.requireNonNull(stoppingCondition);
        this.problem = Objects.requireNonNull(problem);
        this.random = Objects.requireNonNull(random);
        this.slow = slow;
    }

    public void notifyNewRouteTested(Route newRoute) {
        controller.newRouteTested(newRoute);
    }

    public void notifyBetterRouteFound(Route betterRoute) {
        controller.betterRouteFound(betterRoute);
    }

    public abstract Route searchRoute();

}
