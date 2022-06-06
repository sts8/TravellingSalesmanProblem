package tsp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import tsp.controller.Controller;
import tsp.view.cmdline.CmdLineView;
import tsp.view.gui.Visualisation;

public class Main {

    public static void main(String[] args) {

        Options options = new Options();
        options.addOption("p", "problem", true, "Random or circular problem.");
        options.addOption("l", "locations", true, "Number of locations on the map.");
        options.addOption("a", "algorithm", true, "Search algorithm to use.");
        options.addOption("c", "cmdline", false, "Use command line as view.");
        options.addOption("g", "gui", false, "Use GUI as view.");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.err.println("Command line argument parsing failed. Reason: " + e.getMessage());
            System.exit(1);
        }

        String problem = "";
        int locations = -1;
        String algorithm = "";

        if (cmd.hasOption("p")) {
            problem = cmd.getOptionValue("p");
        }

        if (cmd.hasOption("l")) {
            try {
                locations = Integer.parseInt(cmd.getOptionValue("l"));
            } catch (NumberFormatException ignored) {
            }
        }

        if (cmd.hasOption("a")) {
            algorithm = cmd.getOptionValue("a");
        }

        Controller controller = new Controller();
        controller.initialize(problem, locations, algorithm);

        if (cmd.hasOption("c")) {
            controller.registerView(new CmdLineView(controller));
        }

        if (cmd.hasOption("g")) {
            controller.registerView(new Visualisation(controller));
        }

        controller.startSearch();
    }

}
