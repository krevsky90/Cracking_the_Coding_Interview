package solving_techniques.p2_TwoPointers;

/**
 * 556. Next Greater Element III (medium)
 * https://leetcode.com/problems/next-greater-element-iii
 * <p>
 * #Company (17.07.2025): 0 - 3 months Meta 6 DoorDash 4 Google 3 Amazon 3 Microsoft 2 6 months ago TikTok 8 Adobe 5 Bloomberg 3 Goldman Sachs 2 Zoho 2 Mitsogo 2
 * <p>
 * Given a positive integer n, find the smallest integer which has exactly the same digits existing in the integer n
 * and is greater in value than n. If no such positive integer exists, return -1.
 * <p>
 * Note that the returned integer should fit in 32-bit integer,
 * if there is a valid answer but it does not fit in 32-bit integer, return -1.
 * <p>
 * Example 1:
 * Input: n = 12
 * Output: 21
 * <p>
 * Example 2:
 * Input: n = 21
 * Output: -1
 * <p>
 * Constraints:
 * 1 <= n <= 2^31 - 1
 */
public class NextGreaterElement3 {
    /**
     * KREVSKY + OFFICIAL SOLUTION
     * time to solve ~ 40 mins
     * <p>
     * idea:
     * 1) how to transform digit into array: char[] arr = ("" + n).toCharArray();
     * 2) find 2 positions, swap them
     * 3) since right part was sorted and remains sorted in DESC order, we just need to reverse it in ASC order
     * <p>
     * time ~ O(n)
     * space ~ O(1)
     * <p>
     * many attempts:
     * - incorrect count of p2 (need to start with arr.length, this is simple
     */
    public int nextGreaterElement(int n) {
        char[] arr = ("" + n).toCharArray();

        //find p1
        int p1 = arr.length - 1;
        while (p1 - 1 >= 0 && arr[p1] <= arr[p1 - 1]) {
            p1--;
        }

        if (p1 == 0) return -1;

        p1--;

        //find p2: which is more right than p1 and its value the smallest value that is greater than p1'th value
        int p2 = arr.length - 1;
        while (p2 >= 0 && arr[p2] <= arr[p1]) {
            p2--;
        }

        //swap p1 and p2
        char buffer = arr[p1];
        arr[p1] = arr[p2];
        arr[p2] = buffer;

        //since right part was sorted and remains sorted in DESC order, we just need to reverse it in ASC order
        int start = p1 + 1;
        int end = arr.length - 1;
        while (start < end) {
            buffer = arr[start];
            arr[start] = arr[end];
            arr[end] = buffer;
            start++;
            end--;
        }

        try {
            return Integer.parseInt(new String(arr));
        } catch (Exception e) {
            return -1;
        }
    }

}
