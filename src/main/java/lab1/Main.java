package lab1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Mage> ml = getMages();

        Iterator<Mage> custIterator = ml.iterator();
        System.out.println("Before Sorting:\n");

        while (custIterator.hasNext()) {
            System.out.println(custIterator.next());
        }

        ml.sort(new SortMagesNaturally());
        System.out.println("\n\nAfter Sorting Naturally:\n");

        for (Mage m : ml) {
            System.out.println(m);
        }

        ml.sort(new SortMagesAlternatively());
        System.out.println("\n\nAfter Sorting Alternatively:\n");

        for (Mage m : ml) {
            System.out.println(m);
        }

        System.out.println("\n\nPrinting apprentices:\n");
        for (Mage m : ml) {
            m.printRecursive(m.getApprentices(), 0);
        }

        System.out.println("\n\nCount children:\n");
        for (Mage m : ml) {
            System.out.println(m.getName() + " children: " + m.countChildren(m.getApprentices()));
        }
    }

    private static List<Mage> getMages() {
        List<Mage> ml = new ArrayList<>();

        Mage m1 = new Mage("Sirus", 10, 15);
        Mage m2 = new Mage("Tyrius", 44, 22);
        Mage m3 = new Mage("Aganb", 9, 9);
        Mage m4 = new Mage("Cyffen", 40, 2);

        HashSet<Mage> m5_app = new HashSet<>();
        m5_app.add(m1);
        m5_app.add(m2);

        Mage m5 = new Mage("Ckken", 80, 2);
        Mage m6 = new Mage("Kyre", 404, 2);

        HashSet<Mage> m7_app = new HashSet<>();
        m7_app.add(m5);
        m7_app.add(m6);

        Mage m7 = new Mage("Kistar", 40, 9);

        m5.setApprentices(m5_app);
        m7.setApprentices(m7_app);

        Mage m8 = new Mage("Kistar", 660, 91);
        Mage m9 = new Mage("Tistar", 6, 90);
        Mage m10 = new Mage("Oostar", 34, 23);

        HashSet<Mage> m10_app = new HashSet<>();
        m10_app.add(m7);
        m10_app.add(m8);
        m10_app.add(m9);

        m10.setApprentices(m10_app);

        ml.add(m1);
        ml.add(m2);
        ml.add(m3);
        ml.add(m4);
        ml.add(m5);
        ml.add(m6);
        ml.add(m7);
        ml.add(m8);
        ml.add(m9);
        ml.add(m10);
        return ml;
    }

}
