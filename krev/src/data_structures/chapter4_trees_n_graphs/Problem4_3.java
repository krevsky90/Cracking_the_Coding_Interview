package data_structures.chapter4_trees_n_graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * p.121
 * 4.3 List of Depths: Given a binary tree, design an algorithm which creates a linked list of all the nodes
 * at each depth (e.g., if you have a tree with depth D, you 'll have D linked lists).
 * Hints: #107, #123, #135
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_3 {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n4.left = n2;
        n4.right = n5;
        n2.left = n3;
        n3.left = n1;
        n5.left = n6;
        n5.right = n7;

        List<LinkedList<Node>> result = getLevels(n4);
        System.out.println("Set breakpoint here to check what does result contain");
    }

    /**
     * KREVSKY SOLUTION:
     * set level property to each node and add the node to corresponding linked list according to the node's level
     */
    public static List<LinkedList<Node>> getLevels(Node root) {
        List<LinkedList<Node>> result = new ArrayList<>();
        LinkedList<Node> q = new LinkedList<>();
        root.level = 0;
        q.add(root);
        LinkedList<Node> tempList;
        while (!q.isEmpty()) {
            Node tempNode = q.remove();
            int currentLevel = tempNode.level;
            if (result.size() < currentLevel + 1) {
                tempList = new LinkedList<>();
                result.add(tempList);
            }
            result.get(currentLevel).add(tempNode);

            if (tempNode.left != null) {
                tempNode.left.level = currentLevel + 1;
                q.add(tempNode.left);
            }

            if (tempNode.right != null) {
                tempNode.right.level = currentLevel + 1;
                q.add(tempNode.right);
            }
        }

        return result;
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static List<LinkedList<Node>> createLevelLinkedList(Node root) {
        ArrayList<LinkedList<Node>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    public static void createLevelLinkedList(Node root, ArrayList<LinkedList<Node>> lists, int level) {
        if (root == null) {
            return;
        }
        LinkedList<Node> list = null;
        if (lists.size() == level) { //level not contained in list
            list = new LinkedList<>();
            // Levels are always traversed in order. So, if this is the first time we've
            // visited level i, we must have seen levels 0 through i - 1. We can
            // therefore safely add the level at the end.
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level + 1);
        createLevelLinkedList(root.right, lists, level + 1);
    }

    private static class Node {
        public int value;
        public Node left;
        public Node right;
        public int level;   //for KREVSKY solution

        public Node(int x) {
            value = x;
        }

        public String toString() {
            return "(" + value + ")";
        }
    }

}
