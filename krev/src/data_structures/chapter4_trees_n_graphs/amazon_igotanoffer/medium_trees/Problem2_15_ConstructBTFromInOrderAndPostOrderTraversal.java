package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/description/
 *
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
 * construct and return the binary tree.
 *
 * Example 1:
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * Output: [3,9,20,null,null,15,7]
 *
 * Example 2:
 * Input: inorder = [-1], postorder = [-1]
 * Output: [-1]
 *
 */
public class Problem2_15_ConstructBTFromInOrderAndPostOrderTraversal {
    // 9 3 15 20 7 - in order
    // 9 15 7 20 3 - post order

    /**
     * not SOlVED by myself, forget the idea #3
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length || inorder.length == 0) {
            return null;
        }

        //idea #1: the last element of post order is ROOT
        //idea #2: all the elements of inorder arr that left from found ROOT - belongs to left subtree and the same with right elements of inorder arr
        //idea #3: length of subarrays of inorder and postorder arrays that are propagated to buildTreeRecursively. should be the same!
        //that's why we set postLeft and postLeft + leftLength - 1
        return buildTreeRecursively(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    // 15 20 7 - in order
    // 15 7 20 - post order
    private TreeNode buildTreeRecursively(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight) {
        if (inLeft > inRight) {
            return null;
        }

        if (inLeft == inRight) {
            //the only element
            return new TreeNode(inorder[inLeft]);
        } else {
            //find the root
            int rootVal = postorder[postRight];
            TreeNode root = new TreeNode(rootVal);  //20
            int rootIndex = findRootIndex(inorder, inLeft, inRight, rootVal);   //1
            int leftLength = rootIndex - inLeft;    //1 - 0 = 1
            TreeNode leftNode = buildTreeRecursively(inorder, inLeft, rootIndex - 1, postorder, postLeft, postLeft + leftLength - 1);   //(..,0, 0, .., 0, 0)
            root.left = leftNode;

            //как-то надо зачеркнуть leftNode в postorder, к-ый передаем?? пока что передал postLeft (а не postleft+1, например)
            TreeNode rightNode = buildTreeRecursively(inorder, rootIndex + 1, inRight, postorder, postLeft + leftLength, postRight - 1);   //..,2,2,..,1,1
            root.right = rightNode;

            return root;
        }
    }

    //time ~ O(n)
    private int findRootIndex(int[] arr, int left, int right, int element) {
        for (int i = left; i <= right; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;  //error
    }

}
