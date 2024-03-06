package lab2;


public class PrimeChecker implements Runnable {
    private final TaskQueue taskQueue;
    private final ResultCollector resultCollector;

    public PrimeChecker(TaskQueue taskQueue, ResultCollector resultCollector) {
        this.taskQueue = taskQueue;
        this.resultCollector = resultCollector;
    }

    private boolean isPrime(long number) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (number <= 1) return false;
        if (number <= 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;
        for (int i = 5; (long) i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) return false;
        }
        return true;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Task task = taskQueue.getTask();
                long number = task.getNumberToCheck();
                boolean isPrime = isPrime(number);
                resultCollector.addResult(new Result(number, isPrime));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
