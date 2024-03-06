package lab2;

public class Result {
    private final long number;
    private final boolean isPrime;

    public Result(long number, boolean isPrime) {
        this.number = number;
        this.isPrime = isPrime;
    }

    public long getNumber() {
        return number;
    }

    public boolean isPrime() {
        return isPrime;
    }
}