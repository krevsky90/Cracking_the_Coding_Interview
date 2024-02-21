package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://www.geeksforgeeks.org/convert-binary-tree-to-doubly-linked-list-using-morris-traversal/
 * OR
 * looks like https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list (locked)
 */
public class ConvertBinaryTreeToDoublyLinkedListByMorrisTraversal {
    public static void main(String[] args) {
        /* Constructing below tree
                    10
                   /  \
                  20   30
                 /  \
                40   60            */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(20);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(40);
        root.left.right = new TreeNode(60);

        TreeNode head = morrisInOrderTraversalCommonAlgorithm(root);

        //expected 40 <-> 20 <-> 60 <-> 10 <-> 30
        TreeNode temp = head;
        String res = "";
        while (temp.right != null) {
            res += temp.val + " -> ";
            temp = temp.right;
        }
        //i.e. temp.right = null
        res += temp.val;
        System.out.println(res);
        System.out.println("-------------");
        res = "";
        while (temp.left != null) {
            res = " <- " + temp.val + res;
            temp = temp.left;
        }
        //i.e. temp.left = null
        res = temp.val + res;
        System.out.println(res);
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve + debug ~ 20 + 8 mins
     *
     * 2 attempts:
     * - incorrect initialize tempNode
     */
    public static TreeNode morrisInOrderTraversalCommonAlgorithm(TreeNode root) {
        TreeNode fakeHead = new TreeNode(-1);
        TreeNode tempNode = fakeHead;
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                //create double links to current node to linked list
                tempNode.right = current;
                current.left = tempNode;

                tempNode = tempNode.right;

                current = current.right;
            } else {
                //find predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    //create loop
                    predecessor.right = current;
                    current = current.left;
                } else {
                    //remove loop
                    predecessor.right = null;
                    //create double links to current node to linked list
                    tempNode.right = current;
                    current.left = tempNode;

                    tempNode = tempNode.right;

                    current = current.right;
                }
            }
        }

        TreeNode result = fakeHead.right;
        result.left = null; //remove link to fakeHead, for beauty
        return result;
    }
}
