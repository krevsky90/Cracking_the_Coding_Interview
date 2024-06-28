package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://leetcode.com/problems/find-distance-in-a-binary-tree (medium) (blocked)
 * 1740. find-distance-in-a-binary-tree
 * #Company: Amazon
 * <p>
 * info: https://leetcode.ca/all/1740.html
 * <p>
 * Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
 * The distance between two nodes is the number of edges on the path from one to the other.
 * <p>
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
 * Output: 3
 * Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
 * <p>
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
 * Output: 2
 * Explanation: There are 2 edges between 5 and 7: 5-2-7.
 * <p>
 * Example 3:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
 * Output: 0
 * Explanation: The distance between a node and itself is 0.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 10^4].
 * 0 <= Node.val <= 10^9
 * All Node.val are unique.
 * p and q are values in the tree.
 */
public class FindDistanceInBT {
    public static void main(String[] args) {
        FindDistanceInBT obj = new FindDistanceInBT();
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n2.left = n7;
        n2.right = n4;
        n1.left = n0;
        n1.right = n8;
        TreeNode root = n3;

        //example 1:
        int p1 = 5;
        int q1 = 0;
        System.out.println(obj.findDistance(root, p1, q1)); //expected 3

        //example 2:
        int p2 = 5;
        int q2 = 7;
        System.out.println(obj.findDistance(root, p2, q2)); //expected 2
    }

    /**
     * KREVSKY SOLUTION:
     * idea: use Lowest Common Ancestor
     * + levels
     * DOES NOT WORK! need to calculate distance! otherwise lcaLevel is incorrect and always = 0!
     */
    private int pLevel = 0;
    private int qLevel = 0;
    private int lcaLevel = 0;

    public int findDistanceInBT(TreeNode root, int p, int q) {
        if (p == q) return 0;

        lca(root, p, q, 0);

        System.out.println("lcaLevel = " + lcaLevel);
        System.out.println("pLevel = " + pLevel);
        System.out.println("qLevel = " + qLevel);
        return (pLevel - lcaLevel) + (qLevel - lcaLevel);
    }

    private int lca(TreeNode root, int p, int q, int rootLevel) {
        if (root == null) return 0;

        int numNodes = 0;
        if (root.val == q) {
            lcaLevel = rootLevel;
            qLevel = rootLevel;
            numNodes++;
        } else if (root.val == p) {
            lcaLevel = rootLevel;
            pLevel = rootLevel;
            numNodes++;
        }

        numNodes += lca(root.left, p, q, rootLevel + 1);
        numNodes += lca(root.right, p, q, rootLevel + 1);

        if (numNodes >= 2) {
            lcaLevel = rootLevel;
        }

        return numNodes;
    }

    /**
     * https://leetcode.ca/2021-03-23-1740-Find-Distance-in-a-Binary-Tree/
     * 1) find LCA
     * 2) calculate distances traversing the tree starting from ancestor
     */
    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) return 0;

        TreeNode ancestor = lowestCommonAncestor(root, p, q);
        return getDistance(ancestor, p) + getDistance(ancestor, q);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, int p, int q) {
        if (root == null)
            return null;
        if (root.val == p || root.val == q)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        return left == null ? right : left;
    }

    public int getDistance(TreeNode node, int val) {
        if (node == null)
            return -1;
        if (node.val == val)
            return 0;
        int leftDistance = getDistance(node.left, val);
        int rightDistance = getDistance(node.right, val);
        int subDistance = Math.max(leftDistance, rightDistance);
        return subDistance >= 0 ? subDistance + 1 : -1;
    }
}