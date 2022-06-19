package data_structures.chapter10_sorting_n_searching;

/**
 * p.163
 * 10.10 Rank from Stream:
 * Imagine you are reading in a stream of integers. Periodically, you wish
 * to be able to look up the rank of a number x (the number of values less than or equal to x).
 * Implement the data structures and algorithms to support these operations. That is, implement
 * the method track(int x), which is called when each number is generated, and the method
 * getRankOfNumber(int x), which returns the number of values less than or equal to x (not
 * including x itself).
 * EXAMPLE
 * Stream (in order of appearance): 5, 1, 4, 4, 5, 9, 7, 13, 3
 * getRankOfNumber(l) = 0
 * getRankOfNumber(3) = 1
 * getRankOfNumber(4) = 3
 * <p>
 * ASSUMPTION/VALIDATION:
 */
public class Problem10_10 {
    /**
     * ORIGINAL SOLUTION:
     * data structure to store - binary search tree, each node stores:
     * 1) int value
     * 2) size of left subtree
     */
    public static RankNode root;

    public static void main(String[] args) {
        int[] input = new int[]{5, 1, 4, 4, 5, 9, 7, 13, 3};
        //todo: attention! 4 and 4 results in getRankOfNumber(4) = 2 rather than 3. so I don't see how it can be handled, because I don't understand 'the number of values less than or equal to x (not
        // * including x itself).'

        for (int i = 0; i < input.length; i++) {
            track(input[i]);
        }

        System.out.println(getRankOfNumber(1));
        System.out.println(getRankOfNumber(3));
        System.out.println(getRankOfNumber(9));
    }

    // time complexity O(logN)
    public static void track(int d) {
        if (root == null) {
            root = new RankNode(d);
        } else {
            root.insert(d);
        }
    }

    // time complexity O(logN)
    public static int getRankOfNumber(int d) {
        return root.getRank(d);
    }

    static class RankNode {
        public int leftsize = 0;
        public RankNode left, right;
        public int data = 0;

        public RankNode(int d) {
            this.data = d;
        }

        // time complexity O(logN)
        public void insert(int d) {
            if (this.data > d) {
                if (left == null) {
                    left = new RankNode(d);
                } else {
                    left.insert(d);
                }
                this.leftsize++;
            } else {
                if (right == null) {
                    right = new RankNode(d);
                } else {
                    right.insert(d);
                }
            }
        }

        // time complexity O(logN)
        public int getRank(int d) {
            if (this.data == d) {
                return this.leftsize;
            } else if (this.data > d) {
                if (this.left == null) {
                    return -1;  //i.e. node with d-value can not be found in the tree
                }
                return this.left.getRank(d);
            } else {    //d > data
                if (this.right == null) {
                    return -1;  //i.e. node with d-value can not be found in the tree
                }

                //the MAIN idea!
                return leftsize + 1 + this.right.getRank(d);
            }
        }
    }
}
