package tsp.controller;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import tsp.view.cmdline.CmdLineView;
import tsp.view.gui.Visualisation;

import java.security.InvalidParameterException;

public class TSPCommandLineParser {

    private TSPCommandLineParser() {
    }

    public static Controller parse(String[] args) throws ParseException {

        Options options = new Options();
        options.addOption("p", "problem", true, "(\"random\"/\"circular\"). Which problem generator to use.");
        options.addOption("l", "locations", true, "(4-9999). Number of locations on the map.");
        options.addOption("a", "algorithm", true, "(\"genetic\"/\"random\"). Search algorithm to use.");
        options.addOption("c", "cmdline", false, "Use command line as view.");
        options.addOption("g", "gui", false, "Use GUI as view.");
        options.addOption("s", "slow", false, "Slow down the search algorithm to improve visualisation.");
        options.addOption("h", "help", false, "Prints this help.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (
                ParseException e) {
            printHelp(options);
            throw e;
        }

        String problemGenerator = Controller.DEFAULT_PROBLEM_GENERATOR;
        int locations = Controller.DEFAULT_NUMBER_OF_LOCATIONS;
        String algorithm = Controller.DEFAULT_ALGORITHM;
        boolean slow = false;

        if (cmd.hasOption("h")) {
            printHelp(options);
            System.exit(0);
        }

        if (!cmd.hasOption("c") && !cmd.hasOption("g")) {
            throw new InvalidParameterException("Either GUI or cmdline (or both) must be specified!");
        }

        if (cmd.hasOption("p")) {
            problemGenerator = cmd.getOptionValue("p");
        } else {
            System.err.println("No problem generator type specified. Using " + Controller.DEFAULT_PROBLEM_GENERATOR + ".");
        }

        if (cmd.hasOption("l")) {
            try {
                locations = Integer.parseInt(cmd.getOptionValue("l"));
            } catch (NumberFormatException e) {
                throw new InvalidParameterException("Parsing number of locations failed. Details: " + e.getMessage());
            }
        } else {
            System.err.println("Number of locations not specified. Using " + Controller.DEFAULT_NUMBER_OF_LOCATIONS + ".");
        }

        if (cmd.hasOption("a")) {
            algorithm = cmd.getOptionValue("a");
        } else {
            System.err.println("Algorithm not specified. Using " + Controller.DEFAULT_ALGORITHM + ".");
        }

        if (cmd.hasOption("s")) {
            slow = true;
        }

        Controller controller = new Controller(problemGenerator, locations, algorithm, slow);

        if (cmd.hasOption("c")) {
            controller.registerView(new CmdLineView(controller));
        }

        if (cmd.hasOption("g")) {
            controller.registerView(new Visualisation(controller));
        }

        return controller;
    }

    private static void printHelp(Options options) {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null); // don't sort options alphabetically
        formatter.printHelp(120, "java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar", null, options, null);
    }

}
