package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://www.techiedelight.com/find-inorder-successor-given-key-bst
 *
 * Given a BST, find the inorder successor of a given key in it.
 * If the given key does not lie in the BST, then return the next greater node (if any) present in the BST.
 *
 * An inorder successor of a node in the BST is the next node in the inorder sequence.
 * For example, consider the following BST:
 */

public class Problem2_8_InOrderSuccessorInBST {
    public static void main(String[] args) {
        TreeNode node15 = new TreeNode(15);
        TreeNode node10 = new TreeNode(10);
        TreeNode node20 = new TreeNode(20);
        TreeNode node8 = new TreeNode(8);
        TreeNode node12 = new TreeNode(12);
        TreeNode node16 = new TreeNode(16);
        TreeNode node25 = new TreeNode(25);

        node15.left = node10;
        node15.right = node20;
        node10.left = node8;
        node10.right = node12;
        node20.left = node16;
        node20.right = node25;

        TreeNode root = node15;
        /**
         *         15
         *       /     \
         *    10         20
         *  /   \      /   \
         * 8     12  16     25
         */

        findInOrderSuccessor(root, 8);
        findInOrderSuccessor(root, 12);
        findInOrderSuccessor(root, 25);
        findInOrderSuccessor(root, 20);
        findInOrderSuccessor(root, 2);
    }

    /**
     * KREVSKY SOLUTION + logic of search from here https://www.techiedelight.com/find-inorder-successor-given-key-bst/
     * A node’s inorder successor is the node with the least value in its right subtree, i.e., its right subtree’s leftmost child.
     * If the right subtree of the node doesn’t exist, then the inorder successor is one of its ancestors.
     * To find which ancestors are the successor, we can move up the tree towards the root until we encounter a node that is left child of its parent.
     * If any such node is found, then inorder successor is its parent; otherwise, inorder successor does not exist for the node.
     * <p>
     * not optimal since we store path instead of save only one potential successor's value - see APPROACH 1 below
     *
     * time to solve ~ 50 mins
     * time complexity ~ O(n), where n - number of nodes
     * space complexity ~ O(logn) - to store path
     */
    public static int findInOrderSuccessor(TreeNode root, int key) {
        Deque<TreeNode> path = new LinkedList<>();
        boolean isFound = preOrderTraversalKREV(root, key, path);
        if (!isFound) {
            //return the greatest node in BST
            while (root.right != null) {
                root = root.right;
            }
            System.out.println(key + " is not in BST. So return the largest value of the tree: " + root.val);
            return root.val;
        }

        TreeNode node = path.pollFirst();

        TreeNode successor = null;
        //try to find the most left node in right subtree of node
        if (node.right != null) {
            successor = node.right;
            while (successor.left != null) {
                successor = successor.left;
            }
        } else {
            //go to the root until we find the first parent that has left child. and return this parent's value
            while (!path.isEmpty()) {
                TreeNode tempParent = path.pollFirst();
                if (tempParent.left == node) {
                    successor = tempParent;
                    break;
                } else {
                    node = tempParent;
                }
            }
        }

        if (successor == null) {
            System.out.println("The inorder successor of 25 does not exist");
            return Integer.MIN_VALUE;
        } else {
            System.out.println("The inorder successor of " + key + " is " + successor.val);
            return successor.val;
        }
    }

    private static boolean preOrderTraversalKREV(TreeNode root, int key, Deque<TreeNode> path) {
        if (root != null) {
            if (root.val == key) {
                path.offerLast(root);
                return true;
            }

            boolean leftFound = preOrderTraversalKREV(root.left, key, path);
            if (leftFound) {
                path.offerLast(root);
                return true;
            }

            boolean rightFound = preOrderTraversalKREV(root.right, key, path);
            if (rightFound) {
                path.offerLast(root);
                return true;
            }
        }

        return false;
    }

    /**
     * APPROACH 1: recursion
     * https://www.techiedelight.com/find-inorder-successor-given-key-bst/
     * The idea is to search for the given node in the tree and update the successor to the current node before visiting its left subtree.
     * If the node is found in the BST, return the least value node in its right subtree,
     * and if the right subtree of the node doesn’t exist, then inorder successor is one of the ancestors of it,
     * which has already been updated while searching for the given key.
     */
    // Helper function to find minimum value node in a given BST
    public static TreeNode findMinimum(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    // Recursive function to find an inorder successor for the given key in the BST
    //set succ = null when the method is called for testing
    public static TreeNode findSuccessorRecursively(TreeNode root, TreeNode succ, int key) {
        // base case
        if (root == null) {
            return succ;
        }

        // if a node with the desired value is found, the successor is the minimum
        // value node in its right subtree (if any)
        if (root.val == key) {
            if (root.right != null) {
                return findMinimum(root.right);
            }
        }

        // if the given key is less than the root node, recur for the left subtree
        else if (key < root.val) {
            // update successor to the current node before recursing in the
            // left subtree
            succ = root;
            return findSuccessorRecursively(root.left, succ, key);
        }

        // if the given key is more than the root node, recur for the right subtree
        else {
            return findSuccessorRecursively(root.right, succ, key);
        }

        return succ;
    }

    /**
     * APPROACH 2: Iterative
     * https://www.techiedelight.com/find-inorder-successor-given-key-bst/
     * The same algorithm (as in APPROACH 1) can be easily implemented iteratively.
     */
    // Iterative function to find an inorder successor for the given key in the BST
    public static TreeNode findSuccessorIteratively(TreeNode root, int key) {
        // base case
        if (root == null) {
            return null;
        }

        TreeNode succ = null;

        while (true) {
            // if the given key is less than the root node, visit the left subtree
            if (key < root.val) {
                // update successor to the current node before visiting
                // left subtree
                succ = root;
                root = root.left;
            }
            // if the given key is more than the root node, visit the right subtree
            else if (key > root.val) {
                root = root.right;
            }
            // if a node with the desired value is found, the successor is the minimum
            // value node in its right subtree (if any)
            else {
                if (root.right != null) {
                    succ = findMinimum(root.right);
                }
                break;
            }

            // if the key doesn't exist in the binary tree, return next greater node
            if (root == null) {
                return succ;
            }
        }

        // return successor, if any
        return succ;
    }
}
