package live_coding.uber;

import java.util.*;

/**
 * this problem I got 6.01.2025 on the real interview to Uber
 */
public class ScheduleMeetingAPI {
    private Map<String, List<long[]>> roomToMeetings;

    public ScheduleMeetingAPI(List<String> rooms) {
        roomToMeetings = new HashMap<>();
        for (String room : rooms) {
            roomToMeetings.put(room, new ArrayList<>());
        }
    }

    public String scheduleMeeting(long startTime, long endTime) {
        for (String room : roomToMeetings.keySet()) {
            long[] newInterval = new long[]{startTime, endTime};
            if (isAvailable(room, newInterval)) {
                roomToMeetings.get(room).add(newInterval);
                return room;
            }
        }

        return "no room";    //there is no available room
    }

    private boolean isAvailable(String room, long[] newInterval) {
        for (long[] interval : roomToMeetings.get(room)) {
            if (collision(interval, newInterval)) return false;
        }

        return true;
    }

    private boolean collision(long[] a, long[] b) {
        return Math.max(a[0], b[0]) < Math.min(a[1], b[1]);
    }

    public static void main(String[] args) {
        ScheduleMeetingAPI obj = new ScheduleMeetingAPI(Arrays.asList("room1", "room2"));
        long[][] requests = new long[][]{{1,4},{7,8},{3,5},{9,10},{4,7}, {4, 6}, {9, 11}};
        for (long[] r : requests) {
            System.out.println(obj.scheduleMeeting(r[0], r[1]));
        }
    }
}
