package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6399dbf40d7254be596612d2
 * OR
 * https://www.geeksforgeeks.org/check-root-leaf-path-given-sequence/
 * <p>
 * Problem Statement
 * Given a binary tree and a number sequence, find if the sequence is present as a root-to-leaf path in the given tree.
 * <p>
 * Examples:
 * Input : arr[] = {5, 2, 4, 8} for above tree
 * Output: "Path Exist"
 * <p>
 * Input :  arr[] = {5, 3, 4, 9} for above tree
 * Output: "Path does not Exist"
 */
public class PathWithGivenSequence {
    /**
     * KREVSKY SOLUTION
     *
     * !!! ATTENTION !!! it is NOT tested by smth like leetcode!!!
     * time to solve ~ 10 mins
     * time to debug/test ~ 6 mins
     * time ~ O(n)
     * space ~ O(h), where h - height of BT
     * 2 attempts: forget "IF index == n THEN return false"
     */
    public static boolean existPath(TreeNode root, int arr[]) {
        if (root == null && (arr == null || arr.length == 0)) return true;

        return existPath(root, arr, arr.length, 0);
    }

    /**
     *
     * @param root
     * @param arr
     * @param n     - length of arr
     * @param index - index of element of arr that should be compared to root.val
     * @return
     */
    public static boolean existPath(TreeNode root, int arr[], int n, int index) {
        if (root == null || index == n) return false;

        if (root.val != arr[index]) return false;

        //if root == leave and index is the last element of arr
        //it is supposed that root.val = arr[index]. Otherwise we would quite in the previous if-block
        if (root.left == null && root.right == null && index == n - 1) {
            return true;
        }

        boolean leftExist = existPath(root.left, arr, n, index + 1);
        boolean rightExist = existPath(root.right, arr, n, index + 1);
        return leftExist || rightExist;
    }
}