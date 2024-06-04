package solving_techniques.p7_TreeBreadthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

public class MinimumNumberOfOperationsToSortBTbyLevel {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
//        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);
        TreeNode n9 = new TreeNode(9);
        TreeNode n10 = new TreeNode(10);

        n1.left = n4;
        n1.right = n3;
        n4.left = n7;
        n4.right = n6;
        n3.left = n8;
        n3.right = n5;
        n8.left = n9;
        n5.left = n10;

        MinimumNumberOfOperationsToSortBTbyLevel obj = new MinimumNumberOfOperationsToSortBTbyLevel();
        int res1 = obj.minimumOperations(n1);
        System.out.println(res1);   //expected 3


    }

    /**
     * NOT SOLVED by myself
     * time to solve ~ 70 mins
     * <p>
     * idea:
     * Start by initializing a count variable to 0(globally) and a queue to store the nodes of the binary tree.
     * Add the root node to the queue.
     * While the queue is not empty, do the following:
     * 1) Get the size of the queue and initialize an array to store the values of the nodes at the current level of the tree.
     * 2) For each node in the current level, add its value to the array and enqueue its children if they exist.
     * - Call the getSwaps function with the levelValues array to calculate the number of swaps required to make the level into a heap.
     * Return the final count variable.
     * <p>
     * The getSwaps function works as follows:
     * 1) Copy the input array and sort the copy in ascending order.
     * 2) Create a mapping of each element in the input array to its index using a HashMap.
     * 3) For each element in the input array, do the following:
     * - If the element is not in its correct position in the sorted array, find the correct position of the element in the input array using the mapping created in step 2.
     * - Swap the current element with the element at the correct position and update the mapping.
     * - Increment the count variable.
     *
     * a lot of attempts:
     * - forgot to update valToIdxMap
     * - cloned list of TreeNodes => when I swapped TreeNode values, it also affected sortedList
     * - used add(idx, value) method instead of set(idx, value)
     *
     * BEATS = 42%
     */
    public int minimumOperations(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        int result = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            List<TreeNode> tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                tempList.add(cur);
                if (cur.left != null) q.add(cur.left);
                if (cur.right != null) q.add(cur.right);
            }

            //will work with integers, not with TreeNode objects!
            List<Integer> values = new ArrayList<>(tempList.size());
            for (TreeNode tn : tempList) {
                values.add(tn.val);
            }
            //count swaps for current level
            result += getSwaps(values);
        }

        return result;
    }

    private int getSwaps(List<Integer> values) {
        List<Integer> sortedList = new ArrayList<>(values);
        Collections.sort(sortedList);   //O(klogk), where k - length of tempList

        Map<Integer, Integer> valToIdxMap = new HashMap<>();
        for (int i = 0; i < values.size(); i++) {
            valToIdxMap.put(values.get(i), i);
        }

        // 5 6 7 8

        // map:
        // 7 6 8 5
        // 2 1 3 0

        // 5 6 7 8
        // i = 2
        // curVal = 8
        // correctval = 7
        // indexToSwap = 3

        int counter = 0;
        for (int i = 0; i < values.size(); i++) {
            int curVal = values.get(i);
            int correctVal = sortedList.get(i);
            if (curVal != correctVal) {
                int indexToSwap = valToIdxMap.get(correctVal);
                //swap
                values.set(i, correctVal);
                values.set(indexToSwap, curVal);
                //change map of indexes
                valToIdxMap.put(curVal, indexToSwap);
                valToIdxMap.put(correctVal, i);

                counter++;
            }
        }

        return counter;
    }
}
