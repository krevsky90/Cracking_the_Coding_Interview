package data_structures.chapter1_arrays_n_strings.extra;

/**
 * 849. Maximize Distance to Closest Person (medium)
 * https://leetcode.com/problems/maximize-distance-to-closest-person
 * <p>
 * #Company: Amazon Google Yandex
 * <p>
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat,
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to the closest person.
 * <p>
 * Example 1:
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation:
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 * <p>
 * Example 2:
 * Input: seats = [1,0,0,0]
 * Output: 3
 * Explanation:
 * If Alex sits in the last seat (i.e. seats[3]), the closest person is 3 seats away.
 * This is the maximum distance possible, so the answer is 3.
 * <p>
 * Example 3:
 * Input: seats = [0,1]
 * Output: 1
 * <p>
 * Constraints:
 * 2 <= seats.length <= 2 * 10^4
 * seats[i] is 0 or 1.
 * At least one seat is empty.
 * At least one seat is occupied.
 */
public class MaximizeDistanceToClosestPerson {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * calc max Distance = max(maxLeftDistance, maxRightDistance, maxMiddleDistance),
     * where:
     * maxLeftDistance - distance from left side of array to the most left '1' element
     * maxRightDistance - distance from right side of array to the most right '1' element
     * maxMiddleDistance - max distance between two neighbour '1' (if arr has more than one '1'), BUT divided by 2
     * <p>
     * time ~ O(n)
     * time to solve ~ 15 mins + 3 mins (check)
     * <p>
     * 1 attempt:
     * <p>
     * BEATS ~ 98%
     */
    public int maxDistToClosest(int[] seats) {
        int maxMiddleDistance = Integer.MIN_VALUE;
        int seat1 = -1;
        int seat2 = -1;
        int leftSeat = -1;
        int rightSeat = -1;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (seat1 == -1) {
                    seat1 = i;
                    leftSeat = i;
                    rightSeat = i;
                } else if (seat2 == -1) {
                    seat2 = i;
                    rightSeat = i;
                    maxMiddleDistance = Math.max(maxMiddleDistance, seat2 - seat1);
                } else {
                    seat1 = seat2;
                    seat2 = i;
                    rightSeat = i;
                    maxMiddleDistance = Math.max(maxMiddleDistance, seat2 - seat1);
                }
            }
        }

        int maxLeftDistance = leftSeat;
        int maxRightDistance = seats.length - rightSeat - 1;

        int result = Math.max(maxLeftDistance, Math.max(maxMiddleDistance / 2, maxRightDistance));
        return result;
    }

    /**
     * KREVSKY #2: 15.10.2024
     * BEATS ~ 36%
     */
    public int maxDistToClosest2(int[] seats) {
        int leftMost = -1;
        int rightMost = -1;
        int start = -1;
        int maxMiddleDistance = 0;

        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (leftMost == -1) leftMost = i;
                rightMost = Math.max(rightMost, i);

                if (start != -1) {
                    maxMiddleDistance = Math.max(maxMiddleDistance, (i - start) / 2);
                }
                start = i;
            }
        }

        int leftDist = leftMost;
        int rightDist = seats.length - 1 - rightMost;
        return Math.max(maxMiddleDistance, Math.max(leftDist, rightDist));
    }
}
