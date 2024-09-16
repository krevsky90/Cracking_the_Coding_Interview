package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.*;

/**
 * 987. Vertical Order Traversal of a Binary Tree (hard)
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree
 *
 * #Company: Adobe Amazon Bloomberg Databricks Facebook Google LinkedIn Microsoft Reddit Samsung
 *
 * Given the root of a binary tree, calculate the vertical order traversal of the binary tree.
 *
 * For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1)
 *      and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).
 *
 * The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index
 *      starting from the leftmost column and ending on the rightmost column.
 * There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.
 *
 * Return the vertical order traversal of the binary tree.
 *
 * Example 1:
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation:
 * Column -1: Only node 9 is in this column.
 * Column 0: Nodes 3 and 15 are in this column in that order from top to bottom.
 * Column 1: Only node 20 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 2:
 * Input: root = [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * Column -2: Only node 4 is in this column.
 * Column -1: Only node 2 is in this column.
 * Column 0: Nodes 1, 5, and 6 are in this column.
 *           1 is at the top, so it comes first.
 *           5 and 6 are at the same position (2, 0), so we order them by their value, 5 before 6.
 * Column 1: Only node 3 is in this column.
 * Column 2: Only node 7 is in this column.
 *
 * Example 3:
 * Input: root = [1,2,3,4,6,5,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation:
 * This case is the exact same as example 2, but with nodes 5 and 6 swapped.
 * Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
 *
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 1000
 */
public class VerticalOrderTraversalOfBT {
    public static void main(String[] args) {
//        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
//            if (a[0] < b[0]) {
//                return -1;
//            } else if (a[0] == b[0]) {
//                return a[1] - b[1];
//            } else {
//                return 1;
//            }
//        });
//
//        set.add(new int[]{2,6});
//        set.add(new int[]{2,7});
//        set.add(new int[]{2,5});
//
//
//        for (int[] s : set) {
//            System.out.println(s[0] + " " + s[1]);
//        }

        TreeNode n3 = new TreeNode(3) ;
        TreeNode n1 = new TreeNode(1) ;
        TreeNode n4 = new TreeNode(4) ;
        TreeNode n0 = new TreeNode(0) ;
        TreeNode n2 = new TreeNode(2) ;
        TreeNode n22 = new TreeNode(2) ;

        n3.left = n1;
        n3.right = n4;
        n1.left = n0;
        n1.right = n2;
        n4.left = n22;

        VerticalOrderTraversalOfBT obj = new VerticalOrderTraversalOfBT();
        obj.verticalTraversal(n3);
    }

    /**
     * KREVSKY SOLUTION:
     * time to think and implement + time to debud ~ 21 mins + 20 mins ~ 40 mins
     * idea:
     * 1) store map: column -> Set of nodes
     * 2) to store nodes and sort them by value if (row, col) are the same, introduce array + comparator
     * 2.2) in case if (row, col, val) are the same, introduce id (counter) => changed comparator.
     * NOTE: without id in comparator TreeSet excluded the array despite it was different due to id!
     * => to come to idea "add id to comparator" took 15 mins!
     * 3) keep min and max value of the column
     * 3.2) in the end travers from the most left to the mot right, get corresponding TreeSet and put only values to final list
     *
     * a lot of attempts:
     * - stupid typos
     * - did not include id (i.e. [2] element) in comparator
     *
     * BEATS ~ 99%
     */
    private int counter = 0;

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        // min - the leftest column number, max - the rightest column number
        int[] minmax = new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE};
        Map<Integer, TreeSet<int[]>> map = new HashMap<>(); //where [0] - row, [1] - node value, [2] - some kind of id

        dfs(root, map, minmax, 0, 0);

        for (int i = minmax[0]; i <= minmax[1]; i++) {
            TreeSet<int[]> tempSet = map.get(i);
            List<Integer> tempList = new ArrayList<>(tempSet.size());
            for (int[] s : tempSet) {
                tempList.add(s[1]);
            }
            result.add(tempList);
        }
        return result;
    }

    private void dfs(TreeNode root, Map<Integer, TreeSet<int[]>> map, int[] minmax, int row, int col) {
        if (root == null) {
            return;
        }

        map.putIfAbsent(col, new TreeSet<int[]>((a, b) -> {
            if (a[0] == b[0]) {
                if (a[1] == b[1]) {
                    return a[2] - b[2];
                } else {
                    return a[1] - b[1];
                }
            } else {
                return a[0] - b[0];
            }
        }));

        map.get(col).add(new int[]{row, root.val, counter});
        counter++;
        minmax[0] = Math.min(minmax[0], col);
        minmax[1] = Math.max(minmax[1], col);

        dfs(root.left, map, minmax, row + 1, col - 1);
        dfs(root.right, map, minmax, row + 1, col + 1);
    }
}