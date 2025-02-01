package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * 1339. Maximum Product of Splitted Binary Tree (medium)
 * https://leetcode.com/problems/maximum-product-of-splitted-binary-tree
 * <p>
 * #Companies (1.02.2025): 6 months ago Microsoft 3 Amazon 3
 * <p>
 * Given the root of a binary tree, split the binary tree into two subtrees by removing one edge
 * such that the product of the sums of the subtrees is maximized.
 * <p>
 * Return the maximum product of the sums of the two subtrees. Since the answer may be too large, return it modulo 109 + 7.
 * <p>
 * Note that you need to maximize the answer before taking the mod and not after taking it.
 * <p>
 * Example 1:
 * Input: root = [1,2,3,4,5,6]
 * Output: 110
 * Explanation: Remove the red edge and get 2 binary trees with sum 11 and 10. Their product is 110 (11*10)
 * <p>
 * Example 2:
 * Input: root = [1,null,2,3,4,null,null,5,6]
 * Output: 90
 * Explanation: Remove the red edge and get 2 binary trees with sum 15 and 6.Their product is 90 (15*6)
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [2, 5 * 10^4].
 * 1 <= Node.val <= 104
 */
public class MaximumProductOfSplittedBT {
    public static void main(String[] args) {
        MaximumProductOfSplittedBT obj = new MaximumProductOfSplittedBT();

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
        n3.left = n6;

        int res = obj.maxProduct(n1);
        System.out.println(res);
    }

    /**
     * the same idea, BUT we DO NOT need to store any nodes! only sums of the nodes (without links to/from this node!)
     * info: https://www.youtube.com/watch?v=ZGmvcUtXKxU&list=PLUPSMCjQ-7oeenUxyrJFDFqd40PokIqdO&index=22
     * <p>
     * BEATS ~ 21%
     */
    public int maxProduct2(TreeNode root) {
        List<Long> sums = new ArrayList<>();
        long totalSum = dfs2(root, sums);

        long res = -1;
        for (long s : sums) {
            long tempProduct = s * (totalSum - s);
            if (res < tempProduct) res = tempProduct;
        }

        int modulo = 7 + (int) Math.pow(10, 9);
        return (int) (res % modulo);
    }

    private long dfs2(TreeNode root, List<Long> sums) {
        if (root == null) return 0;

        long sum = root.val;
        if (root.left != null) {
            sum += dfs2(root.left, sums);
        }

        if (root.right != null) {
            sum += dfs2(root.right, sums);
            ;
        }

        sums.add(sum);
        return sum;
    }


    /**
     * KREVSKY SOLUTION:
     * time to solve + debug ~ 20 + 15 ~ 35 mins
     * <p>
     * time ~ O(n)
     * space ~ O(n)
     * <p>
     * 3 attempts:
     * - forgot 'nodeToSum.put(root, sum);'
     * - forgot to call 'dfs(root, nodeToSum, edges);'
     * - incorrect usage of 10^9 (need to replaced with Math.pow) => changed to long modulo = (long) (7 + Math.pow(10, 9));
     * <p>
     * BEATS ~ 6%
     */
    public int maxProduct(TreeNode root) {
        Map<TreeNode, Integer> nodeToSum = new HashMap<>();
        List<List<TreeNode>> edges = new ArrayList<>();
        dfs(root, nodeToSum, edges);

        long res = -1;
        for (List<TreeNode> e : edges) {
            TreeNode n1 = e.get(0);
            TreeNode n2 = e.get(1);
            TreeNode smallestNode = nodeToSum.get(n1) > nodeToSum.get(n2) ? n2 : n1;
            long sumTree1 = nodeToSum.get(smallestNode);
            long sumTree2 = nodeToSum.get(root) - sumTree1;
            long tempProduct = sumTree1 * sumTree2;
            if (res < tempProduct) res = tempProduct;
        }

        int modulo = 7 + (int) Math.pow(10, 9);
        return (int) (res % modulo);
    }

    private int dfs(TreeNode root, Map<TreeNode, Integer> nodeToSum, List<List<TreeNode>> edges) {
        if (root == null) return 0;

        int sum = root.val;
        if (root.left != null) {
            sum += dfs(root.left, nodeToSum, edges);
            edges.add(Arrays.asList(root, root.left));
        }

        if (root.right != null) {
            sum += dfs(root.right, nodeToSum, edges);
            edges.add(Arrays.asList(root, root.right));
        }

        nodeToSum.put(root, sum);
        return sum;
    }
}
