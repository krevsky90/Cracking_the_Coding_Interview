package solving_techniques.p28_MonotonicStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/64c151c7505e25aa946412b6
 * OR
 * 739. Daily Temperatures
 * https://leetcode.com/problems/daily-temperatures (medium)
 *
 * Given an array of integers temperatures represents the daily temperatures,
 * return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Example 1:
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 *
 * Example 2:
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 *
 * Example 3:
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 *
 * Constraints:
 * 1 <= temperatures.length <= 10^5
 * 30 <= temperatures[i] <= 100
 */
public class DailyTemperatures {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) use Monotonically Decreasing Stack
     * 2) use mapping "value -> index" to find delta between indexes
     *
     * time to solve ~ 14 mins
     *
     * time ~ O(n), where n = temperatures.length
     * space ~ O(n)
     *
     * 1 attempt
     *
     * BEATS = 5%
     **/
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];    //0..0
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            int el = temperatures[i];
            while (!stack.isEmpty() && stack.peek() <= el) {
                map.remove(stack.pop());    //to clean stack and map, little space optimization
            }

            //do nothing if stack is empty, since we initially have 0s in the result array.
            if (!stack.isEmpty()) {
                result[i] = map.get(stack.peek()) - i;
            }

            stack.add(el);
            map.put(el, i);
        }

        return result;
    }

    /**
     * Optimization: use Monotonically Decreasing Stack that stores indexes, but not values!
     */
    public int[] dailyTemperaturesOptimized(int[] temperatures) {
        int[] result = new int[temperatures.length];    //0..0
        Stack<Integer> stack = new Stack<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            int el = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] <= el) {
                stack.pop();
            }

            //do nothing if stack is empty, since we initially have 0s in the result array.
            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            }

            stack.add(i);
        }

        return result;
    }
}
