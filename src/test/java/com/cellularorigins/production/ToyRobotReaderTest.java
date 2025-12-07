package com.cellularorigins.production;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ToyRobotReaderTest {
    private static List<String> testFiles = new ArrayList<>();

    @BeforeAll
    protected static void init() {
        testFiles = loadTestFiles();
    }

    /**
     * Loads all the test files
     *
     * @return A list of all the absolute paths of all test files
     */
    private static List<String> loadTestFiles() {
        ClassLoader classLoader = ToyRobotReaderTest.class.getClassLoader();
        File inputDir = new File(Objects.requireNonNull(classLoader.getResource("input")).getFile());
        List<String> fileNames = new ArrayList<>();
        for (File file : Objects.requireNonNull(inputDir.listFiles())) {
            fileNames.add(file.getAbsolutePath());
        }
        Collections.sort(fileNames);
        return fileNames;
    }

    @Test
    public void testSimulateRobotThrowExceptionDueToInvalidFilePath() {
        RuntimeException thrown = assertThrows(
            RuntimeException.class,
            () -> ToyRobotReader.simulateToyRobot(null),
            "Expected simulateToyRobot() to throw an exception when the file path is null"
        );
        Assertions.assertTrue(thrown.getMessage().contains("Invalid file path"));
    }

    @Test
    public void testSimulateRobotThrowExceptionDueToFileNotExists() {
        ArrayList<String> result = ToyRobotReader.simulateToyRobot("notExistsInput.txt");
        Assertions.assertTrue(result.isEmpty());
        /*RuntimeException thrown = assertThrows(
            RuntimeException.class,
            () -> ToyRobotReader.simulateToyRobot("test.txt"),
            "Expected simulateToyRobot() to throw an exception when the file does not exist"
        );
        Assertions.assertTrue(thrown.getMessage().equals("Input file does not exist"));*/
    }

    /**
     * Tests with the real files.
     * Read more <a href="https://dzone.com/articles/mock-the-file-system">Mock the File System</a>
     * @param tempDir {@link Path}
     */
    @Test
    public void testSimulateRobotWithValidFile(@TempDir Path tempDir) throws IOException {
        Path robotDir = tempDir.resolve("ToyRobotReaderTest");
        Files.createDirectory(robotDir);
        List<String> testCases = new ArrayList<>(
            Arrays.asList(
                "PLACE 0,0,NORTH\nMOVE\nREPORT;0,1,NORTH",
                "PLACE 0,0,NORTH\nLEFT\nREPORT;0,0,WEST",
                "PLACE 1,2,EAST\nMOVE\nMOVE\nLEFT\nMOVE\nREPORT;3,3,NORTH",
                "PLACE 0,0,NORTH\nMOVE\nREPORT\nPLACE 0,0,NORTH\nLEFT\nREPORT\nPLACE 1,2,EAST\nMOVE\nMOVE\nLEFT\n" +
                    "MOVE\nREPORT;0,1,NORTH$0,0,WEST$3,3,NORTH"
            )
        );
        for (String testCase : testCases) {
            String[] parts = testCase.split(";");
            Files.write(robotDir.resolve("ToyRobotReaderInput.txt"), parts[0].getBytes());
            Path filePath = robotDir.resolve("ToyRobotReaderInput.txt");
            ArrayList<String> result = ToyRobotReader.simulateToyRobot(filePath.toFile().getAbsolutePath());
            Assertions.assertNotNull(result);
            Assertions.assertEquals(parts[1], String.join("$",  result));
        }
    }

    @Test
    public void testSimulateRobotWithActualTestFile() {
        for (String testFile : testFiles) {
            System.out.println("Testing against file " + testFile);
            ArrayList<String> result = ToyRobotReader.simulateToyRobot(testFile);
            System.out.println(result);
            Assertions.assertNotNull(result);
        }
    }
}
