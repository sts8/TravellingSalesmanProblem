package tsp.view;

import tsp.model.Route;

public interface TSPView {

    void notifyRegistered(String configuration);

    void notifyInitialized(String configuration);

    void notifySearchStarted();

    void notifyNewRouteTested(Route newRoute);

    void notifyBetterRouteFound(Route betterRoute);

    void notifyStopSignalReceived();

    void notifyFinished(Route bestFoundRoute);

}
