package solving_techniques.p11_BinarySearch;

import java.util.Arrays;

/**
 * 825. Friends Of Appropriate Ages (medium)
 * https://leetcode.com/problems/friends-of-appropriate-ages
 * <p>
 * #Company (21.03.2025): 0 - 3 months Meta 4 6 months ago Adobe 2
 * <p>
 * There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.
 * <p>
 * A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
 * <p>
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * Otherwise, x will send a friend request to y.
 * <p>
 * Note that if x sends a request to y, y will not necessarily send a request to x.
 * Also, a person will not send a friend request to themself.
 * <p>
 * Return the total number of friend requests made.
 * <p>
 * Example 1:
 * Input: ages = [16,16]
 * Output: 2
 * Explanation: 2 people friend request each other.
 * <p>
 * Example 2:
 * Input: ages = [16,17,18]
 * Output: 2
 * Explanation: Friend requests are made 17 -> 16, 18 -> 17.
 * <p>
 * Example 3:
 * Input: ages = [20,30,100,110,120]
 * Output: 3
 * Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.
 * <p>
 * Constraints:
 * n == ages.length
 * 1 <= n <= 2 * 10^4
 * 1 <= ages[i] <= 120
 */
public class FriendsOfAppropriateAges {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 35 + 15 = 50 mins
     * idea:
     * binary search to find the bounds for each person
     * <p>
     * NOTE: condition "age[y] > 100 && age[x] < 100" is useless! it just confuses
     * <p>
     * time ~ O(n*logn)
     * space ~ O(1)
     * <p>
     * BEATS ~ 5%
     */
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);  // O(n*logn)
        int result = 0;
        for (int i = 0; i < ages.length; i++) {
            result += binary(ages, 0.5 * ages[i] + 7, ages[i], i);
        }

        return result;
    }

    //lower < y <= upper, i.e. 0.5*age[x] + 7 < age[y] <= age[x]
    private int binary(int[] ages, double lower, int upper, int pos) {
        if (lower >= upper) return 0;

        int left = 0;
        int right = pos;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ages[mid] <= lower) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int leftBound = left;

        //since the numbers are not unique => find the right most position of the number that = upper
        left = pos;
        right = ages.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (ages[mid] == upper) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        int rightBound = right;

        return rightBound - leftBound;
    }

    /**
     * Optimal (official) solution
     * idea:
     * pay attention to constraints: age max = 120 => we can calculate amount of people that have i-th age
     * After that operate these counters (with unique positions)
     * <p>
     * time ~ O(N + A*A), where A = 120
     * space ~ O(A) - to keep counters
     * <p>
     * time to implement ~ 23 mins
     * <p>
     * BEATS ~ 98%
     */
    public int numFriendRequestsBuckets(int[] ages) {
        int a = 120;
        int[] counters = new int[a + 1];
        //time ~ O(N)
        for (int i = 0; i < ages.length; i++) {
            counters[ages[i]]++;
        }

        int result = 0;
        //0.5 * age[x] + 7 < ages[y] <= ages[x]
        //i.e. ages[x] > 14
        //time ~ O(A*A)
        for (int c = 15; c < a + 1; c++) {
            double lowerD = 0.5 * c + 7;
            int lower = (int) Math.ceil(lowerD);
            if (lower == lowerD) lower++;

            int upper = c;

            int tempResult = 0;
            for (int j = lower; j < upper; j++) {
                tempResult += counters[j];
            }

            //since each friend from c-th cell will invite all people who are in lower < .. < upper cells
            tempResult *= counters[upper];
            //becides, each friend from c-th cell will invite people from the same cell (i.e. counters[c] - 1 people)
            tempResult += counters[c] * (counters[c] - 1);

            result += tempResult;
        }

        return result;
    }

    /**
     * Official solution
     */
    public int numFriendRequestsOfficial(int[] ages) {
        int[] count = new int[121];
        for (int age : ages) count[age]++;

        int ans = 0;
        for (int ageA = 0; ageA <= 120; ageA++) {
            int countA = count[ageA];
            for (int ageB = 0; ageB <= 120; ageB++) {
                int countB = count[ageB];
                if (ageA * 0.5 + 7 >= ageB) continue;
                if (ageA < ageB) continue;
                if (ageA < 100 && 100 < ageB) continue;
                ans += countA * countB;

                //If ageA == ageB, then we overcounted:
                // we should have countA * (countA - 1) pairs of people making friend requests instead,
                // as you cannot friend request yourself.
                if (ageA == ageB) ans -= countA;
            }
        }

        return ans;
    }
}
