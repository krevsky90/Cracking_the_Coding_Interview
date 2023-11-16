package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6399d8d20d7254be596610f4
 * OR
 * 129. Sum Root to Leaf Numbers
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/
 * <p>
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 * <p>
 * A leaf node is a node with no children.
 * <p>
 * Example 1:
 * Input: root = [1,2,3]
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [1, 1000].
 * 0 <= Node.val <= 9
 * The depth of the tree will not exceed 10.
 */
public class SumOfPathNumbers {
    // root = 2
    // tempPath = [1, 2]
    // tempNum = 0
    // p = 2 - 1 = 1
    // nums = [12, 13]


    /**
     * KREVSKY:
     * time to solve ~ 23 mins
     * time to debug ~ 20 mins
     * <p>
     * 2 attempts (since do NOT use ^ instead of Math.pow, since ^ means XOR)
     */
    public int sumNumbers(TreeNode root) {
        List<Integer> nums = new ArrayList<>();

        sumNumbers(root, nums, null);

        int result = 0;
        for (Integer n : nums) {
            result += n;
        }

        return result;
    }

    private void sumNumbers(TreeNode root, List<Integer> nums, LinkedList<Integer> tempPath) {
        if (root == null) return;

        if (tempPath == null) tempPath = new LinkedList<>();

        tempPath.add(root.val);
        // check if root is leaf
        if (root.left == null && root.right == null) {
            // calculate number and add to nums
            int tempNum = 0;
            int p = tempPath.size() - 1;
            for (int val : tempPath) {
                tempNum += val * Math.pow(10, p);   //NOTE! do NOT use 10^p, since operator ^ means XOR!
                p--;
            }
            nums.add(tempNum);
        }

        //if root is leaf then both these calls will do nothing => we can omit 'return' in 'if (is leaf)' block'
        sumNumbers(root.left, nums, tempPath);
        sumNumbers(root.right, nums, tempPath);

        tempPath.pollLast();    //remove the last element (i.e. root) from path/stack
    }

    /**
     * SOLUTION #2:
     * https://leetcode.com/problems/sum-root-to-leaf-numbers/solutions/4157694/java-100-beats-easy-o-n-solution/
     * The idea:
     * it is not necessary to store the path! and all sums!
     * it is just sufficient to add current root node to result sum in case if root is leaf node
     */
    int result = 0;

    public int sumNumbers2(TreeNode root) {
        sum2(root, 0);
        return result;
    }

    public void sum2(TreeNode root, int num) {
        if (root == null) return;
        num = root.val + 10 * num;

        if (root.left == null && root.right == null) {
            result += num;
        }

        sum2(root.left, num);
        sum2(root.right, num);
    }
}
