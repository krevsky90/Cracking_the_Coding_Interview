package solving_techniques.p7_TreeBreadthFirstSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal
 * <p>
 * Given an n-ary tree, return the level order traversal of its nodes' values.
 * <p>
 * Nary-Tree input serialization is represented in their level order traversal,
 * each group of children is separated by the null value (See examples).
 * <p>
 * Example 1:
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output: [[1],[3,2,4],[5,6]]
 *
 * Constraints:
 * The height of the n-ary tree is less than or equal to 1000
 * The total number of nodes is between [0, 104^]
 */
public class NaryTreeLevelOrderTraversal {
    /**
     * KREVSKY SOLUTION
     * time to solve ~ 10 mins
     *
     * time ~ O(n), where n - amount of nodes
     * space ~ O(n) - worst case
     *
     * 2 attempts:
     * - did not read that there can be 0 nodes => missed root == null validation
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> subResult = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node tempNode = q.poll();
                subResult.add(tempNode.val);
                if (tempNode.children != null) {
                    for (Node n : tempNode.children) {
                        q.add(n);
                    }
                }
            }
            result.add(subResult);
        }

        return result;
    }
}

// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
