package solving_techniques.p20_TopologicalSort;

import java.util.*;

/**
 * 1136. Parallel Courses (hard) (blocked)
 * https://leetcode.com/problems/parallel-courses
 * OR
 * https://leetcode.ca/all/1136.html
 *
 * #Company: Google Uber Facebook
 *
 * There are N courses, labelled from 1 to N.
 * We are given relations[i] = [X, Y], representing a prerequisite relationship between course X and course Y:
 *      course X has to be studied before course Y.
 * In one semester you can study any number of courses as long as you have studied all the prerequisites for the course you are studying.
 * Return the minimum number of semesters needed to study all courses.
 * If there is no way to study all the courses, return -1.
 *
 * Example 1:
 * Input: N = 3, relations = [[1,3],[2,3]]
 * Output: 2
 * Explanation:
 * In the first semester, courses 1 and 2 are studied. In the second semester, course 3 is studied.
 *
 * Example 2:
 * Input: N = 3, relations = [[1,2],[2,3],[3,1]]
 * Output: -1
 * Explanation:
 * No course can be studied because they depend on each other.
 *
 * Note:
 * 1 <= N <= 5000
 * 1 <= relations.length <= 5000
 * relations[i][0] != relations[i][1]
 * There are no repeated relations in the input.
 */
public class ParallelCourses {
    public static void main(String[] args) {
        int n1 = 6;
        int[][] relations1 = {{1,3},{2,4},{3,4},{3,5},{4,5},{4,6},{5,6}};

        int n2 = 3;
        int[][] relations2 = {{1,3},{2,3}};

        int n3 = 3;
        int[][] relations3 = {{1,2},{2,3},{3,1}};

        ParallelCourses obj = new ParallelCourses();
        System.out.println(obj.minimumSemesters(n1, relations1));   //expected 5
        System.out.println(obj.minimumSemesters(n2, relations2));   //expected 2
        System.out.println(obj.minimumSemesters(n3, relations3));   //expected -1
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 18 mins
     * idea: Topological sort + track depth (i.e. result) until we listed all vertices
     *
     * time ~ O(n + relations.length)
     * space ~ O(n + relations.length)
     *
     * 2 attempts:
     * - did not handle case when we need to return -1;
     */
    public int minimumSemesters(int n, int[][] relations) {
        int[] incomingEgdesNum = new int[n + 1];
        incomingEgdesNum[0] = Integer.MAX_VALUE;

        Map<Integer, List<Integer>> outcomingEdgesMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            outcomingEdgesMap.put(i, new ArrayList<>());
        }

        for (int i = 0; i < relations.length; i++) {
            incomingEgdesNum[relations[i][1]]++;
            outcomingEdgesMap.get(relations[i][0]).add(relations[i][1]);
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < incomingEgdesNum.length; i++) {
            if (incomingEgdesNum[i] == 0) {
                q.add(i);
            }
        }

        int result = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int vertex = q.poll();
                List<Integer> neighbours = outcomingEdgesMap.get(vertex);
                for (int neighbour : neighbours) {
                    incomingEgdesNum[neighbour]--;
                    if (incomingEgdesNum[neighbour] == 0) {
                        q.add(neighbour);
                    }
                }
            }
            result++;
        }


        return result == 0 ? -1 : result;
    }
}
