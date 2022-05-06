package data_structures.chapter4_trees_n_graphs;

/**
 * p.122
 * 4.10. Check Subtree: Tl and T2 are two very large binary trees, with Tl much bigger than T2. Create an
 * algorithm to determine if T2 is a subtree of Tl.
 * A tree T2 is a subtree ofTi if there exists a node n in Ti such that the subtree of n is identical to T2.
 * That is, if you cut off the tree at node n, the two trees would be identical.
 * Hints: #4, #11, #18, #31, #37
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_10 {
    /**
     * ORIGINAL SOLUTION:
     * The idea is to use traversal view for T1 and T2, compare string representations using 'contains'.
     * If we use InOrder - it will be bad, since each BST has the same traversal even if BSTs have different structures.
     * 1 <- 2 -> 3 and 3 <- 2 <- 1
     * That's why we will use PreOrder. We will have the problems with duplicates (1 <- 2 -> 1 and 2 -> 1 -> 1)
     * BUT if we insert some 'empty' node in all places when there is no right and/or left node,
     * we will see the differences between the trees
     * */
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(1);
        Node n6 = new Node(6);
        Node n7 = new Node(1);

        n4.left = n2;
        n4.right = n6;
        n2.left = n1;
        n2.right = n3;
        n6.left = n5;
        n6.right = n7;

        Node tree1 = n4;
        Node tree2good = n6;

        Node n5bad = new Node(1);
        Node n6bad = new Node(6);
        Node n7bad = new Node(1);
        n5bad.right = n6bad;
        n6bad.right = n7bad;
        Node tree2bad = n5bad;

        StringBuilder sb1 = new StringBuilder();
        preOrderTraversal(tree1, sb1);
        StringBuilder sb2 = new StringBuilder();
        preOrderTraversal(tree2good, sb2);

        boolean r = sb1.toString().contains(sb2.toString());
        System.out.println(r);

    }

    public static final Node nullNode = new Node(Integer.MIN_VALUE);

    public static void preOrderTraversal(Node root, StringBuilder result) {
        if (root != null) {
            result.append(root.value);
            preOrderTraversal(root.left, result);
            preOrderTraversal(root.right, result);
        } else {
            result.append(nullNode.value);
        }
    }
}
