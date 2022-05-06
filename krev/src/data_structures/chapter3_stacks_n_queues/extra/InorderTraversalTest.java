package data_structures.chapter3_stacks_n_queues.extra;

import java.util.Stack;

/**
 * https://www.youtube.com/watch?v=MZ67ceIH8v4&list=PLNmW52ef0uwuvEW2yg2PxErsLF9ldA1WP&index=7
 * Interview Question: Inorder Traversal
 *
 * Given a binary search tree, print out the elements of the tree in order without using recursion
 *      5
 *    /   \
 *   3     7
 *  / \   / \
 * 1   4 6   8
 *
 * 1, 3, 4, 5, 6, 7, 8
 *
 */
public class InorderTraversalTest {
    private static class Node {
        private int value;
        private boolean marked;
        private Node left;
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }
    }

    // 5 3 7 1 4 6 8
    // 5 7 3 8 6 4 1
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        //link nodes to binary search tree
        n5.left = n3;
        n5.right = n7;
        n3.left = n1;
        n3.right = n4;
        n7.left = n6;
        n7.right = n8;
        traverse(n5);
    }

    /**
     * KREVSKY
     */
    public static void printKrevsky(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        Node parent = root;
        stack.push(parent);

        while (!stack.empty()) {
            if (parent.left != null && !parent.left.marked) {
                parent = parent.left;
                stack.push(parent);
            } else if (parent.right != null && !parent.right.marked) {
                //it means that parent.left = null or parent.left.marked = true (i.e. already printed)
                if (!parent.marked) {
                    System.out.println(parent);
                    parent.marked = true;
                }
                parent = parent.right;
                stack.push(parent);
            } else {
                //it means that parent is leaf (left = right = null)
                if (!parent.marked) {
                    System.out.println(parent);
                    parent.marked = true;
                }
                stack.pop();    //remove parent from stack
                if (stack.isEmpty()) {
                    break;  //if parent was the last element of stack
                }
                parent = stack.peek();  //back to the parent of parent node
            }
        }
    }

    /**
     * ORIGINAL SOLUTION
     * Time O(n)
     * Space O(n) - see case when root has only left nodes (and N depth)
     */
    public static void traverse(Node n) {
        Stack<Node> s = new Stack<>();
        addLeftToStack(s, n);
        while (!s.isEmpty()) {
            Node curr = s.pop();
            System.out.println(curr);
            if (curr.right != null) {
                addLeftToStack(s, curr.right);
            }
        }
    }

    //add all left branch of n node
    private static void addLeftToStack(Stack<Node> s, Node n) {
        while (n != null) {
            s.push(n);
            n = n.left;
        }
    }
}
