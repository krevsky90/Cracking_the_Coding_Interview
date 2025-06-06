package solving_techniques.p4_MergeIntervals.theory;

import java.util.*;

/**
 * qwen. example
 */
public class NonOverlappingIntervalsSweepLine {
    static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }

    static class Event {
        int time;
        int index;       // Index of the interval in original list
        boolean isStart;

        public Event(int time, int index, boolean isStart) {
            this.time = time;
            this.index = index;
            this.isStart = isStart;
        }
    }

    public static int countNonOverlapping(List<Interval> intervals) {
        int n = intervals.size();
        List<Event> events = new ArrayList<>();

        // Step 1: Create events
        for (int i = 0; i < n; i++) {
            Interval interval = intervals.get(i);
            events.add(new Event(interval.start, i, true));
            events.add(new Event(interval.end, i, false));
        }

        // Step 2: Sort events
        events.sort((a, b) -> {
            if (a.time != b.time) {
                return Integer.compare(a.time, b.time);
            } else {
                // End before start at same time
                return Boolean.compare(a.isStart, b.isStart);
            }
        });

        // To track number of active intervals per index
        int[] overlapCount = new int[n];
        Set<Integer> activeIndices = new HashSet<>();

        // Step 3: Process events
        for (Event event : events) {
            Interval currentInterval = intervals.get(event.index);

            if (event.isStart) {
                // Before adding, check if any active interval overlaps
                for (int idx : activeIndices) {
                    overlapCount[event.index]++;
                    overlapCount[idx]++;
                }
                activeIndices.add(event.index);
            } else {
                activeIndices.remove(event.index);
            }
        }

        //KREV: here have amount of overlappings for EACH interval

        // Step 4: Count intervals with zero overlaps
        int result = 0;
        for (int count : overlapCount) {
            if (count == 0) result++;
        }

        return result;
    }

    public static void main(String[] args) {
        List<Interval> intervals = Arrays.asList(
                new Interval(1, 8),
                new Interval(2, 3),
                new Interval(6, 10)
        );

        int count = countNonOverlapping(intervals);
        System.out.println("Number of non-overlapping intervals: " + count); // Should be 0
    }
}
