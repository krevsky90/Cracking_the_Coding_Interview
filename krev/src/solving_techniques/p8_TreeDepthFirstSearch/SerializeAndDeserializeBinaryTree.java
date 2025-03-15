package solving_techniques.p8_TreeDepthFirstSearch;

import data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.TreeNode;

import java.util.Stack;

/**
 * 297. Serialize and Deserialize Binary Tree (hard)
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree
 * <p>
 * #Company (15.03.2025):
 * <p>
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 * <p>
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 * <p>
 * Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * Input: root = [1,2,3,null,null,4,5]
 * Output: [1,2,3,null,null,4,5]
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 104].
 * -1000 <= Node.val <= 1000
 */
public class SerializeAndDeserializeBinaryTree {
    /**
     * NOTE: I invented my approach of serialization:
     * like //1(2(N)(N))(3(4(N)(N))(5(N)(N)))
     *
     * time to solve ~ 56 mins
     *
     * 1 attempt:
     *
     * BEATS ~ 69%
     *
     * BUT too complex: instead of parsing, we can just split by delimiter like ','
     * without any brackets
     */
    private static final TreeNode dummy = new TreeNode();

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        preOrder(root, sb);

        return sb.toString();
    }

    private void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("N");
            return;
        }

        sb.append(root.val);

        sb.append("(");
        preOrder(root.left, sb);
        sb.append(")");

        sb.append("(");
        preOrder(root.right, sb);
        sb.append(")");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Stack<TreeNode> stack = new Stack<>();
        char[] arr = data.toCharArray();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while (i < arr.length) {
            if (Character.isDigit(arr[i]) || arr[i] == '-') {
                while (i < arr.length && (Character.isDigit(arr[i]) || arr[i] == '-')) {
                    sb.append(arr[i]);
                    i++;
                }

                TreeNode node = new TreeNode(Integer.valueOf(sb.toString()));
                //add dummy children which will be replaced with null in case if (N)
                node.left = dummy;
                node.right = dummy;
                stack.add(node);

                sb.setLength(0);
            } else if (arr[i] == 'N') {
                sb.append("N");
                i++;
            } else if (arr[i] == ')') {
                if ("N".equals(sb.toString())) {
                    TreeNode topNode = stack.peek();
                    if (topNode.left == dummy) {
                        topNode.left = null;
                    } else {
                        topNode.right = null;
                    }
                    sb.setLength(0);
                } else {
                    //otherwise sb is expected to be empty
                    TreeNode node = stack.pop();
                    TreeNode parent = stack.peek();
                    if (parent.left == dummy) {
                        parent.left = node;
                    } else {
                        parent.right = node;
                    }
                }

                i++;
            } else {
                i++;
            }
        }

        if ("N".equals(sb.toString())) return null;

        return stack.peek();
    }

    /**
     * BUT my solution is too complex: instead of parsing, we can just split by delimiter like ','
     * without any brackets
     *
     * so Official solution is like the below one
     *
     * time to implement ~ 17 mins
     *
     * BEATS ~ 96%
     */
    // Encodes a tree to a single string.
    public String serialize2(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        preOrder2(root, sb);

        return sb.toString();
    }

    private void preOrder2(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("N,");
        } else {
            sb.append(root.val).append(",");
            preOrder2(root.left, sb);
            preOrder2(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        String[] splitted = data.split(",");

        return dfsDeserialization(splitted);
    }

    private int pointer = 0;

    private TreeNode dfsDeserialization(String[] splitted) {
        if ("N".equals(splitted[pointer])) {
            pointer++;
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.valueOf(splitted[pointer]));
            pointer++;
            node.left = dfsDeserialization(splitted);
            node.right = dfsDeserialization(splitted);

            return node;
        }
    }

}
