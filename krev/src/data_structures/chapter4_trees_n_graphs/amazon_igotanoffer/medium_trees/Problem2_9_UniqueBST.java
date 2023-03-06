package data_structures.chapter4_trees_n_graphs.amazon_igotanoffer.medium_trees;

/**
 * https://igotanoffer.com/blogs/tech/tree-interview-questions
 * https://leetcode.com/problems/unique-binary-search-trees/description/
 * <p>
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.
 * <p>
 * Example 1:
 * Input: n = 3
 * Output: 5
 * <p>
 * Example 2:
 * Input: n = 1
 * Output: 1
 * <p>
 * Constraints:
 * 1 <= n <= 19
 */
public class Problem2_9_UniqueBST {
    /**
     * KREVSKY SOLUTION:
     * ideas:
     * 1) dynamic programming
     * 2) for each root (i.e. i-th element of the array {1..n}) we know that all elements that placed left (i.e. less) from i-th element will be left subtree
     * and bigger elements - will be in the right sub-tree
     * 3) for each i-th element that is root: if we have k different left subtrees of BST and s different right subtrees of BST,
     * then the total amount of different BSTs (with root = i) is left*right
     * <p>
     * time to think ~ 30 mins
     * time to solve ~ 6 mins
     * time to debug ~ 9 mins
     * 1 attempt
     * seems not optimal because it doesn't store previous results (see Official solution)
     */
    public int numTreesKREV(int n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        return counter(0, n);
    }

    //Input: n = 3
    //Output: 5
    //counter({1,2,3}, 0, 3) =
    //   i = 0 => 1*(1 + 1) = 2
    //   left = counter({{1,2,3},0,0}) = 1
    //   right = counter({1,2,3},1,3) = 2
    //      i = 1:
    //      left = counter({1,2,3},1,1) = 1
    //      right = counter({1,2,3},2,3) = 1
    //      i = 2:
    //      left = counter({1,2,3},1,2) = 1
    //      right = counter({1,2,3},3,3) = 1
    //   i = 1 => 1*1 = 1
    //   left = counter({{1,2,3},0,1}) = 1
    //   right = counter({1,2,3},2,3) = 1
    //   i = 2  => 2
    //   ...
    //
    //   result = 2 + 1 + 2 = 5
    //

    //left - inclusively, right - exclusively
    public int counter(int left, int right) {
        if (right - left <= 1) {
            //empty subtree or the only one element -> return 1
            return 1;
        }

        int result = 0;
        for (int i = left; i < right; i++) {
            int leftCounter = counter(left, i);
            int rightCounter = counter(i + 1, right);
            result += leftCounter * rightCounter;
        }

        return result;
    }

    /**
     * Official solution
     * https://leetcode.com/problems/unique-binary-search-trees/solutions/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
     * <p>
     * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
