package data_structures.chapter1_arrays_n_strings.extra;

import java.util.*;

/**
 * 362. Design Hit Counter (medium) (blocked)
 * https://leetcode.com/problems/design-hit-counter
 * OR
 * https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
 *
 * #Company: 0 - 3 months Databricks 10  Uber 6 Bloomberg 3 Roblox 3 Google 2 Amazon 2 0 - 6 months Apple 2 Atlassian 2 Yandex 2 Dropbox 2 6 months ago Microsoft 8 Affirm 6 Reddit 4 Sentry 4 Cloudflare 3 Snowflake 3
 * <p>
 * Design a  hit counter which counts the number of hits received in the past 5 minutes (i.e., the past 300 seconds).
 * <p>
 * Your system should accept a timestamp parameter (in seconds granularity),
 * and you may assume that calls are being made to the system in chronological order
 * (i.e., timestamp is monotonically increasing).
 * Several hits may arrive roughly at the same time.
 * <p>
 * Implement the HitCounter class:
 * <p>
 * HitCounter()
 * Initializes the object of the  hit counter system.
 * void hit(int timestamp)
 * Records a hit that happened at timestamp (in seconds).
 * Several hits may happen at the same timestamp.
 * int getHits(int timestamp)
 * Returns the number of hits in the past 5 minutes from timestamp (i.e., the past 300 seconds).
 * <p>
 * Example 1:
 * Input
 * ["HitCounter", "hit", "hit", "hit", "getHits", "hit", "getHits", "getHits"]
 * [[], [1], [2], [3], [4], [300], [300], [301]]
 * Output
 * [null, null, null, null, 3, null, 4, 3]
 * <p>
 * Explanation
 * HitCounter hitCounter = new HitCounter();
 * hitCounter.hit(1);       // hit at timestamp 1.
 * hitCounter.hit(2);       // hit at timestamp 2.
 * hitCounter.hit(3);       // hit at timestamp 3.
 * hitCounter.getHits(4);   // get hits at timestamp 4, return 3.
 * hitCounter.hit(300);     // hit at timestamp 300.
 * hitCounter.getHits(300); // get hits at timestamp 300, return 4.
 * hitCounter.getHits(301); // get hits at timestamp 301, return 3.
 * <p>
 * Constraints:
 * 1 <= timestamp <= 2 * 10^9
 * All the calls are being made to the system in chronological order (i.e., timestamp is monotonically increasing).
 * At most 300 calls will be made to hit and getHits.
 * <p>
 * Follow up: What if the number of hits per second could be huge? Does your design scale?
 */
public class HitCounter {
    /**
     * SOLUTION #1
     * info: https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
     */
    public class HitCounterArrayDeque {
        private ArrayDeque<Integer> queue; // @note: ArrayDeque has no capacity restrictions

        // Why is ArrayDeque better than LinkedList?
        // If you need add/remove of the both ends, ArrayDeque is significantly better than a linked list if queue is > 100-200 elements.
        // LL creates object while ArrayDeque is based on the array
        // https://stackoverflow.com/questions/6163166/why-is-arraydeque-better-than-linkedlist

        /**
         * Initialize your data structure here.
         */
        public HitCounterArrayDeque() {
            queue = new ArrayDeque<Integer>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            queue.offer(timestamp);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */

        // Time Complexity : O(n)
        public int getHits(int timestamp) {
            int startTime = timestamp - 300;

            // remove all hits over 300 seconds old
            while (!queue.isEmpty() && queue.peek() <= startTime) {
                queue.poll();
            }
            return queue.size();
        }
    }

    /**
     * follow-up
     * THE BEST!
     * info: leetcode editorial:
     * idea:
     * keep use Deque, but store pair: timestamp, number of hits
     * <p>
     * time to implement ~ 5 mins
     * <p>
     * BEATS ~ 100%
     */
    public class HitCounterFollowUpDequeOfPairs {
        Deque<int[]> q; //store pairs: [0] - timestamp, [1] - amount of hits with this timestamp
        int result = 0;

        public HitCounterFollowUpDequeOfPairs() {
            q = new LinkedList<>();
        }

        public void hit(int timestamp) {
            if (q.isEmpty()) {
                q.add(new int[]{timestamp, 1});
            } else {
                int[] pair = q.peekLast();
                if (pair[0] == timestamp) {
                    pair[1]++;
                } else {
                    q.add(new int[]{timestamp, 1});
                }
            }
            result++;
        }

        public int getHits(int timestamp) {
            while (!q.isEmpty() && timestamp - q.peek()[0] >= 300) {
                int[] pairToRemove = q.poll();
                result -= pairToRemove[1];
            }

            return result;
        }
    }

    /**
     * follow-up:
     * info: info: https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
     */
    public class HitCounterFollowUpArraysTimesAndHits {
        private int[] times;
        private int[] hits;

        /**
         * Initialize your data structure here.
         */
        public HitCounterFollowUpArraysTimesAndHits() {
            int[] times = new int[300];
            int[] hits = new int[300];
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            int idx = timestamp % 300;
            if (times[idx] != timestamp) {
                times[idx] = timestamp; // update with most recent timestamp
                hits[idx] = 1;
            } else {
                ++hits[idx];
            }
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */

        // Time Complexity : O(n)
        public int getHits(int timestamp) {
            int res = 0;
            for (int i = 0; i < 300; ++i) {
                // values in times[] not ordered at all!
                //example like:
                //times = 1... 350... 300
                //hits =  2     1      3
                //then getHits(350) = 1 + 3 = 4
                if (timestamp - times[i] < 300) {
                    res += hits[i];
                }
            }
            return res;
        }
    }

    /**
     * SOLUTION #2:
     * info: info: https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
     */
    class HitCounterMap {
        private Map<Integer, Integer> counter;

        /**
         * Initialize your data structure here.
         */
        public HitCounterMap() {
            counter = new HashMap<>();
        }

        /**
         * Record a hit.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public void hit(int timestamp) {
            counter.put(timestamp, counter.getOrDefault(timestamp, 0) + 1);
        }

        /**
         * Return the number of hits in the past 5 minutes.
         *
         * @param timestamp - The current timestamp (in seconds granularity).
         */
        public int getHits(int timestamp) {
            int hits = 0;
            for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
                if (entry.getKey() + 300 > timestamp) {
                    hits += entry.getValue();
                }
            }
            return hits;
        }
    }

    /**
     * SOLUTION #3:
     * info: https://www.youtube.com/watch?v=MKihMUdG3O8
     * idea: binary search:
     */
}