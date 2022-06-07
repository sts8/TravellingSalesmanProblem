package tsp.model;

import tsp.model.algorithms.TSPAlgorithm;
import tsp.model.algorithms.helpers.StoppingCondition;
import tsp.model.problem_generators.TSPProblemGenerator;

public class TSPConfiguration {

    private final TSPProblemGenerator problemGenerator;
    private final int numberOfLocations;
    private final TSPAlgorithm algorithm;
    private final StoppingCondition stoppingCondition;

    public TSPConfiguration(TSPProblemGenerator problemGenerator, int numberOfLocations, TSPAlgorithm algorithm, StoppingCondition stoppingCondition) {
        this.problemGenerator = problemGenerator;
        this.numberOfLocations = numberOfLocations;
        this.algorithm = algorithm;
        this.stoppingCondition = stoppingCondition;
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

}
