package edu.estu;

import picocli.CommandLine;

import java.util.concurrent.Callable;

public class GaltonBoard implements Callable<Integer> {
//Options for command line
    @CommandLine.Option(
            names = {"-numBins", "--numRow"},
            description = "it sets total number of Slots",
            defaultValue = "10")
    private int numberOfRows;

    @CommandLine.Option(
            names = {"-numThread", "--numBall"},
            description = "it sets total number of Ball(Thread)",
            defaultValue = "1000")
    private int numBalls;

    @Override
    public Integer call() throws Exception {
        int numSlots = numberOfRows;
        int[] results = new int[numSlots];
//Creates Thread according to what we give
        for (int i = 0; i < numBalls; i++) {
            Ball b = new Ball(results,numSlots);
            Thread t = new Thread(b);
            t.start();
        }

        Thread.sleep(1000);
//Printing Stage
        System.out.println("Row Counts:");
        for (int i = 0; i < numSlots; i++) {
            System.out.println("Row" + (i + 1) + ": " + results[i]);
        }

        int sum = 0;
        for (int result : results) {
            sum += result;
        }

        if (sum == numBalls) {
            System.out.println("All balls reached the bottom.");
        } else {
            System.out.println("Error: Ball count mismatch!");
        }

        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new GaltonBoard()).execute(args);
        System.exit(exitCode);
    }
}
