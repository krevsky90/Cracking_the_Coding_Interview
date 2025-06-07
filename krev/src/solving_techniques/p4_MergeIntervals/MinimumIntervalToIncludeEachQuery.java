package solving_techniques.p4_MergeIntervals;

import java.util.*;

/**
 * 1851. Minimum Interval to Include Each Query (hard)
 * https://leetcode.com/problems/minimum-interval-to-include-each-query
 *
 * #Company (7.06.2025): 0 - 3 months Google 2 6 months ago Amazon 2
 *
 * You are given a 2D integer array intervals, where intervals[i] = [lefti, righti]
 *      describes the ith interval starting at lefti and ending at righti (inclusive).
 * The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
 *
 * You are also given an integer array queries.
 * The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti.
 * If no such interval exists, the answer is -1.
 *
 * Return an array containing the answers to the queries.
 *
 * Example 1:
 * Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * Output: [3,3,1,4]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
 * - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
 * - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
 * - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
 *
 * Example 2:
 * Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * Output: [2,-1,4,6]
 * Explanation: The queries are processed as follows:
 * - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
 * - Query = 19: None of the intervals contain 19. The answer is -1.
 * - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
 * - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.
 *
 * Constraints:
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 */
public class MinimumIntervalToIncludeEachQuery {
    /**
     * Approach 1: sweep line
     * info: https://leetcode.com/problems/minimum-interval-to-include-each-query/solutions/4689573/linesweep-heap/
     *
     * idea 1:
     * parse the intervals and queries into an array of events in which three sorts of events are contained, sorted by the time:
     * 0: start of an interval
     * 1: a query
     * 2: end of an interval
     *
     * idea 1.2: sort list of created events by pos. if pos is the same, sort by type: 0 -> 1 -> 2
     *
     * idea 2:
     * keep map of active lengths to its amount
     * keep PQ of lengths
     *
     * idea 3: traverse all events and if type = 1, then remove all not-relevant lengths from PQ
     *
     * idea 4: keep qToLength map to have answer to each value of query
     * idea 4.2: to each query find the value (from qTolength) and save it as part of answer
     *
     * time ~ ((n + m)*log(n + m)), m = queries.length, n = intervals.length
     *  - this time is required for sorting
     *  - also PQ will have (2*n + m) insertions/deletions
     *
     *  time to solve ~ 2h
     *
     */
    class Event {
        int type;   // 0 - start of interval, 1 - query, 2 - end of interval
        int pos;
        int length; // = -1 as stub for query type

        public Event(int type, int pos, int length) {
            this.type = type;
            this.pos = pos;
            this.length = length;
        }
    }

    public int[] minInterval(int[][] intervals, int[] queries) {
        //generate all events:
        List<Event> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new Event(0, interval[0], interval[1] - interval[0] + 1));
            events.add(new Event(2, interval[1], interval[1] - interval[0] + 1));
        }

        for (int q : queries) {
            events.add(new Event(1, q, -1));
        }

        //sort by pos. if pos is the same, sort by type: 0 -> 1 -> 2
        Collections.sort(events, (a, b) -> a.pos == b.pos ? (a.type - b.type) : (a.pos - b.pos));

        Map<Integer, Integer> activeLengths = new HashMap<>(); //length -> amount of such intervals (since there might be 2 similar intervals, for example)
        PriorityQueue<Integer> pq = new PriorityQueue<>();  //keep length of (ideally active) intervals

        //since we re-ordered queries, we need to store the results to map and then transform it according to the initial queries array
        Map<Integer, Integer> qToLength = new HashMap<>();

        for (Event e : events) {
            if (e.type == 0) {
                //start of some interval
                activeLengths.put(e.length, activeLengths.getOrDefault(e.length, 0) + 1);
                pq.add(e.length);
            } else if (e.type == 2) {
                //end of some interval
                activeLengths.put(e.length, activeLengths.get(e.length) - 1);
                if (activeLengths.get(e.length) == 0) activeLengths.remove(e.length);

                //do not remove from pq! will do it once we get 'query' event
            } else {
                //i.e. e.type = 1
                //remove all not-relevant (i.e. not active) lengths from pq
                while (!pq.isEmpty() && !activeLengths.containsKey(pq.peek())) {
                    pq.poll();
                }

                if (pq.isEmpty()) {
                    qToLength.put(e.pos, -1);
                } else {
                    qToLength.put(e.pos, pq.peek());
                }
            }
        }

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            result[i] = qToLength.get(queries[i]);
        }

        return result;
    }

    /**
     * Approach 2:
     * info: https://leetcode.com/problems/minimum-interval-to-include-each-query/
     *
     * idea:
     * keep PQ that contains intervals that's start < current query
     * for each query:
     * 1) fill PQ
     * 2) remove intervals from PQ that has end < query
     * 3) pq.peek() (its length) is the answer for q
     *
     * time ~ O(queries.length * log(intervals.length))
     *
     *
     */
    //todo:
}
