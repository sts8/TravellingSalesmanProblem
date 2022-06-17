package tsp.model.problem_generators;

import org.junit.jupiter.api.Test;
import tsp.model.Location;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircularTSPProblemGeneratorTest {
    
    @Test
    void generateMap() {

        CircularTSPProblemGenerator generator = new CircularTSPProblemGenerator(25, 100);
        Set<Location> map = generator.generateProblem();

        assertEquals(25, map.size());
//        System.out.println(map);
    }
}