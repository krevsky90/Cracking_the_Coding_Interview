package solving_techniques.segment_tree;

/**
 * 307. Range Sum Query - Mutable (medium)
 * https://leetcode.com/problems/range-sum-query-mutable/
 * <p>
 * #Company (4.07.2025): 6 months ago Google 2 Bloomberg 2 TikTok 2
 * <p>
 * Given an integer array nums, handle multiple queries of the following types:
 * <p>
 * Update the value of an element in nums.
 * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
 * Implement the NumArray class:
 * <p>
 * NumArray(int[] nums) Initializes the object with the integer array nums.
 * void update(int index, int val) Updates the value of nums[index] to be val.
 * int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive
 * (i.e. nums[left] + nums[left + 1] + ... + nums[right]).
 * <p>
 * Example 1:
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * At most 3 * 10^4 calls will be made to update and sumRange.
 */
public class RangeSumQueryMutable {
    /**
     * NAIVE approach:
     * to iterate the array from index i to j and sum each element.
     * time to query ~ O(n)
     * time to update ~ O(1)
     * space ~ O(1)
     */
    /**
     * KREVKSY SOLUTION:
     * time to solve ~ 45 mins
     * <p>
     * 2 attempts:
     * - set 2*treeIdx + 1 to merge(..) instead of tree[2*treeIdx + 1]
     * <p>
     * BEATS ~ 36%
     */
    private int[] nums;
    private int[] tree;

    public RangeSumQueryMutable(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        //why 4*n? https://stackoverflow.com/questions/28470692/how-is-the-memory-of-the-array-of-segment-tree-2-2-ceillogn-1
        this.tree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1);
    }

    private void buildSegmentTree(int treeIdx, int low, int high) {
        if (low == high) {
            tree[treeIdx] = nums[low];
            return;
        }

        int mid = low + (high - low) / 2;
        buildSegmentTree(2 * treeIdx + 1, low, mid);
        buildSegmentTree(2 * treeIdx + 2, mid + 1, high);

        tree[treeIdx] = merge(tree[2 * treeIdx + 1], tree[2 * treeIdx + 2]);
    }

    //the SAME method for build/update/query calls!
    private int merge(int node1, int node2) {
        return node1 + node2;
    }

    public void update(int index, int val) {
        update(0, 0, nums.length - 1, index, val);
    }

    private void update(int treeIdx, int low, int high, int index, int val) {
        if (low == high) {
            //i.e. low = high = index
            tree[treeIdx] = val;    //NOTE: we update tree array, BUT NOT nums!
            return;
        }

        //like search in binary tree
        int mid = low + (high - low) / 2;
        if (mid < index) {
            //go to the right
            update(2 * treeIdx + 2, mid + 1, high, index, val);
        } else {
            // mid >= index, go to the left
            update(2 * treeIdx + 1, low, mid, index, val);
        }

        tree[treeIdx] = merge(tree[2 * treeIdx + 1], tree[2 * treeIdx + 2]);
    }

    public int sumRange(int left, int right) {
        return sumRange(0, 0, nums.length - 1, left, right);
    }

    private int sumRange(int treeIdx, int low, int high, int left, int right) {
        //requested range is out of [low, high] range that has data
        if (right < low || high < left) return 0;

        if (left == low && right == high) {
            //requested range is the same as [low, high] => tree node contains the answer
            return tree[treeIdx];
        }

        int mid = low + (high - low) / 2;
        if (mid < left) {
            //requested range if in is right subtree of treeIdx node
            return sumRange(2 * treeIdx + 2, mid + 1, high, left, right);
        } else if (right <= mid) {
            //NOTE: <= and 'mid' is considered
            return sumRange(2 * treeIdx + 1, low, mid, left, right);
        } else {
            //some elements are in the left subtree, some elements - in the right subtree
            int leftQuery = sumRange(2 * treeIdx + 1, low, mid, left, mid);
            int rightQuery = sumRange(2 * treeIdx + 2, mid + 1, high, mid + 1, right);

            return merge(leftQuery, rightQuery);
        }
    }
}
