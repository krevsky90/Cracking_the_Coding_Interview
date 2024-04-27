https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63989f4374c44bdc8812984b

8. Tree Depth First Search
Usage: As the name suggests, this technique is used to solve problems involving traversing trees in depth-first search manner.

MUST know
1) In-Order Morris Traversal - see implementation here: data_structures/chapter4_trees_n_graphs/amazon_igotanoffer/medium_trees/Problem2_14_Recover_BST # morrisInOrderTraversalCommonAlgorithm()
    video: https://www.youtube.com/watch?v=wGXB9OWhPTg
        OR https://www.youtube.com/watch?v=BuI-EULsz0Y
    // time complexity O(n)
    // space complexity O(1) since we change tree in-place
    public void morrisInOrderTraversalCommonAlgorithm(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.println(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;    //create loop in the tree
                    current = current.left;
                } else { //i.e. predecessor.right = current, i.e. loop in the tree, we have already visited this node!
                    predecessor.right = null;   //remove loop in the tree
                    System.out.println(current.val);
                    current = current.right;
                }
            }
        }
    }

2) Pre-Order Morris Traversal:
see video: https://youtu.be/BuI-EULsz0Y?t=506
THE SAME as In-Order, BUT the row "System.out.println(current.val);"
moves to case "predecessor.right == null"
(i.e. we print at the same time when we create loop rather than return to the node from its left child that it already printed

    public void morrisPreOrderTraversalCommonAlgorithm(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                System.out.println(current.val);
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;    //create loop in the tree
                    System.out.println(current.val);
                    current = current.left;
                } else { //i.e. predecessor.right = current, i.e. loop in the tree, we have already visited this node!
                    predecessor.right = null;   //remove loop in the tree
                    current = current.right;
                }
            }
        }
    }

NOTE: Morris traversal is used for
    1) flatten Tree to Linked List (Pre-Order)
    2) Recover BST (In-Order)
    3) Validate BST (In-Order)


Sequence of problems:
1) Binary Tree Path Sum (easy) - done
2) All Paths for a Sum (medium) - done
3) Sum of Path Numbers (medium) - done
4) Path With Given Sequence (medium) - done
5) Count Paths for a Sum (medium) - done
6) Problem Challenge 1: Tree Diameter (medium) - done
7) Problem Challenge 2: Path with Maximum Sum (hard) - done

8) https://leetcode.com/problems/longest-univalue-path (medium) - done
9) https://leetcode.com/problems/symmetric-tree (easy) - done

List of problems from leetcode: https://leetcode.com/tag/depth-first-search/

10) Convert Binary Tree To Doubly Linked List By Morris Traversal - done
11) 235. Lowest Common Ancestor of a Binary Search Tree - done
12) https://leetcode.com/problems/balanced-binary-tree (easy) - done
13) https://leetcode.com/problems/binary-tree-inorder-traversal (easy) (Morris In-order traversal) - done
