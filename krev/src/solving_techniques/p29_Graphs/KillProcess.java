package solving_techniques.p29_Graphs;

import java.util.*;

/**
 * 582. Kill Process (medium)
 * https://leetcode.com/problems/kill-process
 * <p>
 * #Company (24.01.2025) 0 - 6 months Oracle 3 6 months ago Amazon 4 Bloomberg 2
 * <p>
 * You have n processes forming a rooted tree structure.
 * You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 * <p>
 * Each process has only one parent process but may have multiple children processes.
 * Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 * <p>
 * When a process is killed, all of its children processes will also be killed.
 * <p>
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed.
 * You may return the answer in any order.
 * <p>
 * Example 1:
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 * <p>
 * Example 2:
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 * <p>
 * Constraints:
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 104
 * 1 <= pid[i] <= 5 * 104
 * 0 <= ppid[i] <= 5 * 104
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 */
public class KillProcess {
    /**
     * KREVSKY SOLUTION:
     * idea: adjMap + BFS from the root
     * <p>
     * time to solve ~ 23 mins
     * <p>
     * BEATS ~ 5%
     * <p>
     * 2 attempts:
     * - forgot 'visited' set
     */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();
        int n = pid.size();
        int root = -1;
        for (int i = 0; i < n; i++) {
            adjMap.putIfAbsent(pid.get(i), new HashSet<>());
            if (ppid.get(i) == 0) {
                root = pid.get(i);
            } else {
                adjMap.putIfAbsent(ppid.get(i), new HashSet<>());
                adjMap.get(pid.get(i)).add(ppid.get(i));
                adjMap.get(ppid.get(i)).add(pid.get(i));
            }
        }

        //bfs
        Set<Integer> visited = new HashSet<>();
        Queue<int[]> q = new LinkedList<>();    //[0] - vertex, [1] - 0 - not removed, 1 - to be removed
        int toRemove = root == kill ? 1 : 0;
        q.add(new int[]{root, toRemove});
        visited.add(root);

        List<Integer> res = new ArrayList<>();

        while (!q.isEmpty()) {
            int[] el = q.poll();

            if (el[1] == 1) {
                res.add(el[0]);
            }

            for (int v : adjMap.getOrDefault(el[0], new HashSet<>())) {
                if (!visited.contains(v)) {
                    q.add(new int[]{v, el[1] == 1 || v == kill ? 1 : 0});
                    visited.add(v);
                }
            }
        }

        return res;
    }

    /**
     * KREVSKY optimized = Official solution #4
     * optimization:
     * 1) keep mapping parent -> list of children
     * 2) use BFS starting from kill node
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * BEATS ~ 18%
     */
    public List<Integer> killProcess2(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, Set<Integer>> adjMap = new HashMap<>();    // parent to list of children map
        int n = pid.size();

        for (int i = 0; i < n; i++) {
            if (ppid.get(i) > 0) {
                adjMap.putIfAbsent(ppid.get(i), new HashSet<>());
                adjMap.get(ppid.get(i)).add(pid.get(i));
            }
        }

        //bfs
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(kill);

        while (!q.isEmpty()) {
            int el = q.poll();
            res.add(el);

            for (int v : adjMap.getOrDefault(el, new HashSet<>())) {
                q.add(v);
            }
        }

        return res;
    }
}
