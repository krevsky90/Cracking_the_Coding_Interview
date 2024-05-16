package solving_techniques.different;
/**
 * 528. Random Pick with Weight
 * https://leetcode.com/problems/random-pick-with-weight
 * #Company: Apple
 * <p>
 * You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
 * <p>
 * You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1] (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
 * <p>
 * For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%), and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["Solution","pickIndex"]
 * [[[1]],[]]
 * Output
 * [null,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1]);
 * solution.pickIndex(); // return 0. The only option is to return 0
 * since there is only one element in w.
 * <p>
 * Example 2:
 * Input
 * ["Solution","pickIndex","pickIndex","pickIndex","pickIndex","pickIndex"]
 * [[[1,3]],[],[],[],[],[]]
 * Output
 * [null,1,1,1,1,0]
 * <p>
 * Explanation
 * Solution solution = new Solution([1, 3]);
 * solution.pickIndex(); // return 1. It is returning the second element (index = 1) that has a probability of 3/4.
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 1
 * solution.pickIndex(); // return 0. It is returning the first element (index = 0) that has a probability of 1/4.
 * <p>
 * Since this is a randomization problem, multiple answers are allowed.
 * All of the following outputs can be considered correct:
 * [null,1,1,1,1,0]
 * [null,1,1,1,1,1]
 * [null,1,1,1,0,0]
 * [null,1,1,1,0,1]
 * [null,1,0,1,0,0]
 * ......
 * and so on.
 * <p>
 * Constraints:
 * 1 <= w.length <= 10^4
 * 1 <= w[i] <= 10^5
 * pickIndex will be called at most 104 times.
 */
public class RandomPickWithWeight {
    /**
     * NOT SOLVED by myself
     * info:
     * https://www.youtube.com/watch?v=3xCqA-IJ7gU
     * or
     * https://leetcode.com/problems/random-pick-with-weight/solutions/3861387/beats-90-binary-search-java/
     *
     * time ~ O(n) + O(logn) ~ O(n)
     * space ~ O(n)
     */
    private double[] intervals;

    public static void main(String[] args) {
        RandomPickWithWeight obj = new RandomPickWithWeight(new int[]{3, 14, 1, 7});
        System.out.println(obj.pickIndex());
        System.out.println(obj.pickIndex());
        System.out.println(obj.pickIndex());
        System.out.println(obj.pickIndex());
    }

    //time ~ O(n)
    //space ~ O(n)
    public RandomPickWithWeight(int[] w) {
        int sum = 0;
        for (int tmp : w) {
            sum += tmp;
        }

        double[] probabilities = new double[w.length];
        for (int i = 0; i < w.length; i++) {
            probabilities[i] = 1.0 * w[i] / sum;
        }

        intervals = new double[w.length];  //store right bounds of intervals
        intervals[0] = probabilities[0];
        for (int i = 1; i < w.length; i++) {
            intervals[i] = intervals[i - 1] + probabilities[i];
        }
    }

    public int pickIndex() {
        double rand = Math.random();
        // apply binary search to find appropriate interval
        // => its index = index of element from w => return this index
        int left = 0;
        int right = intervals.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid] < rand) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
