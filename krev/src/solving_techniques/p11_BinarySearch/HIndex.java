package solving_techniques.p11_BinarySearch;

/**
 * 274. H-Index (medium)
 * https://leetcode.com/problems/h-index
 * <p>
 * #Company (26.06.2025): 0 - 3 months Microsoft 2 0 - 6 months Google 2 Bloomberg 2 6 months ago Amazon 18 Meta 5 Adobe 5 Nvidia 4 Apple 3 Uber 3 Yahoo 3 LinkedIn 2 ByteDance 2 Zoox 2
 * <p>
 * Given an array of integers citations where citations[i] is the number of citations a researcher
 * received for their ith paper, return the researcher's h-index.
 * <p>
 * According to the definition of h-index on Wikipedia:
 * The h-index is defined as the maximum value of h such that the given researcher has published
 * at least h papers that have each been cited at least h times.
 * <p>
 * Example 1:
 * <p>
 * Input: citations = [3,0,6,1,5]
 * Output: 3
 * Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively.
 * Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, their h-index is 3.
 * <p>
 * Example 2:
 * Input: citations = [1,3,1]
 * Output: 1
 * <p>
 * Constraints:
 * n == citations.length
 * 1 <= n <= 5000
 * 0 <= citations[i] <= 1000
 */
public class HIndex {
    /**
     * KREVSKY SOLUTION:
     * idea: binary search with validate() method
     * <p>
     * time to solve ~ 13 mins
     * time ~ O(N*logN)
     * space ~ O(1)
     * <p>
     * 1 attempt:
     * <p>
     * BEATS ~ 100%
     */
    public int hIndex(int[] citations) {
        //solution #1.1: use binary search
        int low = -1;
        int high = 1001;
        while (high - low > 1) {
            int mid = low + (high - low) / 2;
            if (!validate(mid, citations)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }

    // return true if amount of citations that >= mid is >= mid
    private boolean validate(int h, int[] arr) {
        int cnt = 0;
        for (int a : arr) {
            if (a >= h) cnt++;
        }

        return cnt >= h;
    }

    public static void main(String[] args) {
        HIndex obj = new HIndex();
        int[] arr  = {3,0,6,1,5};
        obj.hIndex2(arr);
    }

    public int hIndex2(int[] citations) {
        //solution #1.2: use binary search
        int low = 0;
        int high = 1000;
        int res = 0;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (validate(mid, citations)) {
                res = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return res;
    }
}
