package solving_techniques.p4_MergeIntervals;

import java.util.PriorityQueue;

/**
 * 1109. Corporate Flight Bookings (medium)
 * https://leetcode.com/problems/corporate-flight-bookings
 * <p>
 * #Company (4.07.2025): 0 - 3 months Google 2 6 months ago Amazon 6 Goldman Sachs 3
 *
 * There are n flights that are labeled from 1 to n.
 *
 * You are given an array of flight bookings bookings,
 *      where bookings[i] = [firsti, lasti, seatsi] represents a booking for flights firsti through lasti (inclusive) with seatsi seats reserved for each flight in the range.
 *
 * Return an array answer of length n, where answer[i] is the total number of seats reserved for flight i.
 *
 * Example 1:
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 * Explanation:
 * Flight labels:        1   2   3   4   5
 * Booking 1 reserved:  10  10
 * Booking 2 reserved:      20  20
 * Booking 3 reserved:      25  25  25  25
 * Total seats:         10  55  45  25  25
 * Hence, answer = [10,55,45,25,25]
 *
 * Example 2:
 * Input: bookings = [[1,2,10],[2,2,15]], n = 2
 * Output: [10,25]
 * Explanation:
 * Flight labels:        1   2
 * Booking 1 reserved:  10  10
 * Booking 2 reserved:      15
 * Total seats:         10  25
 * Hence, answer = [10,25]
 *
 * Constraints:
 * 1 <= n <= 2 * 10^4
 * 1 <= bookings.length <= 2 * 10^4
 * bookings[i].length == 3
 * 1 <= firsti <= lasti <= n
 * 1 <= seatsi <= 10^4
 */
public class CorporateFlightBookings {
    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 25 mins
     * <p>
     * idea: sweep line
     * Events for start and end of booking
     * PQ of available events
     * tempSum
     * <p>
     * 1 attempt
     * BEATS ~ 28%
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        //sweeping line
        PriorityQueue<Event> available = new PriorityQueue<>((a, b) -> a.pos - b.pos);  //min start is in the top (to include ASAP)

        for (int[] booking : bookings) {
            available.add(new Event(booking[0], booking[2]));
            available.add(new Event(booking[1] + 1, -booking[2]));  //negative value means the booking stops to be available, starting from booking[1] + 1 position
        }

        int[] result = new int[n];
        int tempSum = 0;
        for (int i = 1; i <= n; i++) {
            //include relevant events:
            while (!available.isEmpty() && available.peek().pos == i) {
                Event toInclude = available.poll();
                tempSum += toInclude.value;
            }

            result[i - 1] = tempSum;
        }

        return result;
    }

    class Event {
        int pos;
        int value;  // > 0 means start, < 0 means end

        Event(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }
    }

    /**
     * Solution #2:
     * use 2 PQ (available and used), BUT too complicated to create Event to used etc...
     *
     * BEATS ~ 28%
     */
    public int[] corpFlightBookings2(int[][] bookings, int n) {
        //sweeping line
        PriorityQueue<Event2> available = new PriorityQueue<>((a, b) -> a.pos - b.pos);  //min start is in the top (to include ASAP)
        PriorityQueue<Event2> used = new PriorityQueue<>((a, b) -> a.pos - b.pos);   //min end is in the top (to exclude if necessary)

        for (int i = 0; i < bookings.length; i++) {
            available.add(new Event2(bookings[i][0], i));
        }

        int[] result = new int[n];
        int tempSum = 0;
        for (int i = 1; i <= n; i++) {
            //exclude not relevant events:
            while (!used.isEmpty() && used.peek().pos < i) {
                Event2 event = used.poll();
                int[] booking = bookings[event.index];
                tempSum -= booking[2];    //i.e. -= value of booking
            }

            //include relevant events:
            while (!available.isEmpty() && available.peek().pos == i) {
                Event2 event = available.poll();
                int[] booking = bookings[event.index];
                tempSum += booking[2];    //i.e. += value of booking
                used.add(new Event2(booking[1], event.index));  //create new Event for [end of booking, booking id]
            }

            result[i - 1] = tempSum;
        }

        return result;
    }

    class Event2 {
        int pos;    //start or end
        int index;  //index of booking in bookings array

        Event2(int pos, int index) {
            this.pos = pos;
            this.index = index;
        }
    }
}
