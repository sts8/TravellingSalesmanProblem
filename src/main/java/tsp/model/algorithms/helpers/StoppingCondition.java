package tsp.model.algorithms.helpers;

public class StoppingCondition {

    private boolean triggered = false;

    public void trigger() {
        triggered = true;
    }

    public boolean isNotTriggered() {
        return !triggered;
    }

}
