package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 * <p>
 * You are given the root of a binary search tree (BST),
 * where the values of exactly two nodes of the tree were swapped by mistake.
 * Recover the tree without changing its structure.
 * <p>
 * Follow up: A solution using O(n) space is pretty straight-forward. Could you devise a constant O(1) space solution?
 */
public class Problem2_14_Recover_BST {
    private TreeNode first;
    private TreeNode second;
    private TreeNode prev;

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node1.left = node3;
        node3.right = node2;

        new Problem2_14_Recover_BST().recoverTreeMorris(node1);
    }

    /**
     * NOT SOLVED
     * https://leetcode.com/problems/recover-binary-search-tree/solutions/1962981/idea-inorder-traversal-easy-to-understand/
     * good explanation - https://www.youtube.com/watch?v=LR3K5XAWV5k
     * time complexity O(n)
     * space complexity O(n) (on average O(logN))
     * idea - inOrderTraversal of BST is sorted array!
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;

        inOrderTraversal(root);

        //swap the values, but not real nodes
        int tempVal = first.val;
        first.val = second.val;
        second.val = tempVal;
    }

    // 3 2 1
    //inOrderTraversal(1)
    //  inOrderTraversal(3)
    //      inOrderTraversal(null) - skip
    //      prev = 3
    //      inOrderTraversal(2)
    //          inOrderTraversal(null) - skip
    //          first = 3
    //          prev = 2
    //  second = 1
    //  prev = 1
    //  inOrderTraversal(null) - skip
    private void inOrderTraversal(TreeNode root) {
        if (root == null) return;

        inOrderTraversal(root.left);

        if (prev == null) {
            prev = root;
        } else {
            if (root.val < prev.val) {
                if (first == null) {
                    first = prev;
                }
                second = root;  //should not write } else { second = root; } since if the tree is 1 -> 2 (right node) then second = null, that is incorrect

                prev = root;
            } else {
                prev = root;
            }
        }

        inOrderTraversal(root.right);
    }

    //**************************************

    /**
     * Morris in order traversal
     * https://www.youtube.com/watch?v=wGXB9OWhPTg
     * time complexity O(n)
     * space complexity O(1) since we change tree in-place
     */
    public void morrisInOrderTraversal(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.println(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;    //create loop in the tree
                    current = current.left;
                } else { //i.e. predecessor.right = current, i.e. loop in the tree, we have already visited this node!
                    predecessor.right = null;   //remove loop in the tree
                    System.out.println(current.val);
                    current = current.right;
                }
            }
        }
    }

    /**
     * Optimized solution - based on Morris inOrder traversal
     * https://leetcode.com/problems/recover-binary-search-tree/solutions/32535/No-Fancy-Algorithm-just-Simple-and-Powerful-In-Order-Traversal/
     * (see the solution of davidtan1890 from Feb 11, 2015)
     * time complexity O(n)
     * space complexity O(1) !!
     *
     * the idea is to insert the code
     * <code>
     *     if (prevNode != null && prevNode.val > current.val) {
     *         errorNodes.add(prevNode);
     *         errorNodes.add(current);
     *     }
     *     prevNode = current;
     * </code>
     * into Morris inOrderTraversal instead of code for 'visiting' current node
     * <code>
     *     System.out.println(current.val);
     * </code>
     */
    public void recoverTreeMorris(TreeNode root) {
        if (root == null) return;

        TreeNode prevNode = null;
        List<TreeNode> errorNodes = new ArrayList<>(4);

        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
//                System.out.println(current.val);
                if (prevNode != null && prevNode.val > current.val) {
                    errorNodes.add(prevNode);
                    errorNodes.add(current);
                }
                prevNode = current;

                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    predecessor.right = current;    //create loop in the tree
                    current = current.left;
                } else { //i.e. predecessor.right = current, i.e. loop in the tree, we have already visited this node!
                    predecessor.right = null;   //remove loop in the tree
//                    System.out.println(current.val);
                    if (prevNode != null && prevNode.val > current.val) {
                        errorNodes.add(prevNode);
                        errorNodes.add(current);
                    }
                    prevNode = current;

                    current = current.right;
                }
            }
        }

        TreeNode firstNode = null;
        TreeNode secondNode = null;
        if (errorNodes.size() == 2) {
            firstNode = errorNodes.get(0);
            secondNode = errorNodes.get(1);
        } else if (errorNodes.size() == 4) {
            firstNode = errorNodes.get(0);
            secondNode = errorNodes.get(3);
        } else {
            System.out.println("Error!");
            return;
        }

        //swap the values, but not real nodes
//        System.out.println(firstNode.val);
//        System.out.println(secondNode.val);
        int tempVal = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = tempVal;
    }

}