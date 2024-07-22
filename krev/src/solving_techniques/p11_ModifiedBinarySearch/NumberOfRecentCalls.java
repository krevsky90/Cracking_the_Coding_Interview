package solving_techniques.p11_ModifiedBinarySearch;

import java.util.ArrayList;
import java.util.List;

/**
 * 933. Number of Recent Calls (easy)
 * https://leetcode.com/problems/number-of-recent-calls
 *
 * #Company: Yandex
 * <p>
 * You have a RecentCounter class which counts the number of recent requests within a certain time frame.
 * Implement the RecentCounter class:
 * RecentCounter()
 * Initializes the counter with zero recent requests.
 * int ping(int t)
 * Adds a new request at time t, where t represents some time in milliseconds,
 * and returns the number of requests that has happened in the past 3000 milliseconds (including the new request).
 * Specifically, return the number of requests that have happened in the inclusive range [t - 3000, t].
 * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
 * <p>
 * Example 1:
 * Input
 * ["RecentCounter", "ping", "ping", "ping", "ping"]
 * [[], [1], [100], [3001], [3002]]
 * Output
 * [null, 1, 2, 3, 3]
 * Explanation
 * RecentCounter recentCounter = new RecentCounter();
 * recentCounter.ping(1);     // requests = [1], range is [-2999,1], return 1
 * recentCounter.ping(100);   // requests = [1, 100], range is [-2900,100], return 2
 * recentCounter.ping(3001);  // requests = [1, 100, 3001], range is [1,3001], return 3
 * recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3
 * <p>
 * Constraints:
 * 1 <= t <= 10^9
 * Each test case will call ping with strictly increasing values of t.
 * At most 10^4 calls will be made to ping.
 */
public class NumberOfRecentCalls {
    /**
     * KREVSKY SOLUTION:
     * idea: since call contains increasing sequence of int numbers => we need to find the closest number,
     * that is the closest to 't - 3000' and greated then it
     * So I used binarySearch => time ~ O(logN)
     * <p>
     * time to solve ~ 11 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 15%
     * NOTE: interesting that my solution is SLOWER than SOLUTION #2 despite time complexity!
     */

    class RecentCounter {
        private List<Integer> calls;

        public RecentCounter() {
            calls = new ArrayList<>();
        }

        public int ping(int t) {
            calls.add(t);
            int idx = binarySearch(t - 3000);
            return calls.size() - idx;
        }

        private int binarySearch(int val) {
            if (val < calls.get(0)) return 0;

            int low = 0;
            int high = calls.size() - 1;
            while (low < high) {
                int mid = (low + high) / 2;
                if (calls.get(mid) == val) {
                    return mid;
                } else if (calls.get(mid) < val) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }

            return low;
        }
    }

    /**
     * SOLUTION #2:
     * info: https://leetcode.com/problems/number-of-recent-calls/solutions/5390658/beats-100-both-time-and-space-java-python-c-detailed-explanation/
     * idea:
     * <p>
     * Data Structure:
     * Use an array records to store timestamps of requests.
     * The start and end indices mark the window of valid requests within the last 3000 ms.
     * ping(int t) Method:
     * Add Timestamp: Insert the current timestamp t at the position indicated by end.
     * Remove Outdated Requests: Increment the start index until the requests are within the 3000 ms window (t - records[start] <= 3000).
     * Count Valid Requests: Return the count of requests within the window (end - start), which represents the number of valid requests in the last 3000 ms.
     * <p>
     * Complexity:
     * Time complexity: O(n)
     * Space complexity: O(1)
     *
     * BEATS ~ 98%
     */

    class RecentCounter2 {
        private final int[] records = new int[10000]; //
        private int start;
        private int end;

        public RecentCounter2() {
            start = 0;
            end = 0;
        }

        public int ping(int t) {
            while (start < end && (t - records[start] > 3000)) {
                start++; // if the difference in time is greater than 3000ms,
                // than increase the value of start unitl it's equal or less than 3000ms.
            }
            records[end++] = t; // Inserting the current time at the end
            return end - start; // Returning the answer including the element added just now.
        }
    }
}
