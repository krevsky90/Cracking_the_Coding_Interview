package solving_techniques.different;

/**
 * 1344. Angle Between Hands of a Clock (medium)
 * https://leetcode.com/problems/angle-between-hands-of-a-clock
 *
 * #Company: Facebook?
 * <p>
 * Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
 * Answers within 10-5 of the actual value will be accepted as correct.
 * <p>
 * Example 1:
 * Input: hour = 12, minutes = 30
 * Output: 165
 * <p>
 * Example 2:
 * Input: hour = 3, minutes = 30
 * Output: 75
 * <p>
 * Example 3:
 * Input: hour = 3, minutes = 15
 * Output: 7.5
 * <p>
 * Constraints:
 * 1 <= hour <= 12
 * 0 <= minutes <= 59
 */
public class AngleBetweenHandsOfAClock {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 8-10 mins
     * time ~ O(1)
     * space ~ O(1)
     * 2 attempts:
     * - forgot to cast to double
     *
     * BEATS ~ 100%
     */
    public double angleClock(int hour, int minutes) {
        double mAngle = 360.0 * minutes / 60;
        double hAngle = (hour * 360.0 / 12) % 360 + minutes / 60.0 * 360 / 12;
        double delta = Math.abs(mAngle - hAngle);
        return delta > 180 ? 360 - delta : delta;
    }
}
