package solving_techniques.p11_ModifiedBinarySearch;

/**
 * 1891. Cutting Ribbons (medium) (locked)
 * https://leetcode.com/problems/cutting-ribbons/
 * OR
 * https://leetcode.ca/2021-07-24-1891-Cutting-Ribbons/
 * <p>
 * Description
 * You are given an integer array ribbons, where ribbons[i] represents the length of the i-th  ribbon,
 * and an integer k. You may cut any of the  ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
 * <p>
 * For example, if you have a ribbon of length 4, you can:
 * Keep the ribbon of length 4,
 * Cut it into one ribbon of length 3 and one ribbon of length 1,
 * Cut it into two ribbons of length 2,
 * Cut it into one ribbon of length 2 and two ribbons of length 1, or
 * Cut it into four ribbons of length 1.
 * Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.
 * <p>
 * Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.
 * <p>
 * Example 1:
 * Input: ribbons = [9,7,5], k = 3
 * Output: 5
 * <p>
 * Explanation:
 * Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
 * Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
 * Keep the third ribbon as it is.
 * Now you have 3 ribbons of length 5.
 * <p>
 * Example 2:
 * Input: ribbons = [7,5,9], k = 4
 * Output: 4
 * <p>
 * Explanation:
 * Cut the first ribbon to two ribbons, one of length 4 and one of length 3.
 * Cut the second ribbon to two ribbons, one of length 4 and one of length 1.
 * Cut the third ribbon to three ribbons, two of length 4 and one of length 1.
 * Now you have 4 ribbons of length 4.
 * <p>
 * Example 3:
 * Input: ribbons = [5,7,9], k = 22
 * Output: 0
 * <p>
 * Explanation: You cannot obtain k ribbons of the same positive integer length.
 * <p>
 * Constraints:
 * 1 <= ribbons.length <= 10^5
 * 1 <= ribbons[i] <= 10^5
 * 1 <= k <= 10^9
 */
public class CuttingRibbons {
    public static void main(String[] args) {
        CuttingRibbons obj = new CuttingRibbons();
        int[] arr2 = {7,5,9};
        int k2 = 4;
        System.out.println(obj.countRibbonsByLength(arr2, k2));
    }

    /**
     * KREVSKY SOLUTION + tips from https://www.youtube.com/watch?v=ha8RA6ZpRyY&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=22
     * time to solve + debug ~ 25 mins
     * ideas: see in the code
     *
     * time ~ O(N*logX), where N = ribbons.length, X = initial 'right' - 1
     * space ~ O(1)
     */
    public int maxLength(int[] ribbons, int k) {
//        int sum = 0;
        int right = -1;
        for (int ribbon : ribbons) {
//            sum += ribbon;
            right = Math.max(right, ribbon);
        }

//        if (sum < k) return 0;

        int left = 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (countRibbonsByLength(ribbons, mid) < k) {
                right = mid - 1;
            } else {
                //idea #1
                left = mid + 1;
            }
        }

        //idea #2: return right, but not left! it is better to understand on the example!  [5,7,9], k = 22
        return right;

    }

    private int countRibbonsByLength(int[] ribbons, int len) {
        int sum = 0;
        for (int ribbon : ribbons) {
            sum += ribbon / len;
        }
        return sum;
    }
}
