# TOY ROBOT SIMULATOR IN JAVA
[![OpenJDK](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![Maven Central Version](https://img.shields.io/maven-central/v/org.projectlombok/lombok)](https://mvnrepository.com/artifact/org.projectlombok/lombok)
[![Homebrew](https://badgen.net/homebrew/v/fish)](https://brew.sh/)
[![JUnit5](https://img.shields.io/badge/JUnit5-f5f5f5?style=for-the-badge&logo=junit5&logoColor=dc524a)](https://docs.junit.org/current/user-guide/)
[![unit](https://github.com/fritx/jayin/actions/workflows/unit-test.yml/badge.svg)](https://github.com/ntung/toy-robot-simulator-in-java/actions/workflows/maven.yml)
[![cov](https://we-cli.github.io/jayin/badges/coverage.svg)](https://github.com/ntung/toy-robot-simulator-in-java/actions)

This is a command line Java application that simulates a robot on a square tabletop.

## Table of Contents
- [Description](#description)
- [Example Input And Output](#example-input-and-output)
- [Build and Run](#build-and-run)
- [Usage](#usage-instructions)
- [For Developers/Contributors](#for-developers-and-contributors)
- [Further Developments](#considerations-about-the-development)
- [Licence](#licence)

## Description
- The application is a simulation of a toy robot moving on a square tabletop,
  of dimensions 5 units x 5 units.
- There are no other obstructions on the table surface.
- The robot is free to roam around the surface of the table, but must be
  prevented from falling to destruction. Any movement that would result in the
  robot falling from the table must be prevented, however further valid movement
  commands must still be allowed.

The application that can read in commands of the following form

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y
  and facing NORTH, SOUTH, EAST or WEST.
- The origin (0,0) can be considered to be the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that,
  any sequence of commands may be issued, in any order, including another
  PLACE command. The application should discard all commands in the
  sequence until a valid PLACE command has been executed.
- MOVE will move the toy robot one unit forward in the direction it is currently
  facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot.
- Any move that would cause the robot to fall must be ignored.

## Example Input And Output

a)

	PLACE 0,0,NORTH
    MOVE
    REPORT

	Output: 0,1,NORTH

b)

	PLACE 0,0,NORTH
	LEFT
	REPORT
	
	Output: 0,0,WEST

c)

	PLACE 1,2,EAST
	MOVE
	MOVE
	LEFT
	MOVE
	REPORT

	Output: 3,3,NORTH

## Build And Run
**Exclamation**: This maven-based program is written in and tested with Java 11 or newer versions. To build JAR file 
with your default JDK, please make sure the following lines in `pom.xml` updated correspondingly.
```xml
<maven.compiler.source>25</maven.compiler.source>
<maven.compiler.target>25</maven.compiler.target>
```
In the snippet above shows that I have tested the program with Java 25. The step-by-steps instructions to compile, 
test, run and packaging are below.

* Compile: `mvn compile`
* Test: `mvn test`
* Run: `mvn exec:java` or `java -jar target/ToyRobotSimulator-jar-with-dependencies.jar`.
* Packaging: `mvn package`, the compiled jar-with-dependencies in *target/* folder

## Usage Instructions
```bash
$ ls -lht
drwxr-xr-x@ 20 tnguyen  staff   640B  5 Dec 19:37 htmlReport
-rw-r--r--@  1 tnguyen  staff   1.1K  5 Dec 09:54 LICENSE
-rw-r--r--@  1 tnguyen  staff   3.0K  5 Dec 21:32 pom.xml
-rw-r--r--@  1 tnguyen  staff   549B  5 Dec 21:34 README.md
drwxr-xr-x@  4 tnguyen  staff   128B  5 Dec 08:52 src
drwxr-xr-x@ 12 tnguyen  staff   384B  5 Dec 21:36 target
lrwxr-xr-x@  1 tnguyen  staff    50B  5 Dec 20:46 ToyRobotSimulator.jar -> target/ToyRobotSimulator-jar-with-dependencies.jar

$ java -jar ToyRobotSimulator.jar 
Welcome to Cellular Origins!
Usage: java -jar ToyRobotSimulator-jar-with-dependencies.jar <file of commands>

$ java -jar ToyRobotSimulator.jar 
Welcome to Cellular Origins!
Usage: java -jar ToyRobotSimulator-jar-with-dependencies.jar <file of commands>

$ java -jar ToyRobotSimulator.jar -i 
Welcome to Cellular Origins!
Play robot game now! Commands: PLACE X,Y,[NORTH, EAST, SOUTH, WEST]; MOVE; LEFT; RIGHT; REPORT. Type `quit` or press `Ctrl+C` to exit the game.
HELLO
Command is null. Try again with the built-in commands!
hi
Command is null. Try again with the built-in commands!
move
0,1,NORTH
REPORT
0,1,NORTH
MOVE
0,2,NORTH
LEFT
0,2,WEST
MOVE
0,2,WEST
MOVE
0,2,WEST
RIGHT
0,2,NORTH
MOVE
0,3,NORTH
MOVE
0,4,NORTH
RIGHT
0,4,EAST
MOVE
1,4,EAST
MOVE
2,4,EAST
QUIT
```
Below are the demos with the input files.
```bash
$ java -jar ToyRobotSimulator.jar -f src/main/resources/input/input01.txt 
Welcome to Cellular Origins!
Output: 0,1,NORTH

$ java -jar ToyRobotSimulator.jar -f src/main/resources/input/input02.txt
Welcome to Cellular Origins!
Output: 0,0,WEST

$ java -jar ToyRobotSimulator.jar -f src/main/resources/input/input03.txt
Welcome to Cellular Origins!
Output: 3,3,NORTH

$ java -jar ToyRobotSimulator.jar -f src/main/resources/input/input04.txt
Welcome to Cellular Origins!
Output: 0,1,NORTH
Output: 0,0,WEST
Output: 3,3,NORTH

$ java -jar ToyRobotSimulator.jar -f src/main/resources/input/input05.txt
Welcome to Cellular Origins!
src/main/resources/input/input05.txt does not exist

% java -jar ToyRobotSimulator.jar -f ~/tmp/test.groovy
Welcome to Cellular Origins!
Command is null. Try again with the built-in commands!
Command is null. Try again with the built-in commands!
Command is null. Try again with the built-in commands!
Command is null. Try again with the built-in commands!
```
## For Developers And Contributors
1. Clone this repository.
2. Open the project in any favourite IDE.
3. Make sure that the version of JDK no less than 11.

**Test Coverage Report**: It was uploaded alongside the project in `src/test/report/html-test-coverage`.

The top package is `com.cellularorigins`. The main class is named `Main.java` where the toy robot simulator is 
created. Two classes for creating Toy Robot locate in `com.cellularorigins.production` where we can add more methods.

## Considerations about the development
1. Extend the size of game board. It could be configured via `config.ini`, for instance, when running the program. 
   If no config file is provided, the program will create a board of 5x5 as the default dimension.
2. The initial position could be any position.
3. New commands could be also interesting. 
4. Support for multiple players: find the optimised route to reach the destination position earliest.
5. Develop a GUI and add an optional argument in the `main()` method to launch the program in graphic mode. 

## Licence
This program is under MIT Licence. Read [LICENCE](LICENCE) for more information.