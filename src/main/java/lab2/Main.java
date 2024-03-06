package lab2;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        ResultCollector resultCollector = new ResultCollector();

        int numberOfThreads = Integer.parseInt(args[0]);

        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new PrimeChecker(taskQueue, resultCollector));
            thread.start();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter numbers to check for primality (or 'exit' to quit):");
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            try {
                long number = Long.parseLong(input);
                taskQueue.addTask(new Task(number));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or 'exit' to quit.");
            }
        }

        // Closing all threads
        System.out.println("Closing application...");
        System.exit(0);
    }
}
