package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 1028. Recover a Tree From Preorder Traversal (hard)
 * https://leetcode.com/problems/recover-a-tree-from-preorder-traversal
 * <p>
 * #Company (8.03.2025):
 * <p>
 * We run a preorder depth-first search (DFS) on the root of a binary tree.
 * <p>
 * At each node in this traversal, we output D dashes (where D is the depth of this node),
 * then we output the value of this node.
 * If the depth of a node is D, the depth of its immediate child is D + 1.  The depth of the root node is 0.
 * <p>
 * If a node has only one child, that child is guaranteed to be the left child.
 * <p>
 * Given the output traversal of this traversal, recover the tree and return its root.
 * <p>
 * Example 1:
 * Input: traversal = "1-2--3--4-5--6--7"
 * Output: [1,2,5,3,4,6,7]
 * <p>
 * Example 2:
 * Input: traversal = "1-2--3---4-5--6---7"
 * Output: [1,2,5,3,null,6,null,4,null,7]
 * <p>
 * Example 3:
 * Input: traversal = "1-401--349---90--88"
 * Output: [1,401,null,349,88,90]
 * <p>
 * Constraints:
 * The number of nodes in the original tree is in the range [1, 1000].
 * 1 <= Node.val <= 10^9
 */
public class RecoverTreeFromPreorderTraversal {
    /**
     * KREVSKY SOLUTION:
     * idea: keep list of nodes (one node for each level) that might become parent for current node
     * <p>
     * time to solve ~ 25 mins
     * <p>
     * 2 attempts:
     * - did not think about the latest case (when num > 0, but we finish for loop) => changed loop to i < len + 1
     * <p>
     * BEATS ~ 53%
     */
    public TreeNode recoverFromPreorder(String traversal) {
        //time ~ O(n), n - length of given string
        //space ~ O(h) - to store the list of tree nodes that might be parents of the next node
        char[] arr = traversal.toCharArray();
        TreeNode root = null;
        Map<Integer, TreeNode> levelToNode = new HashMap<>();
        int num = 0;
        int level = 0;
        for (int i = 0; i < arr.length + 1; i++) {
            if (i == arr.length || arr[i] == '-') {
                if (num > 0) {
                    TreeNode curNode = new TreeNode(num);
                    if (root == null) {
                        root = curNode;
                    } else {
                        //add node to tree
                        TreeNode parent = levelToNode.get(level - 1);
                        if (parent.left == null) {
                            parent.left = curNode;
                        } else {
                            parent.right = curNode;
                        }
                    }

                    levelToNode.put(level, curNode);
                    num = 0;
                    level = 0;
                }

                level++;
            } else {
                //i.e. if (Character.isDigit(c)) {
                num = 10 * num + (arr[i] - '0');
            }
        }
        return root;
    }

    /**
     * Alternative solution using Stack
     * <p>
     * PLUS: it is more optimal to use while inside while to count amount of dashes and number (instead of resetting depth and num, as I did)
     */
    public TreeNode recoverFromPreorder2(String traversal) {
        Stack<TreeNode> stack = new Stack<>();
        int index = 0;

        while (index < traversal.length()) {
            // Count the number of dashes
            int depth = 0;
            while (index < traversal.length() && traversal.charAt(index) == '-') {
                depth++;
                index++;
            }

            // Extract the node value
            int value = 0;
            while (index < traversal.length() && Character.isDigit(traversal.charAt(index))) {
                value = value * 10 + (traversal.charAt(index) - '0');
                index++;
            }

            // Create the current node
            TreeNode node = new TreeNode(value);

            // Adjust the stack to the correct depth
            while (stack.size() > depth) {
                stack.pop();
            }

            // Attach the node to the parent
            if (!stack.empty()) {
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                } else {
                    stack.peek().right = node;
                }
            }

            // Push the current node onto the stack
            stack.push(node);
        }

        // The root is the first node in the stack
        while (stack.size() > 1) {
            stack.pop();
        }

        return stack.peek();
    }
}
