package tsp.model.problem_generators;

import tsp.model.Location;

import java.util.Set;

public interface TSPProblemGenerator {

    Set<Location> generateProblem();

}
