package data_structures.chapter4_trees_n_graphs;

/**
 * p.122
 * Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
 * this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 * node never differ by more than one.
 * Hints: #21, #33, #49, #105, #124
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_4 {
    public static void main(String[] args) {
        Node n12 = new Node(2);
        Node n13 = new Node(3);
        Node n14 = new Node(4);
        Node n15 = new Node(5);
        Node n16 = new Node(6);
//        Node n17 = new Node(7);

        n12.left = n13;
        n13.left = n14;
        n12.right = n15;
        n15.right = n16;
//        n16.right = n17;

        System.out.println(isBalanced2(n12));

    }

    /**
     * ORIGINAL SOLUTION #1:
     * On each node, we recurse through its entire subtree. This means that getHeight is called repeatedly on the same nodes.
     * The algorithm is O(N log N) since each node is "touched" once per node above it.
     */
    public static int getHeight(Node top) {
        if (top == null) {
            return -1;   //or 0, it doesn't matter!
        }

        int leftHeight = getHeight(top.left);
        int rightHeight = getHeight(top.right);

        return Math.max(leftHeight, rightHeight) + 1;   //+1 because current tree is higher of max(left,right), because of existing of top node
    }

    public static boolean isBalanced1(Node top) {
        if (top == null) return true;

        int diff = getHeight(top.left) - getHeight(top.right);
        if (Math.abs(diff) > 1) {
            return false;
        } else {
            return isBalanced1(top.left) && isBalanced1(top.right);
        }
    }

    /**
     * ORIGINAL SOLUTION #2 (optimized):
     * time - O(n), space - O(h), where h - height of the tree
     */
    public static boolean isBalanced2(Node root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    /**
     * ORIGINAL SOLUTION:
     * time - O(n), space - O(h), where h - height of the tree
     * Примем, что если для определенного узла обнаружили, что его поддерево НЕсбалансировано,
     * то возвращаем ошибку в виде Integer.MIN_VALUE и прокидываем ее наверх, чтоб вернуть как итоговый результат
     */
    public static int checkHeight(Node node) {
        if (node == null) {
            return -1;   //or 0, it doesn't matter!
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;   // pass error up
        }
        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;   // pass error up
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            //tree is unbalanced
            return Integer.MIN_VALUE;   //found error -> pass it back
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}