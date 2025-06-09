package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 621. Task Scheduler (medium)
 * https://leetcode.com/problems/task-scheduler
 * <p>
 * #Company (9.06.2025): 0 - 3 months Google 5 Roblox 5 Amazon 4 Apple 3 Snowflake 3 Microsoft 2 Oracle 2 Uber 2 0 - 6 months BCG 9 Meta 3 6 months ago TikTok 13 DoorDash 9 Salesforce 5 Bloomberg 3 DE Shaw 3 Yahoo 3 IBM 2 Zeta 2 Intuit 2 Rubrik 2
 * <p>
 * You are given an array of CPU tasks, each labeled with a letter from A to Z, and a number n.
 * Each CPU interval can be idle or allow the completion of one task.
 * Tasks can be completed in any order, but there's a constraint:
 * there has to be a gap of at least n intervals between two tasks with the same label.
 * <p>
 * Return the minimum number of CPU intervals required to complete all tasks.
 * <p>
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A possible sequence is: A -> B -> idle -> A -> B -> idle -> A -> B.
 * After completing task A, you must wait two intervals before doing A again.
 * The same applies to task B. In the 3rd interval, neither A nor B can be done, so you idle.
 * By the 4th interval, you can do A again as 2 intervals have passed.
 * <p>
 * Example 2:
 * Input: tasks = ["A","C","A","B","D","B"], n = 1
 * Output: 6
 * Explanation: A possible sequence is: A -> B -> C -> D -> A -> B.
 * With a cooling interval of 1, you can repeat a task after just one other task.
 * <p>
 * Example 3:
 * Input: tasks = ["A","A","A", "B","B","B"], n = 3
 * Output: 10
 * Explanation: A possible sequence is: A -> B -> idle -> idle -> A -> B -> idle -> idle -> A -> B.
 * There are only two types of tasks, A and B, which need to be separated by 3 intervals. This leads to idling twice between repetitions of these tasks.
 * <p>
 * Constraints:
 * 1 <= tasks.length <= 10^4
 * tasks[i] is an uppercase English letter.
 * 0 <= n <= 100
 */
public class TaskScheduler {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) create freq map: Char -> freq
     * 2) put entries to max heap
     * 3) in loop (while heap is not empty):
     * - poll elements from heap until it is empty of we do k iterations.
     * for each iteration decrease freq for polled entry and out it to stack if freq > 0 after decreasing
     * also increment result counter
     * <p>
     * - if stack is not empty && k < (heap.size() - 1) then increment result (like we set idle elements) by (k - heap.size() + 1)
     * - if stack is not empty then return elements from stack to heap
     * <p>
     * time ~ O(tasks.length), since filling pq costs ~ O(26log26) ~ O(1). poll + add will be executed tasks.length times
     * space ~ O(26) ~ O(1)
     * <p>
     * time to solve ~ 30-40 mins
     * <p>
     * ~ 5 attempts:
     * <p>
     * BEATS ~ 16%
     */
    class Pair {
        Character task;
        int freq;

        Pair(Character c, int freq) {
            this.task = c;
            this.freq = freq;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : tasks) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));    //max heap
        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            pq.add(new Pair(e.getKey(), e.getValue()));
        }

        Stack<Pair> stack = new Stack<>();

        int result = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int k = 0; k < Math.min(size, n + 1); k++) {
                Pair el = pq.poll();
                result++;

                if (el.freq > 1) {
                    el.freq--;
                    stack.add(el);
                }
            }

            //add idle elements if not all elements are processed && n - size + 1 > 0
            if (!stack.isEmpty() && (n - size + 1 > 0)) {
                result += n - size + 1;
            }

            //return elements from stack to PQ:
            while (!stack.isEmpty()) {
                pq.add(stack.pop());
            }
        }

        return result;
    }

    /**
     * KREVSKY: optimized:
     * 1) we can use list instead of stack - does not matter
     * 2) can use int[26] array instead of map
     * !!! 3) we do not use task field in Pair => can keep only 'freq' integer in max heap
     *
     * BEATS ~ 26%
     */
    public int leastIntervalOptimized(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char task : tasks) {
            freq[task - 'A']++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());    //max heap
        for (int v : freq) {
            if (v > 0) {
                pq.add(v);
            }
        }

        Stack<Integer> stack = new Stack<>();

        int result = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int k = 0; k < Math.min(size, n + 1); k++) {
                int el = pq.poll();
                result++;

                if (el > 1) {
                    el--;
                    stack.add(el);
                }
            }

            //add idle elements if not all elements are processed && n - size + 1 > 0
            if (!stack.isEmpty() && (n - size + 1 > 0)) {
                result += n - size + 1;
            }

            //return elements from stack to PQ:
            while (!stack.isEmpty()) {
                pq.add(stack.pop());
            }
        }

        return result;
    }

    /**
     * Official: Approach 1 ~ my solution
     */
    public int leastIntervalOfficialApproach1(char[] tasks, int n) {
        // Build frequency map
        int[] freq = new int[26];
        for (char ch : tasks) {
            freq[ch - 'A']++;
        }

        // Max heap to store frequencies
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                pq.offer(freq[i]);
            }
        }

        int time = 0;
        // Process tasks until the heap is empty
        while (!pq.isEmpty()) {
            int cycle = n + 1;
            List<Integer> store = new ArrayList<>();
            int taskCount = 0;
            // Execute tasks in each cycle
            while (cycle-- > 0 && !pq.isEmpty()) {
                int currentFreq = pq.poll();
                if (currentFreq > 1) {
                    store.add(currentFreq - 1);
                }
                taskCount++;
            }
            // Restore updated frequencies to the heap
            store.forEach(pq::offer);
            // Add time for the completed cycle
            time += (pq.isEmpty() ? taskCount : n + 1);
        }
        return time;
    }
}
