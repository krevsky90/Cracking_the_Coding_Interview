package solving_techniques.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1353. Maximum Number of Events That Can Be Attended (medium)
 * https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/
 * <p>
 * #Company (25.07.2025): 0 - 3 months Google 9 Amazon 7 Meta 5 Microsoft 4 Nvidia 3 PayPal 3 0 - 6 months Snowflake 3 Gopuff 3 Bloomberg 2 Zoho 2 6 months ago Oracle 4 Visa 3 Apple 2 Uber 2 Instacart 2 Yahoo 2
 * <p>
 * You are given an array of events where events[i] = [startDayi, endDayi].
 * Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startDayi <= d <= endDayi.
 * You can only attend one event at any time d.
 * Return the maximum number of events you can attend.
 * <p>
 * Example 1:
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 * <p>
 * Example 2:
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 * <p>
 * Constraints:
 * 1 <= events.length <= 10^5
 * events[i].length == 2
 * 1 <= startDayi <= endDayi <= 105
 */
public class MaximumNumberOfEventsThatCanBeAttended {
    /**
     * NOT SOLVED by myself
     * idea:
     * 1) sort events by start time - got this idea
     * 2) find maxDay
     * 3) create PQ (min heap) ordered by min endDay
     * 4) traverse from 0 to maxDay, keeping eventNumber pointing to the event in events array:
     * - add events (or its endDay) to PQ, if events[eventNumber]'s start <= curDay
     * - remove events from PQ that are in the past (i.e. their endDay < curDay)
     * - count event that is on top of PQ (if it exists)
     * <p>
     * time to think + read solution + implement ~ 30 + 10 + 10 mins ~ 50 mins
     * <p>
     * time ~ O((maxDay + events.length)*log(events.length))
     * space ~ O(events.length)
     * <p>
     * 2 attempts:
     * - need curDay <= maxDay, but not curDay < maxDay
     * <p>
     * BEATS ~ 66%
     */
    public int maxEvents(int[][] events) {
        int maxDay = -1;
        for (int[] event : events) {
            maxDay = Math.max(maxDay, event[1]);
        }

        //sort by start day
        Arrays.sort(events, (a, b) -> a[0] - b[0]);

        int cnt = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);    //min heap by end day
        for (int curDay = 0, eventNumber = 0; curDay <= maxDay; curDay++) {
            //add potential events to queue
            while (eventNumber < events.length && events[eventNumber][0] <= curDay) {
                pq.add(events[eventNumber]);
                eventNumber++;
            }

            //remove old events from the queue
            while (!pq.isEmpty() && pq.peek()[1] < curDay) {
                pq.poll();
            }

            //take top event from the queue
            if (!pq.isEmpty()) {
                pq.poll();
                cnt++;
            }
        }

        return cnt;
    }
}
