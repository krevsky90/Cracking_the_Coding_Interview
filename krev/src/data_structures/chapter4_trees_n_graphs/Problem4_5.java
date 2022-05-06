package data_structures.chapter4_trees_n_graphs;

/**
 * p.122
 * Validate BST: Implement a function to check if a binary tree is a binary search tree.
 * Hints: #35, #57, #86, #113, #128
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_5 {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);

        //correct BST
        n4.left = n2;
        n2.left = n1;
        n2.right = n3;
        n4.right = n6;
        n6.left = n5;
        n6.right = n7;

        //incorrect BST
//        n4.left = n2;
//        n2.left = n3;
//        n4.right = n1;


        System.out.println(isBST(n4));
        System.out.println(isBST2(n4));
    }

    /**
     * KREVSKY SOLUTION #1
     */
    public static boolean isBST(Node top) {
        return inOrderTraversal(top) != Integer.MIN_VALUE;
    }

    public static int inOrderTraversal(Node top) {
        if (top != null) {
            int leftValue = inOrderTraversal(top.left);
            int value = top.value;
            boolean leftCheck = leftValue == -1 ? true : leftValue <= value;

            int rightValue = inOrderTraversal(top.right);
            boolean rightCheck = rightValue == -1 ? true : value < rightValue;

            if (!(leftCheck && rightCheck)) {
                return Integer.MIN_VALUE;   //like error code;
            } else {
                return value;
            }
        }
        return -1;  // top is null
    }

    /**
     * KREVSKY SOLUTION #2
     */

    public static boolean isBST2(Node top) {
        return inOrderTraversal2(top);
    }

    public static boolean inOrderTraversal2(Node top) {
        if (top != null) {
            boolean leftCheck = inOrderTraversal2(top.left);
            if (!leftCheck) {
                return false;
            }
            int value = top.value;
            boolean rightCheck = inOrderTraversal2(top.right);
            if (!rightCheck) {
                return false;
            }

            boolean left = top.left == null ? true : top.left.value <= value;
            boolean right = top.right == null ? true : value < top.right.value;
            return left && right;
        }
        return true;    //null tree is BST
    }

    /**
     * ORIGINAL SOLUTION #1:
     * the idea: track the last element we saw and compare it as we go
     */
    public static Integer last_printed = null;
    public static boolean checkBST(Node n) {
        if (n == null) return true;

        //check recurse left
        if (!checkBST(n.left)) return false;

        //check current
        if (last_printed != null && n.value <= last_printed) {
            return false;
        }

        last_printed = n.value;

        //check recurse right
        if (!checkBST(n.right)) return false;

        return true;    //all good!
    }

}
