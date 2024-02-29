package solving_techniques.p9_TwoHeaps;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639c960d0ce4573267ea5666
 * OR
 * 502. IPO
 * https://leetcode.com/problems/ipo (hard)
 *
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO.
 * Since it has limited resources, it can only finish at most k distinct projects before the IPO.
 * Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 *
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * Initially, you have w capital.
 * When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 *
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * Example 1:
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 *
 * Example 2:
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 *
 * Constraints:
 * 1 <= k <= 10^5
 * 0 <= w <= 10^9
 * n == profits.length
 * n == capital.length
 * 1 <= n <= 10^5
 * 0 <= profits[i] <= 10^4
 * 0 <= capital[i] <= 10^9
 */
public class MaximizeCapital {
    public static void main(String[] args) {
        int k = 2;
        int w = 0;
        int[] profits = {1,2,3};
        int[] capital = {0,1,1};
        int result = new MaximizeCapital().findMaximizedCapital(k, w, profits, capital);
        System.out.println(result);
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 40 -60 mins (including experiments with comparators)
     *
     * time ~ O((N+k)*logN)
     * space ~ O(N)
     *
     * 3 attempts:
     * - syntax errors
     * - incorrect comparator for leftMaxHeap
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        Queue<Pair> leftMaxHeap = new PriorityQueue<>((a, b) -> {
            //the higher profit the closer to the top of the heap
            return b.profit - a.profit;
        });

        Queue<Pair> rightMinHeap = new PriorityQueue<>((a, b) -> {
            if (a.capital == b.capital) {
                return b.profit - a.profit;
            } else {
                return a.capital - b.capital;
            }
        });

        //split into 2 heaps OR we can put all elements in rightMinHeap, as here
        //https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%F0%9F%99%83%20Pattern%2009%3A%20Two%20Heaps.md
        int n = profits.length;
        for (int i = 0; i < n; i++) {
            if (capital[i] <= w) {
                leftMaxHeap.add(new Pair(capital[i], profits[i]));
            } else {
                rightMinHeap.add(new Pair(capital[i], profits[i]));
            }
        }

        int counter = 0;    //counter of projects
        int result = w;
        while (counter < k && !leftMaxHeap.isEmpty()) {
            Pair p = leftMaxHeap.poll();
            result += p.profit;
            counter++;

            //move all elements that's capital <= result from right to left heap
            while (!rightMinHeap.isEmpty() && result >= rightMinHeap.peek().capital) {
                leftMaxHeap.add(rightMinHeap.poll());
            }

        }

        return result;
    }

    class Pair {
        int capital;
        int profit;

        Pair(int c, int p) {
            this.capital = c;
            this.profit = p;
        }
    }
}
