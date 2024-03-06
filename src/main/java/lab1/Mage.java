package lab1;

import java.util.HashSet;
import java.util.Objects;

public class Mage {
    private final String name;
    private final int level;
    private final int power;
    private HashSet<Mage> apprentices = new HashSet<>();

    public Mage(String name, int level, int power) {
        this.name = name;
        this.level = level;
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public Integer getPower() {
        return power;
    }

    public Integer getLevel() {
        return level;
    }

    public HashSet<Mage> getApprentices() {
        return apprentices;
    }

    public void setApprentices(HashSet<Mage> apprentices) {
        this.apprentices = apprentices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && power == mage.power && Objects.equals(name, mage.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, power);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", power=" + power +
                '}';
    }

    public int countChildren(HashSet<Mage> apprentices) {

        int sum = 0;

        for (Mage m : apprentices) {
            if (!m.getApprentices().isEmpty()) {
                sum += countChildren(m.apprentices);
            }
            sum += 1;
        }

        return sum;
    }

    public void printRecursive(HashSet<Mage> apprentices, int level) {

        int count = 1;

        for (Mage m : apprentices) {

            System.out.println(" ".repeat(Math.max(0, level)) + level + "." + count + " " + m.toString());
            if (!m.getApprentices().isEmpty()) {
                printRecursive(m.apprentices, level + 1);
            }

            count += 1;
        }

    }
}
