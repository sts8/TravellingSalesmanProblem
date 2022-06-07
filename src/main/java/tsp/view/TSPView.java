package tsp.view;

import tsp.model.Route;
import tsp.model.TSPConfiguration;

public interface TSPView {

    void notifyRegistered(TSPConfiguration configuration);

    void notifySearchStarted();

    void notifyNewRouteTested(Route newRoute);

    void notifyBetterRouteFound(Route betterRoute);

    void notifyStopSignalReceived();

    void notifyFinished(Route bestFoundRoute);

}
