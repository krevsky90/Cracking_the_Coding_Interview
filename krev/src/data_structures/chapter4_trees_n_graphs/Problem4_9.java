package data_structures.chapter4_trees_n_graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * p.122
 * 4.9 BST Sequences: A binary search tree was created by traversing through an array from left to right
 * and inserting each element. Given a binary search tree with distinct elements, print all possible
 * arrays that could have led to this tree.
 * EXAMPLE
 * Input: 1 <- 2 -> 3
 * Output: {2, 1, 3}, {2, 3, 1}
 * Hints: #39, #48, #66, #82
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) we DON't have link to parent node
 * 2) we have links to n1, n2 and root of the tree
 */
public class Problem4_9 {
    /**
     * Я НЕ ПОНИМАЮ постановки задачи про traverse through the array !
     * поэтому сложно понять, почему порядок элементов в каждом поддереве должен сохраняться
     * при генерации возможных перестановок в методе weaveLists
     */
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;

        ArrayList<LinkedList<Integer>> allSequences = allSequences(n4);

        for (LinkedList<Integer> l : allSequences) {
            StringBuilder sb = new StringBuilder(allSequences.indexOf(l) + ": ");
            for (Integer i : l) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
        }

        System.out.println("-----");

        LinkedList<Integer> leftL = new LinkedList<Integer>();
        leftL.addAll(Arrays.asList(2, 3, 1));
        LinkedList<Integer> rightL = new LinkedList<Integer>();
        rightL.addAll(Arrays.asList(6, 5, 7));

        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();
        weaveLists(leftL, rightL,
                result,
                new LinkedList<Integer>()
                );

        for (LinkedList<Integer> l : result) {
            StringBuilder sb = new StringBuilder(result.indexOf(l) + ": ");
            for (Integer i : l) {
                sb.append(i).append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static ArrayList<LinkedList<Integer>> allSequences(Node node) {
        ArrayList<LinkedList<Integer>> result = new ArrayList<LinkedList<Integer>>();

        if (node == null) {
            result.add(new LinkedList<Integer>());
            return result;
        }

        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.value);

        ArrayList<LinkedList<Integer>> leftSequences = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSequences = allSequences(node.right);

        for (LinkedList<Integer> left : leftSequences) {
            for (LinkedList<Integer> right : rightSequences) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveLists(left, right, weaved, prefix);
                result.addAll(weaved);
            }
        }

        return result;
    }

    private static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        if (first.isEmpty() || second.isEmpty()) {
            LinkedList<Integer> result = (LinkedList<Integer>)prefix.clone();
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }

        int headFirst = first.removeFirst();
        prefix.add(headFirst);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.add(headSecond);
        weaveLists(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }
}
