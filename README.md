# Travelling Salesman Problem

Implementation of the search for an (optimal) solution to the Travelling Salesman Problem. Used as an example project.

![Travelling_Salesman_Search](https://user-images.githubusercontent.com/104705788/172116249-5566f2c7-642e-4cfc-bb3a-963551786143.png)


## TODO
- more tests!
- improve README file
  - list used concepts and their use
  - screenshots
  - build + run
- improve genetic search, find best search parameters
- use and enforce code style template
- improve and refactor constants in GUI
- improve parameter parsing and handling


## Build
    mvn package

## Run
    java -jar ./target/TravellingSalesmanProblem-1.0-SNAPSHOT-jar-with-dependencies.jar --problem circular --locations 50 --algorithm GeneticSearch --gui
