package solving_techniques.p2_TwoPointers;

import java.util.HashSet;
import java.util.Set;

/**
 * 1650. Lowest Common Ancestor of a Binary Tree III (medium) (locked)
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii
 * OR
 * https://leetcode.ca/all/1650.html
 * <p>
 * #Company: Facebook LinkedIn Microsoft Yandex
 * <p>
 * Given two nodes of a binary tree p and q, return their lowest common ancestor (LCA).
 * <p>
 * Each node will have a reference to its parent node. The definition for Node is below:
 * class Node {
 * public int val;
 * public Node left;
 * public Node right;
 * public Node parent;
 * }
 * <p>
 * According to the definition of LCA on Wikipedia:
 * "The lowest common ancestor of two nodes p and q in a tree T is the lowest node that has both p and q as descendants
 * (where we allow a node to be a descendant of itself)."
 * <p>
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * <p>
 * Example 2:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5 since a node can be a descendant of itself according to the LCA definition.
 * <p>
 * <p>
 * Example 3:
 * Input: root = [1,2], p = 1, q = 2
 * Output: 1
 * <p>
 * Constraints:
 * The number of nodes in the tree is in the range [2, 10^5].
 * -10^9 <= Node.val <= 10^9
 * All Node.val are unique.
 * p != q
 * p and q exist in the tree.
 */
public class LowestCommonAncestor3 {
    public static void main(String[] args) {
        LowestCommonAncestor3 obj = new LowestCommonAncestor3();
        obj.test();
    }

    public void test() {
        Node n3 = new Node(3);
        Node n5 = new Node(5);
        Node n1 = new Node(1);
        Node n6 = new Node(6);
        Node n2 = new Node(2);
        Node n0 = new Node(0);
        Node n8 = new Node(8);
        Node n7 = new Node(7);
        Node n4 = new Node(4);

        n3.left = n5;
        n3.right = n1;
        n5.parent = n3;
        n5.left = n6;
        n5.right = n2;
        n1.parent = n3;
        n1.left = n0;
        n1.right = n8;
        n6.parent = n5;
        n2.parent = n5;
        n2.left = n7;
        n2.right = n4;
        n0.parent = n1;
        n8.parent = n1;
        n7.parent = n2;
        n4.parent = n2;

        Node res1 = lowestCommonAncestor1(n6, n4);
        Node res2 = lowestCommonAncestor2(n6, n4);
        System.out.println(res1.val);
        System.out.println(res2.val);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int val) {
            this.val = val;
        }
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * 1) save path nodes from, for example, p to root in Set
     * 2) traverse from q to root and each time check if current node is in Set
     * if yes - it is LCA
     * time to solve ~ 5 mins
     * time ~ O(n) - worst case: depth of tree = n and p is leaf
     * space ~ O(n) - worst case: depth of tree = n and p is leaf
     * <p>
     * 2 attempts:
     * - forgot "current = current.parent;" in the second while-loop
     */
    public Node lowestCommonAncestor1(Node p, Node q) {
        Set<Integer> set = new HashSet<>();
        Node current = p;
        while (current != null) {
            set.add(current.val);
            current = current.parent;
        }

        current = q;
        while (current != null) {
            if (set.contains(current.val)) {
                break;
            } else {
                current = current.parent;
            }
        }

        return current;
    }

    /**
     * SOLUTION #2:
     * info:
     * https://youtu.be/vZxxksAP8yk?list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&t=267
     * idea:
     * two pointers pCopy and qCopy - for p and q - that go from p/q to the root
     * once the pCopy = null it.... is set to q value!!! and continue going upward
     * the same for qCopy: once qCopy = null, set qCopy = p and continue: qCopy = qCopy.parent etc
     *
     * Once pCopy = qCopy => this node is LCA
     * Why are they become equals?
     * see the example:
     * p = 6
     * q = 4
     * pCopy: 6 5 3 null 4 2 5
     * qCopy: 4 2 5 3 null 6 5
     *           3
     *         /  \
     *       5     1
     *     / \    / \
     *   6    2  0   8
     *       / \
     *      7   4
     *
     * time ~ O(n)
     * space ~ O(1)
     */
    public Node lowestCommonAncestor2(Node p, Node q) {
        Node pCopy = p;
        Node qCopy = q;
        while (pCopy != qCopy) {
            if (pCopy != null) {
                pCopy = pCopy.parent;
            } else {
                pCopy = q;
            }

            if (qCopy != null) {
                qCopy = qCopy.parent;
            } else {
                qCopy = p;
            }
        }

        return pCopy;
    }

}
