package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a5c5fb923d029fb1c4ab8f
 * OR similar to
 * 210. Course Schedule II
 * https://leetcode.com/problems/course-schedule-ii
 *
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates
 * that you must take course bi first if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses.
 * If there are many valid answers, return any of them.
 * If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 *
 * Example 2:
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take.
 * To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 *
 * Constraints:
 * 1 <= numCourses <= 2000
 * 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 * prerequisites[i].length == 2
 * 0 <= ai, bi < numCourses
 * ai != bi
 * All the pairs [ai, bi] are distinct.
 *
 */
public class TasksSchedulingOrder {
    /**
     * NOTE: we will solve leetcode's version!
     */
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        int[] res = new TasksSchedulingOrder().findOrder(numCourses, prerequisites);
        System.out.println("");

    }
    /**
     * KREVSKY SOLUTION #1: standardized according to the other solutions from this topic
     * i.e. almost copy of TasksScheduling # topologicalSortBFS,
     * except:
     * 1) edge[0] and edge[1] are swapped in the condition of the problem
     * 2) return empty array is it is impossible to build schedule based on this data
     *
     * BEATS 14% time
     * BEATS 82% space
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. Initialization
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> vertexToCountMap = new HashMap<>();   //vertex to amount of incoming edges
        for (int i = 0; i < numCourses; i++) {
            //it is more simple to put empty list rather then struggle with NPE for nodes that don't have outgoing edges
            graph.put(i, new ArrayList<>());
            //it is necessary since source nodes will not get 0 value in any other place!
            vertexToCountMap.put(i, 0);
        }

        // 2. Build the graph and find in-degrees of all vertices
        for (int[] edge : prerequisites) {
            //fill graph
            graph.get(edge[1]).add(edge[0]);
            //fill incoming edge map
            vertexToCountMap.put(edge[0], vertexToCountMap.getOrDefault(edge[0], 0) + 1);
        }

        // 3. Find all initial sources
        Queue<Integer> sourceQueue = new LinkedList<>();
        for (Integer key : vertexToCountMap.keySet()) {
            if (vertexToCountMap.get(key) == 0) {
                sourceQueue.add(key);
            }
        }

        List<Integer> result = new ArrayList<>();
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

        if(result.size() != numCourses) {
            return new int[0];
        } else {
            int[] resultArr = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                resultArr[i] = result.get(i);
            }
//          OR  return result.stream().mapToInt(Integer::intValue).toArray();
            return resultArr;
        }
    }

    /**
     * KREVSKY SOLUTION #2:
     * similar to above BUT without Queue (use List zeroTasks instead)
     *
     * time to solve ~ 50 mins
     *
     * a lot of attempts:
     * - incorrect while condition (tried to use courseToChildren.isEmpty()
     * - forget "if (affectedChildren == null) continue; " to avoid NPE
     *
     * BEATS 16% time
     * BEATS 55% space
     */
    public int[] findOrder2(int numCourses, int[][] prerequisites) {
        int[] inCount= new int[numCourses];
        Map<Integer, List<Integer>> courseToChildren = new HashMap<>();
        for (int[] pre : prerequisites) {
            inCount[pre[0]]++;
            List<Integer> children = courseToChildren.getOrDefault(pre[1], new ArrayList<>());
            children.add(pre[0]);
            courseToChildren.put(pre[1], children);
        }

        int[] result = new int[numCourses];
        int idx = 0;

        while (true) {
            List<Integer> zeroTasks = new ArrayList<>();
            for (int i = 0; i < inCount.length; i++) {
                if (inCount[i] == 0) {
                    zeroTasks.add(i);
                    inCount[i] = -1;    //mark as included in the schedule
                    result[idx] = i;
                    idx++;
                }
            }

            //stop condition:
            if (zeroTasks.isEmpty()) {
                if (idx != numCourses) {
                    //we can't create schedule => return new int[0];
                    return new int[0];
                } else {
                    break;
                }
            }

            //exclude influence of zeroTasks on courseToChildren and inCount
            for (Integer task : zeroTasks) {
                List<Integer> affectedChildren = courseToChildren.get(task);
                if (affectedChildren == null) continue;   //to avoid NPE for elements that don't have children

                for (Integer child : affectedChildren) {
                    inCount[child]--;
                }

                courseToChildren.remove(task);
            }
        }

        return result;
    }
}