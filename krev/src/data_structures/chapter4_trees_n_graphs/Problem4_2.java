package data_structures.chapter4_trees_n_graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * p.121
 * Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an algorithm
 * to create a binary search tree with minimal height.
 * Hints: #19, #73, #116
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_2 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};

        List<Node> listToPrint = new ArrayList<>();
        Node root = buildBinarySearchTreeRecursively(arr, 0, arr.length - 1, listToPrint);
        System.out.println("");
    }

    public static Node buildBinarySearchTreeRecursively(int[] arr, int start, int end, List<Node> listToPrint) {
        if (end < start) {
            return null;
        }
        //search middle:
        int middleIndex = (end + start) % 2 == 0 ? (end + start) / 2 : (end + start) / 2 + 1;
        //ORIGINAL solution is middleIndex = (start + end) / 2
        Node node = new Node(arr[middleIndex]);
        listToPrint.add(node);
        node.left = buildBinarySearchTreeRecursively(arr, start, middleIndex - 1, listToPrint);
        node.right = buildBinarySearchTreeRecursively(arr, middleIndex + 1, end, listToPrint);

        return node;
    }
}