package tsp.model.algorithms.helpers;

public class BusyWaiting {

    private BusyWaiting() {
    }

    public static void busyWaiting(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
