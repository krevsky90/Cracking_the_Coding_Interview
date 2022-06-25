package data_structures.chapter4_trees_n_graphs.extra;

import data_structures.chapter4_trees_n_graphs.Node;

/**
 * https://www.youtube.com/watch?v=eltLoCIDIes&list=PLNmW52ef0uwtUY4OFRF0eV1mlT5lKhe_j&index=3
 * Byte by Byte: Interview Question: Tree to Linked List
 * <p>
 * Given a tree, write a function to convert it into a circular doubly linked list from left to right
 * by only modifying the existing pointers
 * <p>
 * tree:
 *      1
 *    /   \
 *   2     3
 *  / \   / \
 * 4   5 6   7
 * <p>
 * traverse(tree): <- 4 <-> 2 <-> 5 <-> 1 <-> 6 <-> 3 <-> 7 ->
 *
 * ============ VALIDATION ON PAPER ============
 * convertToLinkedList(1)
 * getSubList(1, right)
 *     left = getSubList(2, left) = 5
 *                 left = getSubList(4, left) = 4
 *                         left = getSubList(null, left) = null
 *                         right = getSubList(null, right) = null
 *                 right = getSubList(5, right) = 5
 *                 4 <-> 2 <-> 5
 *      right = ...
 */

// 14:37 - 15:11 = 34 mins without checking
// 15:11 - 15:17 = 6 mins to check on paper
// 15:36 - passed with 1 logical error (since getSubList(n, NodeType.RIGHT) doesn't return head, it returns 5 in the example above)

public class TreeToLinkedList {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        Node head = convertToLinkedList(n1);
        Node stopNode = head;
        Node startNode = head.right;
        while (startNode != stopNode) {
            System.out.println(startNode.value);
            startNode = startNode.right;
        }
        System.out.println(startNode.value);
    }

    public enum NodeType {LEFT, RIGHT}

    public static Node convertToLinkedList(Node n) {
        Node someNode = getSubList(n, NodeType.RIGHT);  //for example, RIGHT
        //search head (leftmost node)
        Node head = someNode;
        while (head.left != null) {
            head = head.left;
        }

        //find tail (rightmost node)
        Node tempNode = head;
        while (tempNode.right != null) {
            tempNode = tempNode.right;
        }

        //since we ended while, it means that tempNode.right = null (i.e. we've found the tail)
        //now loop head and tail of the list
        head.left = tempNode;
        tempNode.right = head;

        //for example, return head (or nothing?)
        return head;
    }

    protected static Node getSubList(Node n, NodeType type) {
        if (n == null) return null;
        Node leftPart = getSubList(n.left, NodeType.LEFT);
        Node rightPart = getSubList(n.right, NodeType.RIGHT);

        if (leftPart != null) {
            leftPart.right = n;
            n.left = leftPart;
        }

        if (rightPart != null) {
            rightPart.left = n;
            n.right = rightPart;
        }

        if (type == NodeType.LEFT) {
            //return the last (rightmost) node of created list
            return rightPart != null ? rightPart : n;
        } else {    //i.e. NodeType = RIGHT
            //return the first (leftmost) node of created list
            return leftPart != null ? leftPart : n;
        }
    }

    /**
     * ORIGINAL SOLUTION
     * idea:
     * 1) doubly circular linked list is build in each call of sublist-method,
     * so each time when we concatenate sublist with other node/sublist, we have useful property: first.left = last and last.rught = first
     * 1.2) to avoid NPE problems by default each node points to itself (i.e. n.left = n, n.right = n)
     */

    /**
     * @param a - start node of the first list that should be joined
     * @param b - start node of the second list that should be joined
     * @return a
     * example:
     * before: <- 1 <-> 2 -> and <- 3 ->
     * after:  <- 1 <-> 2 <-> 3 ->
     */
    private static Node concatenate(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node aEnd = a.left;
        Node bEnd = b.left;

        //make circular (connect start node of the first list to finish node of the second list)
        a.left = bEnd;
        bEnd.right = aEnd;

        //link end node of the first list to start node of the second list
        aEnd.right = b;
        b.left = aEnd;

        return a;
    }

    public Node treeToList(Node n) {
        if (n == null) return n;
        Node leftList = treeToList(n.left);
        Node rightList = treeToList(n.right);
        //by default each node points to itself (to avoid NPEs in concatenate method
        n.left = n;
        n.right = n;

        n = concatenate(leftList, n);
        n = concatenate(n, rightList);

        return n;   //points to start node of leftList
    }
}