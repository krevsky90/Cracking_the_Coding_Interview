package solving_techniques.p29_Graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1376. Time Needed to Inform All Employees (medium)
 * https://leetcode.com/problems/time-needed-to-inform-all-employees
 * <p>
 * #Company (10.02.2025): 6 months ago Google 14 Amazon 7 Microsoft 6 Flipkart 2 Verily 2
 * <p>
 * A company has n employees with a unique ID for each employee from 0 to n - 1.
 * The head of the company is the one with headID.
 * Each employee has one direct manager given in the manager array
 * where manager[i] is the direct manager of the i-th employee, manager[headID] = -1.
 * Also, it is guaranteed that the subordination relationships have a tree structure.
 * The head of the company wants to inform all the company employees of an urgent piece of news.
 * He will inform his direct subordinates, and they will inform their subordinates,
 * and so on until all employees know about the urgent news.
 * The i-th employee needs informTime[i] minutes to inform all of his direct subordinates
 * (i.e., After informTime[i] minutes, all his direct subordinates can start spreading the news).
 * Return the number of minutes needed to inform all the employees about the urgent news.
 * <p>
 * Example 1:
 * Input: n = 1, headID = 0, manager = [-1], informTime = [0]
 * Output: 0
 * Explanation: The head of the company is the only employee in the company.
 * <p>
 * Example 2:
 * Input: n = 6, headID = 2, manager = [2,2,-1,2,2,2], informTime = [0,0,1,0,0,0]
 * Output: 1
 * Explanation: The head of the company with id = 2 is the direct manager of all the employees in the company and needs 1 minute to inform them all.
 * The tree structure of the employees in the company is shown.
 * <p>
 * Constraints:
 * 1 <= n <= 10^5
 * 0 <= headID < n
 * manager.length == n
 * 0 <= manager[i] < n
 * manager[headID] == -1
 * informTime.length == n
 * 0 <= informTime[i] <= 1000
 * informTime[i] == 0 if employee i has no subordinates.
 * It is guaranteed that all the employees can be informed.
 */
public class TimeNeededToInformAllEmployees {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) convert manager array to adjMap
     * 2) use BFS or DFS to find  max time needed
     * time to solve ~ 20 mins
     * <p>
     * 2 attempts:
     * - fixed with variables
     * <p>
     * BEATS ~ 5%
     */
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        if (n <= 1) return 0;

        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjMap.putIfAbsent(manager[i], new HashSet<>());
            adjMap.get(manager[i]).add(i);
        }

        int maxTimeNeeded = 0;

        //DFS approach
        maxTimeNeeded = dfs(adjMap, informTime, headID);

        //BFS approach
        // Queue<int[]> q = new LinkedList<>();
        // q.add(new int[]{headID, 0});

        // while (!q.isEmpty()) {
        //     int[] el = q.poll();
        //     maxTimeNeeded = Math.max(maxTimeNeeded, el[1]);
        //     int man = el[0];
        //     int delay = informTime[man];

        //     for (int ch : adjMap.getOrDefault(man, new HashSet<>())) {
        //         q.add(new int[]{ch, el[1] + delay});
        //     }
        // }

        return maxTimeNeeded;
    }

    private int dfs(Map<Integer, Set<Integer>> adjMap, int[] informTime, int head) {
        int maxTime = 0;
        for (int child : adjMap.getOrDefault(head, new HashSet<>())) {
            maxTime = Math.max(maxTime, dfs(adjMap, informTime, child));
        }

        maxTime += informTime[head];

        return maxTime;
    }
}
