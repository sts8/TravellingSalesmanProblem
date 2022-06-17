package tsp.model;

import tsp.model.algorithms.TSPAlgorithm;
import tsp.model.algorithms.helpers.StoppingCondition;
import tsp.model.problem_generators.TSPProblemGenerator;

public class TSPConfiguration {

    private final TSPProblemGenerator problemGenerator;
    private final int numberOfLocations;
    private final TSPAlgorithm algorithm;
    private final StoppingCondition stoppingCondition;
    private final boolean busyWaiting;

    public TSPConfiguration(TSPProblemGenerator problemGenerator, int numberOfLocations, TSPAlgorithm algorithm, StoppingCondition stoppingCondition, boolean busyWaiting) {
        this.problemGenerator = problemGenerator;
        this.numberOfLocations = numberOfLocations;
        this.algorithm = algorithm;
        this.stoppingCondition = stoppingCondition;
        this.busyWaiting = busyWaiting;
    }

    public TSPProblemGenerator getProblemGenerator() {
        return problemGenerator;
    }

    public int getNumberOfLocations() {
        return numberOfLocations;
    }

    public TSPAlgorithm getAlgorithm() {
        return algorithm;
    }

    public StoppingCondition getStoppingCondition() {
        return stoppingCondition;
    }

    public boolean isBusyWaiting() {
        return busyWaiting;
    }

}
