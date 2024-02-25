package solving_techniques.p10_SubsetsAndPermutations;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639dd2366c7c3e931728354f (hard)
 * OR
 * 95. Unique Binary Search Trees II
 * https://leetcode.com/problems/unique-binary-search-trees-ii (medium)
 * <p>
 * Given an integer n, return all the structurally unique BST's (binary search trees),
 * which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: [[1]]
 * <p>
 * Constraints:
 * 1 <= n <= 8
 */
public class ProblemChallenge2_StructurallyUniqueBSTs {
    /**
     * KREVSKY SOLUTION #1:
     * idea is the same as src/solving_techniques/p10_Subsets/ProblemChallenge3_CountOfStructurallyUniqueBSTs.java
     * (i.e. https://leetcode.com/problems/unique-binary-search-trees)
     * <p>
     * time to solve ~ 25 mins
     * 1 attempt
     */
    public List<TreeNode> generateTrees(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        return generateTrees(arr, 0, n);
    }

    private List<TreeNode> generateTrees(int[] arr, int left, int right) {
        List<TreeNode> result = new ArrayList<>();
        if (left >= right) {
            return new ArrayList<>();
        } else if (left + 1 == right) {
            TreeNode n = new TreeNode(arr[left]);
            result.add(n);
            return result;
        } else {
            for (int i = left; i < right; i++) {
                List<TreeNode> leftResult = generateTrees(arr, left, i);
                List<TreeNode> rightResult = generateTrees(arr, i + 1, right);
                if (leftResult.isEmpty()) {
                    for (TreeNode rightNode : rightResult) {
                        TreeNode tempRoot = new TreeNode(arr[i]);
                        tempRoot.right = rightNode;
                        result.add(tempRoot);
                    }
                } else if (rightResult.isEmpty()) {
                    for (TreeNode leftNode : leftResult) {
                        TreeNode tempRoot = new TreeNode(arr[i]);
                        tempRoot.left = leftNode;
                        result.add(tempRoot);
                    }
                } else {
                    for (TreeNode leftNode : leftResult) {
                        for (TreeNode rightNode : rightResult) {
                            TreeNode tempRoot = new TreeNode(arr[i]);
                            tempRoot.left = leftNode;
                            tempRoot.right = rightNode;
                            result.add(tempRoot);
                        }
                    }
                }
            }

            return result;
        }
    }

    /**
     * SOLUTION 2:
     * 1) avoid validations leftResult.isEmpty() and rightResult.isEmpty() by adding result.add(null); in case "left >= right"
     * 2) add memoization as described in https://leetcode.com/problems/unique-binary-search-trees-ii/ (see Editorial)
     */
    public List<TreeNode> generateTrees2(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        Map<Pair<Integer, Integer>, List<TreeNode>> memo = new HashMap<>(); //map (left, right) -> List of roots of different BSTs
        return generateTreesMemo(arr, 0, n, memo);
    }

    private List<TreeNode> generateTreesMemo(int[] arr, int left, int right, Map<Pair<Integer, Integer>, List<TreeNode>> memo) {
        List<TreeNode> memoResult = memo.get(new Pair<>(left, right));
        if (memoResult != null) {
            return memoResult;
        }

        List<TreeNode> result = new ArrayList<>();
        if (left >= right) {
            result.add(null);
        } else if (left + 1 == right) {
            TreeNode n = new TreeNode(arr[left]);
            result.add(n);
        } else {
            for (int i = left; i < right; i++) {
                List<TreeNode> leftResult = generateTreesMemo(arr, left, i, memo);
                List<TreeNode> rightResult = generateTreesMemo(arr, i + 1, right, memo);
                for (TreeNode leftNode : leftResult) {
                    for (TreeNode rightNode : rightResult) {
                        TreeNode tempRoot = new TreeNode(arr[i]);
                        tempRoot.left = leftNode;
                        tempRoot.right = rightNode;
                        result.add(tempRoot);
                    }
                }
            }
        }
        memo.put(new Pair<>(left, right), result);
        return result;
    }
}