# Travelling Salesman Problem

Implementation of the search for an (optimal) solution to the Travelling Salesman Problem. Used as an example project.
![Travelling_Salesman_Search](https://user-images.githubusercontent.com/104705788/174355415-43602bfc-ed89-4712-bc70-d915b1ec370d.gif)

## Features

- **XChart:** visualizing the best route length
- **Apache Commons CLI:** parsing command line parameters
- **GitHub workflow:** build and test after each commit
- **Maven:** dependency management, building a stand-alone executable
- **.gitignore:** from https://www.toptal.com/developers/gitignore/

## MVC + Observer Pattern

### Model

Contains the business logic.

- Problem representation classes (Location, Route)
- Problem Generators
- Search Algorithms

### View

Displays/visualises the model data and state.

- CmdLineView
- GUI/Visualisation (see animation)

### Controller

Manages Model and Views. Observer Pattern: Views can be registered and will be notified about events (see TSPView). The
Controller is notified by the search algorithm about events, manages the registered views and controls the search
procedure.

## Build

    mvn package

## Usage

    usage:
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar
    -p,--problem <arg>     ("random"/"circular"). Which problem generator to use.
    -l,--locations <arg>   (4-9999). Number of locations on the map.
    -a,--algorithm <arg>   ("genetic"/"random"). Search algorithm to use.
    -c,--cmdline           Use command line as view.
    -g,--gui               Use GUI as view.
    -s,--slow              Slow down the search algorithm to improve visualisation.
    -h,--help              Prints this help.

    examples:
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar --gui
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar --problem random --locations 150 --algorithm genetic --gui
