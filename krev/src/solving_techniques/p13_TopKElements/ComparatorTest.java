package solving_techniques.p13_TopKElements;

import java.util.*;

public class ComparatorTest {
    public static void main(String[] args) {
        Pair p1 = new Pair(0, 1);
        Pair p2 = new Pair(1, 2);
        Pair p3 = new Pair(3, 2);
        Pair p4 = new Pair(3, 4);

//        List<Pair> list = new ArrayList<>();
//        list.add(p2);
//        list.add(p3);
//        list.add(p1);
//        list.add(p4);
//
//        list.stream().forEach(System.out::print);
//        System.out.println("");
//        Collections.sort(list, new Comparator<Pair>() {
//            @Override
//            public int compare(Pair a, Pair b) {
//                if (a.capital == b.capital) {
//                    return b.profit - a.profit;
//                } else {
//                    return a.capital - b.capital;
//                }
//            }
//        });

//        list.stream().forEach(System.out::print);

        System.out.println("\nQueue:");
        Queue<Pair> q = new PriorityQueue<>((a, b) -> {
            if (a.capital == b.capital) {
                return b.profit - a.profit;
            } else {
                return a.capital - b.capital;
            }
        });

        q.add(p2);
        q.add(p3);
        q.add(p1);
        q.add(p4);
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }

        System.out.println("\nQueue sorted by profit:");
        Queue<Pair> q2 = new PriorityQueue<>((a, b) -> {
            return b.profit - a.profit;
        });

        q2.add(new Pair(1,0));
        q2.add(new Pair(4,0));
        q2.add(new Pair(10,0));
        q2.add(new Pair(2,0));
        while (!q2.isEmpty()) {
            System.out.println(q2.poll());
        }

    }

    static class Pair {
        int profit;
        int capital;

        Pair(int p, int c) {
            this.profit = p;
            this.capital = c;
        }

        public String toString() {
            return "[" + capital + ", " + profit + "]";
        }
    }

}
