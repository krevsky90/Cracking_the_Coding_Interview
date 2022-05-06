package data_structures.chapter4_trees_n_graphs;

/**
 * p.122
 * Successor: Write an algorithm to find the "next" node (i .e., in-order successor) of a given node in a
 * binary search tree. You may assume that each node has a link to its parent.
 * Hints: #79, #91
 * <p>
 * ASSUMPTION/VALIDATION:
 */

/**
 * In-Order successor (т.е. преемник) in BST для некой ноды n – следующая нода, к-ая будет «напечатана»
 */
public class Problem4_6 {
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
        n22.parent = n20;
        n8.left = n4;
        n8.right = n12;
        n8.parent = n20;
        n4.left = n2;
        n4.right = n6;
        n4.parent = n8;
        n12.left = n10;
        n12.right = n14;
        n12.parent = n8;
        n2.parent = n4;
        n6.parent = n4;
        n10.parent = n12;
        n14.parent = n12;

        Node result = findInOrderSuccessor(n12);
        System.out.println(result == null ? null : result.value);
    }

    /**
     * KREVSKY SOLUTION
     */
    public static Node findInOrderSuccessor(Node node) {
        if (node == null) throw new IllegalArgumentException();

        //left sub-tree is not interesting for us

        //look at right sub-tree
        if (node.right != null) {
            return leftMostChildKrev(node.right);
        }

        //look at the parent:
        //if node is left child of its parent - return the parent
        //else (i.e. node is right child of its parent) - go through the tree up while parent != null
        //and try to find the parent that has this sub-tree as left and print this parent.
        //if parent = null then it means that the node doesn't have successor -> return null
        Node parent = findNextParent(node);
        return parent;
    }

    public static Node findNextParent(Node node) {
        Node parent = node.parent;
        if (parent == null) {   //i.e. node is root of the tree
            return null;
        }
        //check it node is left child of the parent
        if (parent.left == node) return parent;

        while (parent != null && parent.parent != null) {
            Node prevParent = parent;
            parent = prevParent.parent;
            if (parent.left == prevParent) return parent;
        }
        return null;    //node doesn't have successor
    }

    public static Node leftMostChildKrev(Node node) {
        if (node != null) {
            Node leftNode = leftMostChildKrev(node.left);
            if (leftNode != null) return leftNode;
            return node;
            //we won't reach right subtree since node != null and will be printed before right sub-tree
        }
        return null;
    }

    /**
     * ORIGINAL SOLUTION
     */
    public static Node inorderSucc(Node n) {
        if (n == null) return null;

        /* Found right children -> return leftmost node of right subtree. */
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            Node q = n;
            Node x = q.parent;
            // Go up until we're on left instead of right
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            return x;
        }
    }

    private static Node leftMostChild(Node n) {
        if (n == null) {
            return null;
        }
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }

    public static class Node {
        public Node left;
        public Node right;
        public Node parent;
        public int value;

        public Node(int v) {
            this.value = v;
        }
    }
}
