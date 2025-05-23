package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * 333. Largest BST Subtree (medium) (locked)
 * https://leetcode.com/problems/largest-bst-subtree
 * OR
 * https://leetcode.ca/2016-10-28-333-Largest-BST-Subtree/
 * <p>
 * #Company: 0 - 3 months Meta 3 6 months ago TikTok 6 Microsoft 3
 * <p>
 * Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST),
 * where the largest means subtree has the largest number of nodes.
 * <p>
 * A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
 * <p>
 * The left subtree values are less than the value of their parent (root) node's value.
 * The right subtree values are greater than the value of their parent (root) node's value.
 * Note: A subtree must include all of its descendants.
 * <p>
 * Example 1:
 * Input: root = [10,5,15,1,8,null,7]
 * Output: 3
 * Explanation: The Largest BST Subtree in this case is the highlighted one.
 * The return value is the subtree's size, which is 3.
 * <p>
 * Example 2:
 * Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
 * Output: 2
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [0, 10^4].
 * -10^4 <= Node.val <= 10^4
 * <p>
 * Follow up: Can you figure out ways to solve it with O(n) time complexity?
 */
public class LargestBstSubtree {
    public static void main(String[] args) {
        TreeNode n10 = new TreeNode(10);
        TreeNode n5 = new TreeNode(5);
        TreeNode n15 = new TreeNode(15);
        TreeNode n1 = new TreeNode(1);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        n10.left = n5;
        n10.right = n15;
        n5.left = n1;
        n5.right = n8;
        n15.right = n7;

        LargestBstSubtree obj = new LargestBstSubtree();
        int res = obj.largestBSTSubtree(n10);
        System.out.println(res);
    }

    private int result = 0;

    /**
     * NOT SOLVED by myself properly
     * problem is similar to https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
     * <p>
     * idea: for each node we need to return:
     * minValue - min node in sub-tree
     * maxValue - max node in sub-tree
     * sum - sum if its sub-tree
     * <p>
     * criterion of BST subtree is:
     * left subTree is BST & right subtree isBST && maxValue of left subtree < node.val < minValue of right subtree
     * <p>
     * if current subtree is BST, then update result variable
     */
    public int largestBSTSubtree(TreeNode root) {
        helperBST(root);
        return result;
    }

    //return int[3] where [0] - min value in subtree, [1] - max value in subtree, [2] - amount of nodes of subtree
    private int[] helperBST(TreeNode root) {
        if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};

        int[] left = helperBST(root.left);
        int[] right = helperBST(root.right);

        //check if 'left subtree + root + right subtree' form BST
        if (left[1] < root.val && root.val < right[0]) {
            int sum = left[2] + 1 + right[2];
            result = Math.max(result, sum);
            //if left is null node => left[0] = Integer.MAX_VALUE => leftMin = min(MAX_VALUE, root.val) = root.val
            //if left is NOT null, then, obviously, left[0] < root.val => return left[0]
            int leftMin = Math.min(left[0], root.val);
            //if right is null node => right[1] = Integer.MIN_VALUE => rightMax = max(MIN_VALUE, root.val) = root.val
            //if right is NOT null, then, obviously, root.val < right[1] => return right[1]
            int rightMax = Math.max(right[1], root.val);
            return new int[]{leftMin, rightMax, sum};
        } else {
            //this is not BST, return array with bounds that won't leet bigger subtree become BST
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};  //no matter what value is in [2]
        }
    }
}