package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/house-robber-iii/solutions/79330/Step-by-step-tackling-of-the-problem/
 *
 * NOT solved by myself
 */
public class Problem2_12_HouseRobber3 {
    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        node3.left = node2;
        node2.left = node4;
        node4.left = node7;

        int res = new Problem2_12_HouseRobber3().rob1(node3);
        System.out.println(res);


    }

    /**
     * Termination condition: when do we know the answer to rob(root) without any calculation?
     * Of course when the tree is empty ---- we've got nothing to rob so the amount of money is zero.
     *
     * Recurrence relation: i.e., how to get rob(root) from rob(root.left), rob(root.right), ... etc.
     * From the point of view of the tree root, there are only two scenarios at the end: root is robbed or is not.
     * If it is, due to the constraint that "we cannot rob any two directly-linked houses",
     * the next level of subtrees that are available would be the four "grandchild-subtrees" (root.left.left, root.left.right, root.right.left, root.right.right).
     * However if root is not robbed, the next level of available subtrees would just be the two "child-subtrees" (root.left, root.right).
     * We only need to choose the scenario which yields the larger amount of money.
     */
    public int rob1(TreeNode root) {
        if (root == null) return 0;

        int val = 0;

        if (root.left != null) {
            val += rob1(root.left.left) + rob1(root.left.right);
        }

        if (root.right != null) {
            val += rob1(root.right.left) + rob1(root.right.right);
        }

        return Math.max(val + root.val, rob1(root.left) + rob1(root.right));
    }

    /**
     * optimized rob1 with saving of the previous result (in HashMap)
     */
    public int rob1Cached(TreeNode root) {
        return rob1Cached(root, new HashMap<>());
    }

    public int rob1Cached(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int val = 0;

        if (root.left != null) {
            val += rob1Cached(root.left.left, map) + rob1Cached(root.left.right, map);
        }

        if (root.right != null) {
            val += rob1Cached(root.right.left, map) + rob1Cached(root.right.right, map);
        }

        int finalVal = Math.max(val + root.val, rob1Cached(root.left, map) + rob1Cached(root.right, map));
        map.put(root, finalVal);
        return finalVal;
    }

    /**
     * If we were able to maintain the information about the two scenarios for each tree root, let's see how it plays out.
     * Redefine rob(root) as a new function which will return an array of two elements,
     * the first element of which denotes the maximum amount of money that can be robbed if root is not robbed,
     * while the second element signifies the maximum amount of money robbed if it is robbed.
     *
     * Let's relate rob(root) to rob(root.left) and rob(root.right)..., etc.
     * For the 1st element of rob(root), we only need to sum up the larger elements of rob(root.left) and rob(root.right),
     * respectively, since root is not robbed and we are free to rob its left and right subtrees.
     * For the 2nd element of rob(root), however, we only need to add up the 1st elements of rob(root.left) and rob(root.right),
     * respectively, plus the value robbed from root itself, since in this case it's guaranteed that we cannot rob the nodes of root.left and root.right.
     *
     * As you can see, by keeping track of the information of both scenarios,
     * we decoupled the subproblems and the solution essentially boiled down to a greedy one.
     */
    public int rob2(TreeNode root) {
        int[] res = robSub2(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub2(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub2(root.left);
        int[] right = robSub2(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
