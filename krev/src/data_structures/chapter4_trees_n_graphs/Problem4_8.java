package data_structures.chapter4_trees_n_graphs;

/**
 * p.122
 * 4.8 First Common Ancestor:
 * Design an algorithm and write code to find the first common ancestor
 * of two nodes in a binary tree. Avoid storing additional nodes in a data structure.
 * NOTE: This is not necessarily a binary search tree.
 * Hints: # 10, #16, #28, #36, #46, #70, #80, #96
 * <p>
 * ASSUMPTION/VALIDATION:
 * 1) we DON't have link to parent node
 * 2) we have links to n1, n2 and root of the tree
 */
public class Problem4_8 {
    public static void main(String[] args) {
        /**
         *           20
         *         /   \
         *      _ 8 _   22
         *    /      \
         *   4       12
         *  / \     /  \
         * 2   6  10    14
         */
        Node n20 = new Node(20);
        Node n8 = new Node(8);
        Node n22 = new Node(22);
        Node n4 = new Node(4);
        Node n12 = new Node(12);
        Node n2 = new Node(2);
        Node n6 = new Node(6);
        Node n10 = new Node(10);
        Node n14 = new Node(14);

        n20.left = n8;
        n20.right = n22;
        n8.left = n4;
        n8.right = n12;
        n4.left = n2;
        n4.right = n6;
        n12.left = n10;
        n12.right = n14;

        Node result = findCommonAncestor(n4, n14, n20);
        Node result3 = commonAncestor(n20, n4, n14);
        System.out.println(result == null ? null : result.value);
        System.out.println(result3 == null ? null : result3.value);
    }

    /**
     * KREVSKY SOLUTION:
     * we need traverse the tree and return information
     * whether current subtree (including current node as top of this subtree) contains n1 and/or n2.
     * If it contains n1 and n2 - we also need save current node to the information.
     * The information should be propagated up and applied on the level above (if information doesn't contain the node).
     * Otherwise we just propagate the info as is (returning the node on the top of callstack)
     * The info can be stored in special object with fields: (bool find1, bool find2, Node n)
     * If current node or its left subtree or its right subtree contain n1, find1 = true
     * If current node or its left subtree or its right subtree contain n2, find2 = true
     * if find1 && find2 == true -> current node is common ancestor -> save it to info-object
     */
    public static Node findCommonAncestor(Node n1, Node n2, Node root) {
        if (root == n1 || root == n2) return root;

        Result result = preOrderTraversal(n1, n2, root);
        return result.node;
    }

    private static Result preOrderTraversal(Node n1, Node n2, Node top) {
        Result result = new Result();    //empty
        if (top != null) {
            Result left = preOrderTraversal(n1, n2, top.left);
            if (left.node != null) return left;        //throw the result up since common ancestor is found

            Result right = preOrderTraversal(n1, n2, top.right);
            if (right.node != null) return right;    //throw the result up since common ancestor is found

            //if current node or its left subtree or its right subtree contain n1, find1 = true
            boolean find1 = top == n1 || left.find1 || right.find1;
            //if current node or its left subtree or its right subtree contain n2, find2 = true
            boolean find2 = top == n2 || left.find2 || right.find2;

            if (find1 && find2) {
                //we found the required node!
                result = new Result(find1, find2, top);
            } else {
                result = new Result(find1, find2, null);
            }
        }
        return result;
    }

    private static class Result {
        public boolean find1;
        public boolean find2;
        public Node node;

        public Result(boolean find1, boolean find2, Node node) {
            this.find1 = find1;
            this.find2 = find2;
            this.node = node;
        }

        public Result() {
        }
    }

    /**
     * ORIGINAL SOLUTION #3:
     * Idea:
     * if p and q are both on the left of the node, go to the left branch to look for the common ancestor.
     * If they are both on the right, go to the right branch to look for the common ancestor.
     * When p and q are no longer on the same side, you must have found the first common ancestor!
     * <p>
     * Inefficient: we find the node in subtree again and again -> time = O(2n) + O(2n/2) + ... = O(4n) = O(n)
     */
    public static Node commonAncestor(Node root, Node p, Node q) {
        // Error check - one node is not in the tree.
        if (!covers(root, p) || !covers(root, q)) {
            return null;
        }
        return ancestorHelper(root, p, q);
    }

    public static Node ancestorHelper(Node root, Node p, Node q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        if (pIsOnLeft != qIsOnLeft) { // Nodes are on different side
            return root;
        }

        Node childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide, p, q);
    }

    public static boolean covers(Node root, Node p) {
        if (root == null) return false;
        if (root == p) return true;
        return covers(root.left, p) || covers(root.right, p);
    }

    /**
     * ORIGINAL SOLUTION #4 (optimized 3):
     *
     */
}
