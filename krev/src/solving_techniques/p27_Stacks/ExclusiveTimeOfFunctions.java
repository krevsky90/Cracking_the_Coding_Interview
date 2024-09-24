package solving_techniques.p27_Stacks;

import java.util.List;
import java.util.Stack;

/**
 * 636. Exclusive Time of Functions (medium)
 * https://leetcode.com/problems/exclusive-time-of-functions
 * <p>
 * #Company: Amazon Facebook Google LinkedIn Microsoft Oracle Uber
 * <p>
 * On a single-threaded CPU, we execute a program containing n functions.
 * Each function has a unique ID between 0 and n-1.
 * <p>
 * Function calls are stored in a call stack:
 * when a function call starts, its ID is pushed onto the stack,
 * and when a function call ends, its ID is popped off the stack.
 * <p>
 * The function whose ID is at the top of the stack is the current function being executed.
 * Each time a function starts or ends, we write a log with the ID,
 * whether it started or ended, and the timestamp.
 * <p>
 * You are given a list logs, where logs[i] represents the ith log message
 * formatted as a string "{function_id}:{"start" | "end"}:{timestamp}".
 * <p>
 * For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3,
 * and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2.
 * Note that a function can be called multiple times, possibly recursively.
 * <p>
 * A function's exclusive time is the sum of execution times for all function calls in the program.
 * For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit,
 * the exclusive time is 2 + 1 = 3.
 * <p>
 * Return the exclusive time of each function in an array,
 * where the value at the ith index represents the exclusive time for the function with ID i.
 * <p>
 * Example 1:
 * Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
 * Output: [3,4]
 * Explanation:
 * Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
 * Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
 * Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
 * So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.
 * Example 2:
 * <p>
 * Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
 * Output: [8]
 * Explanation:
 * Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
 * Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
 * Function 0 (initial call) resumes execution then immediately calls itself again.
 * Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
 * Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
 * So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
 * Example 3:
 * <p>
 * Input: n = 2, logs = ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]
 * Output: [7,1]
 * Explanation:
 * Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
 * Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
 * Function 0 (initial call) resumes execution then immediately calls function 1.
 * Function 1 starts at the beginning of time 6, executes 1 unit of time, and ends at the end of time 6.
 * Function 0 resumes execution at the beginning of time 6 and executes for 2 units of time.
 * So function 0 spends 2 + 4 + 1 = 7 units of total time executing, and function 1 spends 1 unit of total time executing.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= n <= 100
 * 1 <= logs.length <= 500
 * 0 <= function_id < n
 * 0 <= timestamp <= 10^9
 * No two start events will happen at the same timestamp.
 * No two end events will happen at the same timestamp.
 * Each function has an "end" log for each "start" log.
 */
public class ExclusiveTimeOfFunctions {
    /**
     * KREVSKY SOLUTION:
     * idea: use Stack and store {id, timeslot}
     * + logic if - elseif (see the code)
     * <p>
     * time to solve ~ 40 mins
     * time ~
     * <p>
     * several attempts since stored String[], but then changes to int[]
     * <p>
     * BEATS ~ 27% time, 18% memory
     */
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<int[]> stack = new Stack<>();    //store ID + timeslot from log record

        for (String log : logs) {
            String[] arr = log.split(":");    //expected 3 elements: ID + operation + timeslot
            int id = Integer.valueOf(arr[0]);
            int timeslot = Integer.valueOf(arr[2]);
            if (stack.isEmpty()) {
                stack.add(new int[]{id, timeslot});
            } else if ("start".equals(arr[1])) {
                int delta = timeslot - stack.peek()[1];
                result[stack.peek()[0]] += delta;

                stack.add(new int[]{id, timeslot});
            } else if ("end".equals(arr[1])) {
                //NOTE: can add extra validation of ID, but it should be the same as for stack.peek()[0]

                //1. update duration for finished process: end - start + 1
                int endTimeslot = Integer.valueOf(stack.pop()[1]);
                int delta = timeslot - endTimeslot + 1;
                result[id] += delta;

                //2. update start time for the process that becomes executable after that
                if (!stack.isEmpty()) {
                    stack.peek()[1] = timeslot + 1;
                }
            }
        }

        return result;
    }

    /**
     * optimized based on https://www.youtube.com/watch?v=CBJI_lZxYU8&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy
     * idea: store prevStartTime in separate variable, no in the stack
     *
     * BEAT ~ 52% time, 49% memory
     */
    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();    //store ID
        int prevStartTime = 0;

        for (String log : logs) {
            String[] arr = log.split(":");    //expected 3 elements: ID + operation + timeslot
            int id = Integer.valueOf(arr[0]);
            int timeslot = Integer.valueOf(arr[2]);
            if ("start".equals(arr[1])) {
                if (!stack.isEmpty()) {
                    result[stack.peek()] += timeslot - prevStartTime;
                }

                stack.add(id);
                prevStartTime = timeslot;
            } else {
                //i.e. "end"
                //NOTE: can add extra validation of ID, but it should be the same as for stack.peek()[0]

                //1. update duration for finished process: end - start + 1
                result[stack.pop()] += timeslot - prevStartTime + 1;

                //2. update start time for the process that becomes executable after that
                prevStartTime = timeslot + 1;
            }
        }

        return result;
    }
}
