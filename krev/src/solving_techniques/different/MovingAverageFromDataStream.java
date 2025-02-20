package solving_techniques.different;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 346. Moving Average from Data Stream (easy) (locked)
 * https://leetcode.com/problems/moving-average-from-data-stream
 *
 * #Company (20.02.2025): 0 - 3 months Meta 41 Google 5 0 - 6 months Amazon 3 Spotify 3 6 months ago Arista Networks 3 LinkedIn 2 Activision 2 ZScaler 2 Blizzard 2
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 * Example 1:
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 * Constraints:
 * 1 <= size <= 1000
 * -10^5 <= val <= 10^5
 * At most 10^4 calls will be made to next.
 */
public class MovingAverageFromDataStream {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 5 mins
     * time ~ O(1)
     * space ~ O(size)
     *
     * 2 attempts:
     * - forgot to multiple to 1.0 to make the result as double
     *
     * BEATS ~ 64%
     */
    private Deque<Integer> q;
    private int size;
    private int sum;

    public MovingAverageFromDataStream(int size) {
        q = new LinkedList<>();
        this.size = size;
        this.sum = 0;
    }

    public double next(int val) {
        q.addFirst(val);
        sum += val;

        if (q.size() > size) {
            int toRemove = q.pollLast();
            sum -= toRemove;
        }

        return sum*1.0/q.size();

    }
}
