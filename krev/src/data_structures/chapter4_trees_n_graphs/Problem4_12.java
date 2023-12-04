package data_structures.chapter4_trees_n_graphs;

import java.util.*;

/**
 * p.123
 * Paths with Sum:
 * You are given a binary tree in which each node contains an integer value (which
 * might be positive or negative). Design an algorithm to count the number of paths that sum to a
 * given value. The path does not need to start or end at the root or a leaf, but it must go downwards
 * (traveling only from parent nodes to child nodes).
 * Hints: #6, #14, #52, #68, #77, #87, #94, #103, #108, #115
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_12 {
    public static void main(String[] args) {
        //linear tree
//        Node n1 = new Node(10);
//        Node n2 = new Node(5);
//        Node n3 = new Node(1);
//        Node n4 = new Node(2);
//        Node n5 = new Node(-1);
//        Node n6 = new Node(-1);
//        Node n7 = new Node(7);
//        Node n8 = new Node(1);
//        Node n9 = new Node(2);
//
//        n1.left = n2;
//        n2.left = n3;
//        n3.left = n4;
//        n4.left = n5;
//        n5.left = n6;
//        n6.left = n7;
//        n7.left = n8;
//        n8.left = n9;

        //usual tree
//        Node n1 = new Node(10);
//        Node n2 = new Node(5);
//        Node n3 = new Node(-3);
//        Node n4 = new Node(3);
//        Node n5 = new Node(1);
//        Node n6 = new Node(11);
//        Node n7 = new Node(3);
//        Node n8 = new Node(-2);
//        Node n9 = new Node(2);
//
//        n1.left = n2;
//        n1.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        n3.right = n6;
//        n4.left = n7;
//        n4.right = n8;
//        n5.right = n9;

//        int targetSum = 8;
//        Node root = n1;

        Node n5_1 = new Node(5);
        Node n2 = new Node(2);
        Node n_2 = new Node(-2);
        Node n5_2 = new Node(5);
        int targetSum = 5;

        n5_1.left = n2;
        n2.left = n_2;
        n_2.left = n5_2;


        int result = countPathsWithSum(n5_1, targetSum);
//        int result = countPathsWithSum21(n5_1, targetSum);
        System.out.println(result);
    }

    /**
     * ORIGINAL SOLUTION #2
     * time ~ O(N), space ~ O(N)
     */
    public static int countPathsWithSum(Node root, int targetSum) {
        return countPathsWithSum(root, targetSum, 0, new HashMap<>());
    }


    public static int countPathsWithSum(Node root, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (root == null) return 0;

        runningSum += root.value;
        int sum = runningSum - targetSum;
        int totalPaths = pathCount.getOrDefault(sum, 0);
        if (runningSum == targetSum) {
            totalPaths++;
        }

        // Increment pathCount, recurse, then decrement pathCount
        incrementHashTable(runningSum, pathCount, 1);
        System.out.println("root.value = " + root.value + ", runningSum = " + runningSum + ", sum = " + sum + ", totalPaths = " + totalPaths);
        totalPaths += countPathsWithSum(root.left, targetSum, runningSum, pathCount);
        totalPaths += countPathsWithSum(root.right, targetSum, runningSum, pathCount);
        incrementHashTable(runningSum, pathCount, -1); //just to free space when we handle current root-node. That's more efficient

        return totalPaths;
    }

    private static void incrementHashTable(int runningSum, HashMap<Integer, Integer> pathCount, int delta) {
        int newCount = pathCount.getOrDefault(runningSum, 0) + delta;
        if (newCount == 0) {
            // Remove when zero to reduce space usage
            pathCount.remove(runningSum);
        } else {
            pathCount.put(runningSum, newCount);
        }
    }

    /**
     * ORIGINAL SOLUTION #2.1
     * sightly differs https://youtu.be/uZzvivFkgtM?t=1261
     * time ~ O(N), space ~ O(N)
     */
    public static int countPathsWithSum21(Node root, int targetSum) {
        HashMap<Integer, Integer> pathCount = new HashMap<>();
        pathCount.put(0, 1);    //??? why? is it necessary ???
        return countPathsWithSum21(root, targetSum, 0, pathCount);
    }

    public static int countPathsWithSum21(Node root, int target, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (root == null) return 0;

        runningSum += root.value;
        int totalPaths = pathCount.getOrDefault(runningSum - target, 0);

        //backtracking started
        pathCount.put(runningSum, pathCount.getOrDefault(runningSum, 0) + 1);

        System.out.println("root.value = " + root.value + ", runningSum = " + runningSum + ", sum = " + (runningSum - target) + ", totalPaths = " + totalPaths);
        totalPaths += countPathsWithSum21(root.left, target, runningSum, pathCount);
        totalPaths += countPathsWithSum21(root.right, target, runningSum, pathCount);

        //backtracking finished:
        //if we finish to check the branches that grow from this root node, we need to exclude the node from considered path
        //since the idea should be applied to array, i.e. particular sequence of nodes of the tree, not to the whole tree.
        pathCount.put(runningSum, pathCount.getOrDefault(runningSum, 0) - 1);

        return totalPaths;
    }

    /**
     * ORIGINAL SOLUTION #1 (brute-force)
     * <p>
     * time ~ O(N*logN) - for balanced tree
     * time ~ O(N^2) - for tree ~ straight line
     */
    public static int countPathWithSum(Node root, int targetSum) {
        if (root == null) return 0;

        //Count paths with sum starting from the root
        int pathsFromRoot = countPathWithSumFromNode(root, targetSum, 0);

        //Try the nodes on the left and right.
        int pathsFromLeft = countPathWithSum(root.left, targetSum);
        int pathsFromRight = countPathWithSum(root.right, targetSum);

        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    //Returns the number of paths with this sum starting from this node.
    public static int countPathWithSumFromNode(Node root, int targetSum, int currentSum) {
        if (root == null) return 0;

        currentSum += root.value;

        int totalPaths = 0;
        if (currentSum == targetSum) {
            totalPaths++;
        }

        totalPaths += countPathWithSumFromNode(root.left, targetSum, currentSum);
        totalPaths += countPathWithSumFromNode(root.right, targetSum, currentSum);
        return totalPaths;
    }

    /**
     * KREVSKY's representation - START
     */
    public static void printAllPaths(Node root, List<LinkedList<Node>> paths, LinkedList<Node> prefix) {
        if (root != null) {
            LinkedList<Node> copyPrefix = (LinkedList<Node>) prefix.clone();
            copyPrefix.addLast(root);
            paths.add(copyPrefix);
            printList(copyPrefix);

            printAllPaths(root.left, paths, copyPrefix);
            printAllPaths(root.right, paths, copyPrefix);
        }
    }

    public static void printAllPaths(Node root, List<LinkedList<Node>> paths, LinkedList<Node> prefix, int sum) {
        if (root != null) {
            LinkedList<Node> newPath = (LinkedList<Node>) prefix.clone();
            newPath.addLast(root);
            if (sum(newPath) == sum) {
                paths.add(newPath);
                printList(newPath);
            }

            printAllPaths(root.left, paths, newPath, sum);
            printAllPaths(root.right, paths, newPath, sum);
        }
    }

    private static void printList(LinkedList<Node> list) {
        StringBuilder sb = new StringBuilder();
        for (Node n : list) {
            sb.append(" " + n.value);
        }
        System.out.println(sb.toString());
    }

    private static int sum(LinkedList<Node> list) {
        int result = 0;
        for (Node n : list) {
            result += n.value;
        }
        return result;
    }
    /**
     * KREVSKY's representation - END
     */
}
