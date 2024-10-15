package solving_techniques.different;

import java.util.*;

/**
 * 281. Zigzag Iterator (medium)
 * https://leetcode.com/problems/zigzag-iterator/ (blocked)
 * info: https://leetcode.ca/2016-09-06-281-Zigzag-Iterator/
 * <p>
 * #Company: Yandex
 * <p>
 * Given two 1d vectors, implement an iterator to return their elements alternately.
 * Example:
 * Input:
 * v1 = [1,2]
 * v2 = [3,4,5,6]
 * <p>
 * Output: [1,3,2,4,5,6]
 * <p>
 * Explanation: By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,3,2,4,5,6].
 * <p>
 * Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
 * <p>
 * Clarification for the follow up question:
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases.
 * If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:
 * <p>
 * Input:
 * [1,2,3]
 * [4,5,6,7]
 * [8,9]
 * Output: [1,4,8,2,5,9,3,6,7].
 */
public class ZigzagIterator {
    public static void main(String[] args) {
        List<Integer> v1 = Arrays.asList(1, 2);
        List<Integer> v2 = Arrays.asList(3, 4, 5, 6);

        ZigZagIterator1 z0 = new ZigZagIterator1(v1, v2);
        while (z0.hasNext()) {
            System.out.println(z0.next());
        }

        System.out.println("--------------");

        ZigzagIterator z1 = new ZigzagIterator(v1, v2);
        while (z1.hasNext()) {
            System.out.println(z1.next());
        }

        System.out.println("--------------");

        ZigZagIterator1 z2 = new ZigZagIterator1(v2, v1);
        while (z2.hasNext()) {
            System.out.println(z2.next());
        }
    }

    /**
     * KREVSKY SOLUTION #1:
     * or info https://ttzztt.gitbooks.io/lc/content/zigzag-iterator.html
     * time to solve ~ 20 mins
     * <p>
     * time ~ O(v1.length + v2.length)
     * space ~ O(v1.length + v2.length)
     * <p>
     * 3 attempts:
     * - incorrectly set "<=" to the condition "lists.get(i).size() > curPositions[i]"
     * - incorrect logic in hasNext() in general. rewrote
     */
    static class ZigZagIterator1 {
        private List<List<Integer>> lists;
        private int[] curPositions;
        private int k;  //# of list

        public ZigZagIterator1(List<Integer> v1, List<Integer> v2) {
            lists = Arrays.asList(v1, v2);
            curPositions = new int[lists.size()];
            k = 0;
        }

        public boolean hasNext() {
            for (int i = 0; i < lists.size(); i++) {
                if (lists.get(i).size() > curPositions[i]) return true;
            }
            return false;
        }

        public int next() {
            k = k % lists.size();
            while (lists.get(k).size() <= curPositions[k]) {
                k++;
                k = k % lists.size();   //otherwise k might be = lists.size(). example: v1 = 1,2,3,4, v2=5,6
            }

            int res = lists.get(k).get(curPositions[k]);
            curPositions[k]++;
            k++;
            return res;
        }
    }

    /**
     * KREVSKY #2:
     * idea: initially (in construction) add all values from lists to one long list
     * <p>
     * time ~ O(v1.length + v2.length)
     * space ~ O(v1.length + v2.length)
     */
    private List<Integer> aggrList;
    private int itPos = 0;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        List<List<Integer>> lists = Arrays.asList(v1, v2);
        aggrList = new ArrayList<>();

        int maxLen = Integer.MIN_VALUE;
        for (List l : lists) {
            maxLen = Math.max(maxLen, l.size());
        }

        for (int i = 0; i < maxLen; i++) {
            for (int k = 0; k < lists.size(); k++) {
                if (i < lists.get(k).size()) {
                    aggrList.add(lists.get(k).get(i));
                }
            }
        }
    }

    public boolean hasNext() {
        return itPos < aggrList.size();
    }

    public int next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements");
        }
        return aggrList.get(itPos++);
    }

    /**
     * SOLUTION #3:
     * info: https://ttzztt.gitbooks.io/lc/content/zigzag-iterator.html
     * <p>
     * idea: store built-in iterators of each given list in Queue and use them!
     * It saves space => space ~ O(k), where k - number of iven lists
     */
    static class ZigZagIterator3 {
        private Queue<Iterator> iteratorQueue;

        public ZigZagIterator3(List<Integer> v1, List<Integer> v2) {
            //idea #1: store built-in iterators of each given list in Queue and use them!
            iteratorQueue = new LinkedList<>();
            List<List<Integer>> lists = Arrays.asList(v1, v2);
            for (List l : lists) {
                if (!l.isEmpty()) {
                    iteratorQueue.add(l.iterator());
                }
            }
        }

        public boolean hasNext() {
            //idea #2 since we remove iterators of lists that don't have more elements,
            // then iteratorQueue will be empty in the end of iterating
            return !iteratorQueue.isEmpty();
        }

        public int next() {
            //idea #3:
            //1) get and remove the iterator from head of Queue
            //2) get value from it
            //3) if the iterator hastNext element => add it to tail of Queue. otherwise it remains deleted from the queue
            Iterator<Integer> it = iteratorQueue.remove();
            int res = it.next();
            if (it.hasNext()) {
                iteratorQueue.add(it);
            }
            return res;
        }
    }

}
