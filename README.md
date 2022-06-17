# Travelling Salesman Problem

Implementation of the search for an (optimal) solution to the Travelling Salesman Problem. Used as an example project.

![Travelling_Salesman_Search](https://user-images.githubusercontent.com/104705788/172116249-5566f2c7-642e-4cfc-bb3a-963551786143.png)


## TODO
- more tests!
- improve README file
  - list used concepts and their use
  - screenshots
- better run configs + as examples


## Build
    mvn package

## Usage
    usage: java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar
    -p,--problem <arg>     ("random"/"circular"). Which problem generator to use.
    -l,--locations <arg>   (4-9999). Number of locations on the map.
    -a,--algorithm <arg>   ("genetic"/"random"). Search algorithm to use.
    -c,--cmdline           Use command line as view.
    -g,--gui               Use GUI as view.
    -s,--slow              Slow down the search algorithm to improve visualisation.
    -h,--help              Prints this help.

    examples:
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar --gui
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar --problem circular --locations 50 --algorithm genetic --gui
