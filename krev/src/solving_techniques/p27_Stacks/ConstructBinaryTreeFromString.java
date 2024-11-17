package solving_techniques.p27_Stacks;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Stack;

/**
 * 536. Construct Binary Tree from String (medium) (blocked)
 * https://leetcode.ca/all/536.html#google_vignette
 *
 * #Company: Amazon Meta
 *
 * You need to construct a binary tree from a string consisting of parenthesis and integers.
 *
 * The whole input represents a binary tree. It contains an integer followed by zero, one or two pairs of parenthesis.
 * The integer represents the root's value and a pair of parenthesis contains a child binary tree with the same structure.
 *
 * You always start to construct the left child node of the parent first if it exists.
 *
 * Example:
 * Input: "4(2(3)(1))(6(5))"
 * Output: return the tree root node representing the following tree:
 *
 *        4
 *      /   \
 *     2     6
 *    / \   /
 *   3   1 5
 * Note:
 * There will only be '(', ')', '-' and '0' ~ '9' in the input string.
 * An empty tree is represented by "" instead of "()".
 */
public class ConstructBinaryTreeFromString {
    public static void main(String[] args) {
        String s = "4(2(3)(1))(6(5))";
        ConstructBinaryTreeFromString obj = new ConstructBinaryTreeFromString();
        TreeNode root = obj.str2tree(s);
        System.out.println("");

    }
    /**
     * NOT SOLVED by myself;
     * info: https://www.youtube.com/watch?v=AY7ZO0Q1s0k&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=123
     * idea:
     * 1) keep stack of TreeNodes
     * 2) traverse the string and
     * - save the number as TreeNode into stack
     * - if curChar = ")" then pop top element from stack and make it as child of top element of stack
     * NOTE: it left = null => it will be left child. otherwise - right child
     *
     * time to think ~ 20 mins
     * time to implement ~ 25 mins
     *
     * time ~ O(s.length())
     * space ~ O(s.length())
     *
     * 2 attempts:
     * - changed Integer.valueOf(sArr[i]) to Character.getNumericValue(sArr[i]);
     */
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;

        Stack<TreeNode> stack = new Stack<>();
        // TreeNode root = null;

        char[] sArr = s.toCharArray();
        boolean neg = false;
        int i = 0;
        int tempNum = 0;
        while (i < sArr.length) {
            if (sArr[i] == '-') {
                neg = true;
                i++;
            } else if (Character.isDigit(sArr[i])) {
                tempNum = 0;
                while (i < sArr.length && Character.isDigit(sArr[i])) {
                    tempNum = 10*tempNum + Character.getNumericValue(sArr[i]);
                    i++;
                }

                if (neg) {
                    tempNum = -tempNum;
                    neg = false;
                }

                TreeNode newNode = new TreeNode(tempNum);
                stack.add(newNode);
            } else if (sArr[i] == ')') {
                TreeNode childNode = stack.pop();

                if (stack.peek().left == null) {
                    stack.peek().left = childNode;
                } else {
                    stack.peek().right = childNode;
                }

                i++;
            } else {
                i++;
            }
        }

        return stack.pop();
    }
}
