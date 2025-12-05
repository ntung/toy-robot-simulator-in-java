package com.cellularorigins;

import com.cellularorigins.production.ToyRobotFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Welcome to Cellular Origins!\n");
        try  {
            if (args.length != 1 || args[0].isEmpty()) {
                System.out.println("Usage: java -jar ToyRobotSimulatorV1.0-SNAPSHOT-jar-with-dependencies.jar <file of commands>");
            } else {
                System.out.println("Play robot game now! Commands: MOVE X,Y,[NORTH, EAST, SOUTH, WEST]; LEFT; RIGHT; " +
                        "REPORT. Type quit to exit the game.");
                ToyRobotFactory.createToyRobot();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}