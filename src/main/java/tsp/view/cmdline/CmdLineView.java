package tsp.view.cmdline;

import tsp.controller.Controller;
import tsp.model.Route;
import tsp.view.TSPView;

public class CmdLineView implements TSPView {

    public CmdLineView(Controller controller) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> controller.unregisterView(this))); // CTRL+C should be used to stop the search.
    }

    @Override
    public void notifyRegistered(String configuration) {
        System.out.println("The controller reports that this cmdline view was successfully registered. Current configuration:");
        System.out.println(configuration);
    }

    @Override
    public void notifyInitialized(String configuration) {
        System.out.println("The controller reports that it was (re-)initialized. New configuration:");
        System.out.println(configuration);
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
