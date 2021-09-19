package mydatas.sql.webs.KeyGen;

import java.util.*;

public class GenNum {
    static int i = 0;

    public static void main(String[] args) {
        getSet(2300000);
    }

    public static Set<Integer> getSet(int length) {
        LinkedHashSet<Integer> integers = new LinkedHashSet<>();
        Random random = new Random();

        while (integers.size() <= length) {
            try {
                integers.add(random.nextInt(8000000) + 17000000);
            } catch (Exception e) {
                continue;
            }
            i++;
        }
        System.out.println(i);
        return integers;
    }

    public static List<Integer> getQueue(int length) {
        Set<Integer> set = getSet(length);
        return new LinkedList<>(set);
    }
}
