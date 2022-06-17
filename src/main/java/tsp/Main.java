package tsp;

import org.apache.commons.cli.ParseException;
import tsp.controller.Controller;
import tsp.controller.TSPCommandLineParser;

public class Main {

    public static void main(String[] args) throws ParseException {
        Controller controller = TSPCommandLineParser.parse(args);
        controller.startSearch(); // views currently can not start the search themselves
    }

}
