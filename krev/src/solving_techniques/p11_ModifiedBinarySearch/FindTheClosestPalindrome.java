package solving_techniques.p11_ModifiedBinarySearch;


import java.util.ArrayList;
import java.util.List;

/**
 * 564. Find the Closest Palindrome (hard)
 * https://leetcode.com/problems/find-the-closest-palindrome/
 * <p>
 * #Company: 0 - 3 months Uber 6 0 - 6 months Amazon 4 Google 3 Microsoft 3 Goldman Sachs 3 Yelp 2 6 months ago Oracle 2 PayPay 2
 * <p>
 * Given a string n representing an integer, return the closest integer (not including itself), which is a palindrome.
 * If there is a tie, return the smaller one.
 * <p>
 * The closest is defined as the absolute difference minimized between two integers.
 * <p>
 * Example 1:
 * Input: n = "123"
 * Output: "121"
 * <p>
 * Example 2:
 * Input: n = "1"
 * Output: "0"
 * Explanation: 0 and 2 are the closest palindromes but we return the smallest which is 0.
 * <p>
 * Constraints:
 * 1 <= n.length <= 18
 * n consists of only digits.
 * n does not have leading zeros.
 * n is representing an integer in the range [1, 10^18 - 1].
 */
public class FindTheClosestPalindrome {
    /**
     * NOT SOLVED by myself
     * main idea:
     * find first half ot the number and reflect it to make a palindrome
     * BUT there are 5 cases:
     * idea 1: Create a palindrome by mirroring the first half.
     * idea 2: Create a palindrome by mirroring the first half incremented by 1 - for cases like 139 -> 141, not 131
     * idea 3: Create a palindrome by mirroring the first half decremented by 1 - for cases like 222 -> 212
     * idea 4: edge case like 1000 -> 999
     * idea 5: edge case like 99 -> 101
     * <p>
     * then we just find the closest of these candidates, excluding case when candidate = n
     * <p>
     * time ~ O(log_10_n) => O(k), where k - number of digits in n number
     * space ~ O(n)
     * <p>
     * BEATS ~ 97%
     */
    public String nearestPalindromic(String n) {
        int len = n.length();
        int halfLen = len % 2 == 0 ? len / 2 : len / 2 + 1; // since for 123 we will look at 12, for 1234 we will look at 12
        long half = Long.parseLong(n.substring(0, halfLen));
        List<Long> opportunities = new ArrayList<>();
         /*
        Generate possible palindromic candidates:
        1. Create a palindrome by mirroring the first half.
        2. Create a palindrome by mirroring the first half incremented by 1.
        3. Create a palindrome by mirroring the first half decremented by 1.
        4. Handle edge cases by considering palindromes of the form 999...
           and 100...001 (smallest and largest n-digit palindromes).
        */
        opportunities.add(halfToPalindrome(half, len % 2 == 0));    //#idea 1
        opportunities.add(halfToPalindrome(half + 1, len % 2 == 0));    //#idea 2
        opportunities.add(halfToPalindrome(half - 1, len % 2 == 0));    //#idea 3
        opportunities.add((long) Math.pow(10, len - 1) - 1);    //#idea 4: like 10 -> 9
        opportunities.add((long) Math.pow(10, len) + 1);    //#idea 5: like 99 -> 101

        //find the closest opportunity
        long delta = Long.MAX_VALUE;
        long result = -1;
        long nLong = Long.parseLong(n);
        for (Long k : opportunities) {
            if (k == nLong) continue;   //since if n is palindrome, we ignore it

            if (Math.abs(nLong - k) < delta) {
                delta = Math.abs(nLong - k);
                result = k;
            } else if (Math.abs(nLong - k) == delta) {
                //find min value
                result = Math.min(result, k);
            }
        }

        return String.valueOf(result);
    }

    //12 -> 1221
    private long halfToPalindrome(long left, boolean even) {
        long result = left;

        if (!even) {
            left = left / 10;   //to exclude middle element (of the initial string) from mirroring
        }

        while (left > 0) {
            long remainder = left % 10;

            result = 10 * result + remainder;
            left = left / 10;
        }

        return result;
    }

    /**
     * SOLUTION #2:
     * idea:
     * 1) find first half ot the number and reflect it to make a palindrome
     * 2) use Binary search to generate (using idea #1) the smallest palindrome that is bigger than n
     * and the largest palindrome that is less than n
     * <p>
     * Let n be the input number and k be the number of digits in it.
     * time ~ O(k⋅log(n))
     * space ~ O(k)
     */
    public String nearestPalindromic2(String n) {
        long num = Long.parseLong(n);
        long a = previousPalindrome(num);
        long b = nextPalindrome(num);
        if (Math.abs(a - num) <= Math.abs(b - num)) {
            return Long.toString(a);
        }
        return Long.toString(b);
    }

    // Convert to palindrome keeping first half constant.
    private long convert(long num) {
        String s = Long.toString(num);
        int n = s.length();
        int l = (n - 1) / 2, r = n / 2;
        char[] sArray = s.toCharArray();
        while (l >= 0) {
            sArray[r++] = sArray[l--];
        }
        return Long.parseLong(new String(sArray));
    }

    // Find the previous palindrome, just smaller than n.
    private long previousPalindrome(long num) {
        long left = 0, right = num;
        long ans = Long.MIN_VALUE;
        while (left <= right) {
            long mid = (right - left) / 2 + left;
            long palin = convert(mid);
            if (palin < num) {
                ans = palin;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    // Find the next palindrome, just greater than n.
    private long nextPalindrome(long num) {
        long left = num, right = (long) 1e18;
        long ans = Long.MIN_VALUE;
        while (left <= right) {
            long mid = (right - left) / 2 + left;
            long palin = convert(mid);
            if (palin > num) {
                ans = palin;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
