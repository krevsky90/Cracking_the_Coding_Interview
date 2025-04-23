package solving_techniques.p8_TreeDepthFirstSearch;

/**
 * https://www.tryexponent.com/practice/prepare/bst-successor-search
 * <p>
 * In a Binary Search Tree (BST), an Inorder Successor of a node is defined as the node with the smallest key greater than the key of the input node (see examples below). Given a node inputNode in a BST, you’re asked to write a function findInOrderSuccessor that returns the Inorder Successor of inputNode. If inputNode has no Inorder Successor, return null.
 * <p>
 * Explain your solution and analyze its time and space complexities.
 * <p>
 * In this diagram, the inorder successor of 9 is 11 and the inorder successor of 14 is 20.
 * <p>
 * Example:
 * <p>
 * In the diagram above, for inputNode whose key = 11
 * <p>
 * Your function would return:
 * <p>
 * The Inorder Successor node whose key = 12
 * <p>
 * Constraints:
 * <p>
 * [time limit] 5000ms
 * [input] Node inputNode
 * [output] Node
 */
public class BSTSuccessorSearch {
    static class Node {

        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }

    Node root;

    /**
     * KREVSKY SOLUTION:
     * time ~ O(logN)
     * space ~ O(1)
     */
    Node findInOrderSuccessor(Node inputNode) {
        if (root == null) return null;

        if (inputNode.right != null) {
            Node cur = inputNode.right;
            while (cur.left != null) {
                cur = cur.left;
            }
            return cur;
        } else {
            Node cur = inputNode;
            while (cur.parent != null && cur.parent.right == cur) {
                cur = cur.parent;
            }

            if (cur.parent == null) return null;
            //otherwise we finished while-loop since cur.parent.right != cur. then return parent
            return cur.parent;
        }
    }
}
