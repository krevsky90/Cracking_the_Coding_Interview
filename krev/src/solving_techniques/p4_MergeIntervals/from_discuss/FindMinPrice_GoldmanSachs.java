package solving_techniques.p4_MergeIntervals.from_discuss;

import java.util.*;

/**
 * https://leetcode.com/discuss/post/6802632/goldman-sachs-interview-experience-salt-yuaug/
 *
 * #real_question
 * #Goldman_Sachs
 * <p>
 * A popular online retailer allows vendors to specify different prices in advance
 * for the same item throughout the day. We now need to design an algorithm that
 * helps identify the lowest price for the item at any point of the day.
 * Assumptions:
 * <p>
 * For the algorithm, assume all vendors are selling the same product
 * and there is only one product being sold. Given a list that has
 * vendor information - ( startTime, endTime, price ) of the deal,
 * return a sorted list with different possible intervals and
 * the least price of the product during the interval.
 * <p>
 * The interval is inclusive of start and end time.
 * <p>
 * All the 3 values passed by the vendor are integers.
 * <p>
 * sampleInput = { new Interval( 1, 5, 20 ), new Interval( 3, 8, 15 ), new Interval( 7, 10, 8 ) };
 * expectedOutput = { new Interval( 1, 3, 20 ), new Interval( 3, 7, 15 ), new Interval(7,10,8)};
 */
public class FindMinPrice_GoldmanSachs {
    /**
     * KREVSKY SOLUTION
     * idea: sweep line approach
     * <p>
     * time to solve ~ 1h
     */
    class Interval {
        int start;
        int end;
        int price;

        public Interval(int start, int end, int price) {
            this.start = start;
            this.end = end;
            this.price = price;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    ", price=" + price +
                    '}';
        }
    }

    public List<Interval> findLowestPriceIntervals(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();

        Integer tempStart = null;
        Integer tempEnd = null;
        int price = -1;
        Set<Integer> currSet = new HashSet<>();

        Collections.sort(intervals, (a, b) -> a.start - b.start);

        Map<Integer, Set<Integer>> map = new HashMap<>();    //time position -> set of indexes of intervals that start of end in this time position

        for (int i = 0; i < intervals.size(); i++) {
            map.putIfAbsent(intervals.get(i).start, new HashSet<>());
            map.putIfAbsent(intervals.get(i).end, new HashSet<>());

            map.get(intervals.get(i).start).add(i);
            map.get(intervals.get(i).end).add(-i);    //negative to mark end of interval!
        }

        for (int time : map.keySet()) {
            tempStart = tempEnd;
            tempEnd = time;
            if (tempStart != null) {
                if (!result.isEmpty() && result.get(result.size() - 1).price == price) {
                    //just prolong the last saved interval
                    result.get(result.size() - 1).end = tempEnd;
                } else {
                    result.add(new Interval(tempStart, tempEnd, price));
                }
            }

            for (int n : map.get(time)) {
                if (n >= 0) {
                    currSet.add(n);
                } else {
                    currSet.remove(-n);
                }
            }

            price = Integer.MAX_VALUE;
            for (int k : currSet) {
                price = Math.min(price, intervals.get(k).price);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FindMinPrice_GoldmanSachs obj = new FindMinPrice_GoldmanSachs();
        obj.test();
    }

    public void test() {
        List<Interval> sampleInput = new ArrayList<>(List.of(
                new Interval(1, 5, 20),
                new Interval(3, 8, 15),
                new Interval(7, 10, 8)
        ));

        List<Interval> output = findLowestPriceIntervals(sampleInput);
        output.stream().forEach(System.out::println);
        System.out.println(output);
        // expectedOutput = { new Interval( 1, 3, 20 ), new Interval( 3, 7, 15 ), new Interval(7,10,8)};
    }

}
