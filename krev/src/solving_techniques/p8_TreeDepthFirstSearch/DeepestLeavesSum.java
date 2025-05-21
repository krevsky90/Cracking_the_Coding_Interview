package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1302. Deepest Leaves Sum (medium)
 * https://leetcode.com/problems/deepest-leaves-sum/
 * <p>
 * #Company (21.05.2025): 0 - 3 months Myntra 2 6 months ago Amazon 6 Google 5
 * <p>
 * Given the root of a binary tree, return the sum of values of its deepest leaves.
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
 * Output: 15
 * <p>
 * Example 2:
 * Input: root = [6,7,8,2,7,1,3,9,null,1,4,null,null,null,5]
 * Output: 19
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 104].
 * 1 <= Node.val <= 100
 */
public class DeepestLeavesSum {
    /**
     * KREVSKY BSF solution
     * time to solve ~ 8 mins
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 1 attempt
     * BEATS ~ 58%
     */
    public int deepestLeavesSumBFS(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int sum = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                sum += node.val;
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        DeepestLeavesSum obj = new DeepestLeavesSum();
        obj.deepestLeavesSumDFSStack(n1);
    }

    /**
     * Official DFS solution using Stack
     * <p>
     * idea:
     * keep maxDepth = depth
     * deepestSum
     * Deque (or Stack) of Pairs: TreeNode -> its depth
     * <p>
     * Use pre-order traversal of binary tree
     * and in case of leave update depth and deepestSum in accordance with curDepth and depth's value
     */
    public int deepestLeavesSumDFSStack(TreeNode root) {
        int deepestSum = 0, depth = 0, currDepth;
        Deque<Pair<TreeNode, Integer>> stack = new ArrayDeque();
        stack.push(new Pair(root, 0));

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> p = stack.pop();
            root = p.getKey();
            currDepth = p.getValue();
            System.out.println(root.val);

            if (root.left == null && root.right == null) {
                // if this leaf is the deepest one seen so far
                if (depth < currDepth) {
                    deepestSum = root.val;      // start new sum
                    depth = currDepth;          // note new depth
                } else if (depth == currDepth) {
                    // if there were already leaves at this depth
                    deepestSum += root.val;     // update existing sum
                }
            } else {
                if (root.right != null) {
                    stack.push(new Pair(root.right, currDepth + 1));
                }
                if (root.left != null) {
                    stack.push(new Pair(root.left, currDepth + 1));
                }
            }
        }
        return deepestSum;
    }

    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
