package solving_techniques.p8_TreeDepthFirstSearch;

/**
 * https://www.tryexponent.com/practice/prepare/largest-smaller-bst-key
 * <p>
 * Given a root of a Binary Search Tree (BST) and a number num,
 * implement an efficient function findLargestSmallerKey that finds the largest key in the tree that is smaller than num.
 * If such a number doesn't exist, return -1. Assume that all keys in the tree are nonnegative.
 * <p>
 * Analyze the time and space complexities of your solution.
 * <p>
 * For example:
 * <p>
 * For num = 17 and the binary search tree below:
 * 20(9(5)(12(11)(14)))(25)
 * <p>
 * Your function would return:
 * 14 since it’s the largest key in the tree that is still smaller than 17.
 * <p>
 * Constraints:
 * [time limit] 5000ms
 * [input] Node rootNode
 * [output] integer
 */
public class LargestSmallerBSTKey {
    static class Node {
        int key;
        Node left;
        Node right;
        Node parent;

        Node(int key) {
            this.key = key;
            left = null;
            right = null;
            parent = null;
        }
    }

    static class BinarySearchTree {

        Node root;

        /**
         * KREVSKY SOLUTION #1:
         * CODE  - starts
         * time ~ O(H), H - height of tree
         * space ~ O(H), H - height of tree, since we store recursive stack
         */
        int findLargestSmallerKey(int num) {
            return dfs(root, num);
        }

        int dfs(Node currentNode, int num) {
            if (currentNode == null) return -1;

            if (currentNode.key >= num) {
                return dfs(currentNode.left, num);
            } else {
                int dfsRes = dfs(currentNode.right, num);
                //despite we have BST and all in-order traversal returns sorted increasing sequence, we might have the situation when dfs returns -1;
                return Math.max(currentNode.key, dfsRes);
            }
        }

        /**
         * time ~ O(h)
         * space ~ O(1)
         */
        int findLargestSmallerKey2(int num) {
            int result = -1;
            Node cur = root;
            while (cur != null) {
                if (cur.key < num) {
                    result = cur.key;
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }

            return result;
        }

        /**
         * CODE - ends
         */

        //  inserts a new node with the given number in the
        //  correct place in the tree
        void insert(int key) {

            // 1) If the tree is empty, create the root
            if (this.root == null) {
                this.root = new Node(key);
                return;
            }

            // 2) Otherwise, create a node with the key
            //    and traverse down the tree to find where to
            //    to insert the new node
            Node currentNode = this.root;
            Node newNode = new Node(key);

            while (currentNode != null) {
                if (key < currentNode.key) {
                    if (currentNode.left == null) {
                        currentNode.left = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.left;
                    }
                } else {
                    if (currentNode.right == null) {
                        currentNode.right = newNode;
                        newNode.parent = currentNode;
                        break;
                    } else {
                        currentNode = currentNode.right;
                    }
                }
            }
        }
    }

    /*********************************************
     * Driver program to test above function     *
     *********************************************/

    public static void main(String[] args) {

        // Create a Binary Search Tree
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(20);
        bst.insert(9);
        bst.insert(25);
        bst.insert(5);
        bst.insert(12);
        bst.insert(11);
        bst.insert(14);

        int result = bst.findLargestSmallerKey(17);
        System.out.println("Largest smaller number is " + result);

    }
}
