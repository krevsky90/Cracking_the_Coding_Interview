package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

import data_structures.chapter4_trees_n_graphs.Problem4_11;
import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *
 *
 * Example 1:
 * Input: root = [1,2,5,3,4,null,6]
 * Output: [1,null,2,null,3,null,4,null,5,null,6]
 *
 * Example 2:
 * Input: root = []
 * Output: []
 *
 * Example 3:
 * Input: root = [0]
 * Output: [0]
 *
 * Constraints:
 * The number of nodes in the tree is in the range [0, 2000].
 * -100 <= Node.val <= 100
 *
 */
public class Problem2_10_FlattenBTtoLinkedList {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n1.left = n2;
        n1.right = n5;
        n2.left = n3;
        n2.right = n4;
        n5.right = n6;

        new Problem2_10_FlattenBTtoLinkedList().flattenMorris(n1);

        //print results
        TreeNode curNode = n1;
        String result = "";
        while (curNode != null) {
            result += curNode.val + " -> ";
            curNode = curNode.right;
        }
        System.out.println(result);

    }
    /**
     *  PRE-order Morris traversal approach
     *  info: https://www.youtube.com/watch?v=R7nYOFtv24M
     *
     *  idea: linked list is root -> someNode_1 -> ... -> someNode_N, where all links are RIGHT pointers!
     */
    public void flattenMorris(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                current = current.right;
                //do nothing or print current element if you want
            } else {
                // Find IN-order predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                //if is NOT necessary since once while-loop ended => predecessor.right = null
//                if (predecessor.right == null) {
                    //i.e. if we reach leaf - then link it to current.right node
                    //NOTE: this is modification FROM in-order traversal TO pre-order traversal
                    predecessor.right = current.right;

                    //since finally we need to get linked list consists of nodes that have right link and doesn't have left link
                    current.right = current.left;
                    current.left = null;
//                }

                //and check do the same actions for left (i.e. already right) child of current node
                current = current.right;
            }
        }
    }


    /**
     * good explanation: https://www.youtube.com/watch?v=NOKVBiJwkD0
     *
     * Idea:
     * 1) take tempLeft and tempRight to store root's children
     * 2) set root -> left = null
     * 3) call recursively flatten method for tempLeft and tempRight
     * After that we get two lists (from left and right tree parts)
     * 4) attach root -> right = tempLeft (that is beginning of left list)
     * 5) find the latest element (le) of left list
     * 5.2) and le -> right = tempRight
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        // 1) take tempLeft and tempRight to store root's children
        TreeNode tempLeft = root.left;
        TreeNode tempRight = root.right;
        // 2) set root -> left = null
        root.left = null;
        // 3) call recursively flatten method for tempLeft and tempRight
        flatten(tempLeft);
        flatten(tempRight);
        // 4) attach root -> right = tempLeft (that is beginning of left list)
        root.right = tempLeft;
        // 5) find the latest element (le) of left list
        TreeNode temp = root;
        while (temp.right != null) {
            temp = temp.right;
        }
        // 5.2) and le -> right = tempRight
        temp.right = tempRight;
    }
}
