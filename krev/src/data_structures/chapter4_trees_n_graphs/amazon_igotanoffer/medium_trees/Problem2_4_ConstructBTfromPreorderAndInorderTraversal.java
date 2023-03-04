package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/
 * https://www.youtube.com/watch?v=ihj4IQGZ2zc
 *
 * Given two integer arrays preorder and inorder
 * where preorder is the preorder traversal of a binary tree
 * and inorder is the inorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Example 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 */
public class Problem2_4_ConstructBTfromPreorderAndInorderTraversal {
    /**
     * not solved by myself. Implemented idea from https://www.youtube.com/watch?v=ihj4IQGZ2zc
     * 15 mins + 2 attempts
     *
     * The basic idea is here:
     * Say we have 2 arrays, PRE and IN.
     * 1) Preorder traversing implies that PRE[0] is the root node.
     * Then we can find this PRE[0] in IN, say it's IN[5].
     * 2) Now we know that IN[5] is root, so we know that subarray = IN[0] - IN[4] is on the left side, subarray = IN[6] to the end is on the right side.
     * 3) subarrays of PRE should be the same (reg context and length) as subarrays of IN.
     * Recursively doing this on subarrays, we can build a tree out of it
     */
    public TreeNode buildTreeKREV(int[] preorder, int[] inorder) {
        return buildTreeKREV(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
    //preL = 0
    //preR = 5
    //inL = 0
    //inR = 5
    //val = 3
    //inorderInd = 1
    //leftLength = 1

    //leftNode
    //preL = 1
    //preR = 2
    //inL = 0
    //inR = 1
    //val = 9
    //inorderInd = findIndexByValue(inorder, 0, 1, 9) = 0
    //leftLength = 0

    //rightNode
    //preL = 2
    //preR = 5
    //inL = 2
    //inR = 5
    //val = 20
    //inorderInd = findIndexByValue(inorder, 2, 5, 20) = 3
    //leftLength = 0
    public TreeNode buildTreeKREV(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR) {
        //condition to stop recursion
        if (preR - preL <= 0) return null;

        int val = preorder[preL];
        TreeNode node = new TreeNode(val);

        //condition to stop recursion
        // if (preR - preL == 1) {
        //     return node;
        // }

        int inorderInd = findIndexByValue(inorder, inL, inR, val);
        int leftLength = inorderInd - inL;
//        int rightLength = inR - inorderInd - 1;

        TreeNode leftNode = buildTreeKREV(preorder, preL + 1, preL + 1 + leftLength, inorder, inL, inL + leftLength);
        TreeNode rightNode = buildTreeKREV(preorder, preL + 1 + leftLength, preR, inorder, inorderInd + 1, inR);

        node.left = leftNode;
        node.right = rightNode;

        return node;
    }

    private int findIndexByValue(int[] arr, int l, int r, int val) {
        for (int i = l; i < r; i++) {
            if (arr[i] == val) return i;
        }
        return -100500;  //error
    }

    /**
     * Someone's solution
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return helper(0, 0, inorder.length - 1, preorder, inorder);
    }

    public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
        if (preStart > preorder.length - 1 || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0; // Index of current root in inorder
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == root.val) {
                inIndex = i;
            }
        }
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
        return root;
    }
}
