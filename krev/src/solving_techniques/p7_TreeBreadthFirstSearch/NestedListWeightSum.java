package solving_techniques.p7_TreeBreadthFirstSearch;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 339 Nested List Weight Sum (medium) (locked)
 * https://leetcode.com/problems/nested-list-weight-sum
 *
 * #Company (18.02.2025): 0 - 3 months Meta 76 LinkedIn 3 0 - 6 months Amazon 3 6 months ago Apple 3 eBay 2 Nextdoor 2
 * OR
 * https://leetcode.ca/all/339.html
 * <p>
 * #Company: Amazon Cloudera Facebook LinkedIn Uber
 * <p>
 * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
 * <p>
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * <p>
 * Example 1:
 * Input: [[1,1],2,[1,1]]
 * Output: 10
 * Explanation: Four 1's at depth 2, one 2 at depth 1.
 * <p>
 * Example 2:
 * Input: [1,[4,[6]]]
 * Output: 27
 * Explanation: One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27.
 */
public class NestedListWeightSum {
    interface NestedInteger {
        // Constructor initializes an empty nested list.
//        NestedInteger();

        // Constructor initializes a single integer.
//        public NestedInteger(int value);

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // Set this NestedInteger to hold a single integer.
        public void setInteger(int value);

        // Set this NestedInteger to hold a nested list and adds a nested integer to it.
        public void add(NestedInteger ni);

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    /**
     * KREVSKY SOLUTION #1:
     * use BFS
     * info:
     * https://www.youtube.com/watch?v=jR2UC4K-q2U&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=55
     *
     * time to implement ~ 5 mins
     * time ~ O(n)
     * space ~ O(n)
     *
     * 1 attempt
     *
     * */
    public int depthSumBfs(List<NestedInteger> nestedList) {
        Queue<NestedInteger> q = new LinkedList<>();
        q.addAll(nestedList);

        int level = 1;
        int result = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                NestedInteger ni = q.poll();
                if (ni.isInteger()) {
                    result += ni.getInteger() * level;
                } else {
                    q.addAll(ni.getList());
                }
            }
            level++;
        }

        return result;
    }

    /**
     * KREVSKY SOLUTION #2:
     * use DFS
     *
     * time to solve ~ 10 mins
     * time ~ O(n)
     * space ~ O(n) including recursion stack's memory
     *
     * 1 attempt
     */
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSumDfs(nestedList, 1);
    }

    private int depthSumDfs(List<NestedInteger> nestedList, int level) {
        int result = 0;
        for (NestedInteger ni : nestedList) {
            if (ni.isInteger()) {
                result += ni.getInteger() * level;
            } else {
                result += depthSumDfs(ni.getList(), level + 1);
            }
        }

        return result;
    }

}
