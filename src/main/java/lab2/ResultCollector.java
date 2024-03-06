package lab2;


public class ResultCollector {
    public synchronized void addResult(Result result) {
        System.out.println("Result: " + result.getNumber() + " is prime: " + result.isPrime());
    }
}
