package data_structures.chapter4_trees_n_graphs;

import java.util.Random;

/**
 * p.122
 * 4.11. Random Node:
 * You are implementing a binary tree class from scratch which, in addition to
 * insert, find, and delete, has a method getRandomNode() which returns a random node
 * from the tree. All nodes should be equally likely to be chosen. Design and implement an algorithm
 * for getRandomNode, and explain how you would implement the rest of the methods.
 * Hints: #42, #54, #62, #75, #89, #99, #112, #119
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem4_11 {
    public static void main(String[] args) {
//        TreeNode2 n20 = new TreeNode2(20);
//        TreeNode2 n10 = new TreeNode2(10);
//        TreeNode2 n5 = new TreeNode2(5);
//        TreeNode2 n3 = new TreeNode2(3);
//        TreeNode2 n7 = new TreeNode2(7);
//        TreeNode2 n15 = new TreeNode2(15);
//        TreeNode2 n17 = new TreeNode2(17);
//        TreeNode2 n30 = new TreeNode2(30);
//        TreeNode2 n35 = new TreeNode2(35);
//        n20.left = n10;
//        n20.right = n30;
//        n10.left = n5;
//        n10.right = n15;
//        n5.left = n3;
//        n5.right = n7;
//        n15.right = n17;
//        n30.right = n35;

        Tree tree = new Tree();
        tree.insertInOrder(20);
        tree.insertInOrder(10);
        tree.insertInOrder(5);
        tree.insertInOrder(3);
        tree.insertInOrder(7);
        tree.insertInOrder(15);
        tree.insertInOrder(17);
        tree.insertInOrder(30);
        tree.insertInOrder(35);

        for (int i = 0; i < tree.size(); i++) {
            System.out.println(i+1 + " " + tree.root.getIthNode(i));
        }
    }

    public static class Tree {
        public TreeNode2 root;

        public int size() {
            return root == null ? 0 : root.size;
        }

        public void insertInOrder(int value) {
            if (root == null) {
                root = new TreeNode2(value);
            } else {
                root.insertInOrder(value);
            }
        }

        // see https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
        public void delete(int v) {
            root = deleteKey(root, v);
        }

        public TreeNode2 deleteKey(TreeNode2 root, int v) {
            if (root == null) {
                return root;
            }

            if (v < root.value) {
                root = deleteKey(root.left, v);
            } else if (v > root.value) {
                root = deleteKey(root.right, v);
            } else {
                //if v = root.value the this is the node to be deleted!
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                }
                //if root has 2 children, then find in-order successor (min node in the right subtree) and set it instead of the root's value
                root.value = findInOrderSuccessorValue(root.right);
                //remove the original node (leaf) with min value from right subtree
                deleteKey(root.right, root.value);
            }
            return root;
        }

        private static int findInOrderSuccessorValue(TreeNode2 root) {
            TreeNode2 result = root;
            while (result.left != null) {
                result = result.left;
            }
            return result.value;
        }

        public TreeNode2 getRandomNode() {
            if (root == null) return null;

            int random = new Random(size()).nextInt();
            return root.getIthNode(random);
        }
    }

    public static class TreeNode2 {
        public int value;
        public TreeNode2 left;
        public TreeNode2 right;
        public int size = 0;

        public TreeNode2(int d) {
            value = d;
            size = 1;
        }

        public void insertInOrder(int d) {
            if (d < value) {
                if (left == null) {
                    left = new TreeNode2(d);
                } else {
                    left.insertInOrder(d);
                }
            } else {
                if (right == null) {
                    right = new TreeNode2(d);
                } else {
                    right.insertInOrder(d);
                }
            }
            size++;
        }

        public TreeNode2 find(int d) {
            if (d == value) {
                return this;
            } else if (d < value) {
                return left == null ? null : left.find(d);
            } else {
                return right == null ? null : right.find(d);
            }
        }

        /**
         * ORIGINAL SOLUTION #7
         */
        public TreeNode2 getIthNode(int i) {
            int leftSize = left == null ? 0 : left.size;
            if (i < leftSize) {
                return left.getIthNode(i);
            } else if (leftSize == i) {
                return this;
            } else {
                // Skipping over leftSize + 1 nodes, so subtract them
                return right.getIthNode(i - (leftSize + 1));
            }
        }

        public String toString() {
            return "" + value;
        }
    }

    public static class TreeNode1 {
        public int value;
        public TreeNode1 left;
        public TreeNode1 right;
        public int size = 0;

        public TreeNode1(int d) {
            value = d;
            size = 1;
        }

        public void insertInOrder(int d) {
            if (d < value) {
                if (left == null) {
                    left = new TreeNode1(d);
                } else {
                    left.insertInOrder(d);
                }
            } else {
                if (right == null) {
                    right = new TreeNode1(d);
                } else {
                    right.insertInOrder(d);
                }
            }
            size++;
        }

        public TreeNode1 find(int d) {
            if (d == value) {
                return this;
            } else if (d < value) {
                return left == null ? null : left.find(d);
            } else {
                return right == null ? null : right.find(d);
            }
        }

        /**
         * ORIGINAL SOLUTION #6
         */
        public TreeNode1 getRandomNode() {
            int leftSize = left == null ? 0 : left.size;
            int random = new Random(size).nextInt();
            if (leftSize < random) {
                return left.getRandomNode();
            } else if (leftSize == random) {
                return this;
            } else {
                return right.getRandomNode();
            }
        }

        public String toString() {
            return "" + value;
        }
    }
}


