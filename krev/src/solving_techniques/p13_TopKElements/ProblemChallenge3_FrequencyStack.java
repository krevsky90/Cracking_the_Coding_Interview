package solving_techniques.p13_TopKElements;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1eb62b231098e09f3cc06
 * OR
 * 895. Maximum Frequency Stack
 * https://leetcode.com/problems/maximum-frequency-stack (hard)
 *
 * #Company: Apple
 *
 * Design a stack-like data structure to push elements to the stack
 * and pop the most frequent element from the stack.
 *
 * Implement the FreqStack class:
 *
 * FreqStack() constructs an empty frequency stack.
 * void push(int val) pushes an integer val onto the top of the stack.
 * int pop() removes and returns the most frequent element in the stack.
 * If there is a tie for the most frequent element,
 *      the element closest to the stack's top is removed and returned.
 *
 * Constraints:
 * 0 <= val <= 10^9
 * At most 2 * 10^4 calls will be made to push and pop.
 * It is guaranteed that there will be at least one element in the stack before calling pop.
 */
public class ProblemChallenge3_FrequencyStack {
    public static void main(String[] args) {
        ProblemChallenge3_FrequencyStack stack = new ProblemChallenge3_FrequencyStack();
        stack.push(5);
        stack.push(7);
        stack.push(5);
        stack.push(7);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        //expected 5 7 5 4
    }

    /**
     * KREVSKY SOLUTION (except the idea with usage of valToFreqMap)
     * idea #1: use Max Heap (PriorityQueue) to get value with max frequency
     * idea #2: use value to frequency mapping - need to count frequency add put to the Heap
     * idea #3: use idx to compare the entities of the Heap in case if frequencies are the same
     *
     * push: time ~ O(logN) - add to pq
     * pop: time ~ O(logN) - poll to pq
     *
     * space ~ O(N)
     *
     * 1 attempt
     *
     * BEATS ~ 21%
     */
    //max heap: val, index, frequency
    private PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
        {
            int freqDelta = o2[2] - o1[2];
            if (freqDelta == 0) {
                return o2[1] - o1[1];
            } else {
                return freqDelta;
            }
        }
    );

    private Map<Integer, Integer> valToFreqMap = new HashMap<>();
    private int idx = 0;

    public ProblemChallenge3_FrequencyStack() {
    }

    public void push(int val) {
        //increase frequency of 'val' element
        int freq = valToFreqMap.getOrDefault(val, 0);
        valToFreqMap.put(val, freq + 1);

        //increase max index that exists in the stack
        idx++;

        pq.add(new int[]{val, idx, freq});
    }

    public int pop() {
        //NOTE: there is no sense to reduce idx!

        int val = pq.poll()[0];

        //decrease frequency of 'val' element:
        if (valToFreqMap.get(val) > 0) {
            valToFreqMap.put(val, valToFreqMap.get(val) - 1);
        }

        return val;
    }
}
