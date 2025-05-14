package solving_techniques.p11_ModifiedBinarySearch;

/**
 * 875. Koko Eating Bananas (medium)
 * https://leetcode.com/problems/koko-eating-bananas
 *
 * #Company: Airbnb Facebook Google
 *
 * Koko loves to eat bananas.
 * There are n piles of bananas, the ith pile has piles[i] bananas.
 * The guards have gone and will come back in h hours.
 *
 * Koko can decide her bananas-per-hour eating speed of k.
 * Each hour, she chooses some pile of bananas and eats k bananas from that pile.
 * If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.
 *
 * Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.
 *
 * Return the minimum integer k such that she can eat all the bananas within h hours.
 *
 * Example 1:
 * Input: piles = [3,6,7,11], h = 8
 * Output: 4
 *
 * Example 2:
 * Input: piles = [30,11,23,4,20], h = 5
 * Output: 30
 *
 * Example 3:
 * Input: piles = [30,11,23,4,20], h = 6
 * Output: 23
 *
 * Constraints:
 * 1 <= piles.length <= 10^4
 * piles.length <= h <= 10^9
 * 1 <= piles[i] <= 10^9
 */
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] arr1 = {3, 6, 7, 11};
        int h1 = 8;

        int[] arr2 = {30, 11, 23, 4, 20};
        int h2 = 5;

        int[] arr3 = {30, 11, 23, 4, 20};
        int h3 = 6;

        KokoEatingBananas obj = new KokoEatingBananas();
        System.out.println(obj.minEatingSpeed(arr1, h1));
//        System.out.println(obj.minEatingSpeed(arr2, h2));
//        System.out.println(obj.minEatingSpeed(arr3, h3));
    }


    /**
     * NOT SOLVED by myself:
     * info:
     * https://www.youtube.com/watch?v=fmvQ-qq_dlM&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=17
     * idea:
     * 1) use binary search
     * 2) right = mid, but not mid - 1
     *
     * time to implement ~ 20 mins
     *
     * 5 attempts:
     * - incorrect attempt to count left more precisely
     * - incorrect "right = mid - 1". it should be right = mid
     * - incorrect condition "left <= right". due to "right = mid" it should be "left < right"
     * - forgot to multiply by 1.0 in Math.ceil
     *
     * BEATS ~ 25%
     */
    public int minEatingSpeed(int[] piles, int h) {
        int maxPile = -1;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int left = 1;   //Math.ceil(sum/((long) piles.length));
        int right = maxPile;

        //if iterate k from left to right => will take O(right - left) => O(n)
        //idea: use binary search to improve the solution to O(logn)
        //to avoid infinite loop (since we write right = mid), we need to use <, but not <=
        while (left < right) {
            int mid = (left + right) / 2;
            int sumHours = countHours(piles, mid);
            if (sumHours > h) {
                left = mid + 1; //i.e. need to increase the speed of eating of bananas
            } else {
                right = mid;    //do not write "right = mid - 1" since mid might be the solution
            }
        }

        return left;
    }

    /**
     * 2025/05/14
     * based on Mavrin https://www.youtube.com/watch?v=Kn2DtmsN8f8
     */
    public int minEatingSpeedMavrin(int[] piles, int h) {
        int maxPile = -1;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int left = 0;
        int right = maxPile + 1;

        //if iterate k from left to right => will take O(right - left) => O(n)
        //idea: use binary search to improve the solution to O(logn)
        //to avoid infinite loop (since we write right = mid), we need to use <, but not <=
        while (right - left > 1) {
            int mid = left + (right - left)/2;
            int sumHours = countHours(piles, mid);
            if (sumHours <= h) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return right;
    }
// [30,11,23,4,20], h = 6
// left = 23
// right = 25
// mid = 26
// sumH = 6


    private int countHours(int[] piles, int k) {
        int sumHours = 0;
        for (int pile : piles) {
            sumHours += Math.ceil(1.0 * pile / k);
        }
        return sumHours;
    }
}
