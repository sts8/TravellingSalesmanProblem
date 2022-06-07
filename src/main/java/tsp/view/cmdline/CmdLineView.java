package tsp.view.cmdline;

import tsp.controller.Controller;
import tsp.model.Route;
import tsp.model.TSPConfiguration;
import tsp.view.TSPView;

public class CmdLineView implements TSPView {

    public CmdLineView(Controller controller) {

        // CTRL+C should be used to stop the search.
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Command Line Interrupt, unregistering this Command Line View.");
            controller.unregisterView(this);
        }));
    }

    @Override
    public void notifyRegistered(TSPConfiguration configuration) {
        System.out.println("The controller reports that this cmdline view was successfully registered. Current configuration:");
        System.out.println("Problem: " + configuration.getProblemGenerator().getClass().getSimpleName());
        System.out.println("Number of Locations: " + configuration.getNumberOfLocations());
        System.out.println("Algorithm: " + configuration.getAlgorithm().getClass().getSimpleName());
    }

    @Override
    public void notifySearchStarted() {
        System.out.println("The controller reports that the search has started.");
    }

    @Override
    public void notifyNewRouteTested(Route newRoute) {
        // do nothing, not every tested route should be logged, only better routes
    }

    @Override
    public void notifyBetterRouteFound(Route betterRoute) {
        System.out.println(betterRoute);
    }

    @Override
    public void notifyStopSignalReceived() {
        System.out.println("The controller reports that a stop signal was received.");
    }

    @Override
    public void notifyFinished(Route bestFoundRoute) {
        System.out.println("The controller reports that the search has finished. Best found route:");
        System.out.println(bestFoundRoute);
    }

}
