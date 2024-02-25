package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 *
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a5c4a4fd87e19ef8c4df64
 * OR SIMILAR TO
 * 207. Course Schedule
 * https://leetcode.com/problems/course-schedule/
 *
 * will solve the problem from leetcode:
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 *
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
 * So it is impossible.
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= 5000
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * All the pairs prerequisites[i] are unique.
 */
public class TasksScheduling {
    /**
     * KREVSKY SOLUTION:
     * the idea and even code are the same as for src/solving_techniques/p20_TopologicalSort/TopologicalSort.java
     * BUT we add one validation and return its value:
     * return result.length == amount of vertices;
     *
     */

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return topologicalSortBFS(numCourses, prerequisites);
    }

    public static boolean topologicalSortBFS(int vertices, int [][] edges) {
        List<Integer> result = new ArrayList<>();

        // 1. Initialization
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> vertexToCountMap = new HashMap<>();   //vertex to amount of incoming edges
        for (int i = 0; i < vertices; i++) {
            //it is more simple to put empty list rather then struggle with NPE for nodes that don't have outgoing edges
            graph.put(i, new ArrayList<>());
            //it is necessary since source nodes will not get 0 value in any other place!
            vertexToCountMap.put(i, 0);
        }

        // 2. Build the graph and find in-degrees of all vertices
        for (int[] edge : edges) {
            //fill graph
            graph.get(edge[0]).add(edge[1]);
            //fill incoming edge map
            vertexToCountMap.put(edge[1], vertexToCountMap.getOrDefault(edge[1], 0) + 1);
        }

        // 3. Find all initial sources
        Queue<Integer> sourceQueue = new LinkedList<>();
        for (Integer key : vertexToCountMap.keySet()) {
            if (vertexToCountMap.get(key) == 0) {
                sourceQueue.add(key);
            }
        }

        // 4. Sort
        while (!sourceQueue.isEmpty()) {
            Integer tempSource = sourceQueue.poll();
            result.add(tempSource);
            List<Integer> children = graph.get(tempSource);
            //decrement counter of incoming edges for children of tempSource
            for (Integer child : children) {
                vertexToCountMap.put(child, vertexToCountMap.get(child) - 1);
                //check if child becomes source vertex after that
                if (vertexToCountMap.get(child) == 0) {
                    sourceQueue.add(child);
                }
            }
        }

        return result.size() == vertices;
    }
}
