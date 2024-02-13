package solving_techniques.p13_TopKElements;

import java.util.*;

public class FindKClosestNumbers {
    public static void main(String[] args) {
        FindKClosestNumbers obj = new FindKClosestNumbers();
        int[] arr1 = {0,1,1,1,2,3,6,7,8,9};
        int x1 = 4;
        int k1 = 9;
        List<Integer> result1 = obj.findClosestElements(arr1, k1, x1);
        System.out.println(Arrays.toString(result1.toArray()));
    }

    /**
     * SOLUTION using Two pointers!
     * https://leetcode.com/problems/find-k-closest-elements/solutions/2638714/two-pointers-o-n-java-solution/
     */

    /**
     * Similar (to my) solution, but sort the whole array of pairs. And Pair is <value, distance>
     *     https://leetcode.com/problems/find-k-closest-elements/solutions/4496154/simple-and-easy-to-understand-java-solution-intuitive-approach/
     *
     */

    /**
     * KREVSKY SOLUTION:
     * NOTE! to solve this problem via Priority Queue is VERY INCONVENIENT!
     * idea:
     * 1) store array of Pairs (call it 'data'): idx of the element from arr -> distance between arr[idx] and x
     *      NOTE: since we traverse though sorted 'arr' => when we will traverse through the 'data' and the fetch the values by idx => we will get sorted array of these values
     * 2) use PriorityQueue with custom comparator to store the pairs with right priority:
     *      NOTE: Pair does not contain the value from arr, but it can be easily fetched by idx!
     * 3) if we throw smth from the Queue, we also mark this index (idx) as removed.
     * 4) traverse through data of Pairs (see p.1 - the values are already sorted by default) and put them to 'result',
     *      if i-th index is not marked as 'removed'
     *
     * time to solve ~ 55 mins
     *
     * a lot of attempts
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        //to store: idx of the element from arr -> distance (arr[idx] - x)
        Pair[] data = new Pair[arr.length];
        for (int idx = 0; idx < arr.length; idx++) {
            data[idx] = new Pair(idx, Math.abs(arr[idx] - x));
        }

        //max heap
        Queue<Pair> pq = new PriorityQueue<>((a, b) ->
                (b.getSecond() > a.getSecond() || b.getSecond() == a.getSecond() && arr[b.getFirst()] > arr[a.getFirst()]) ? 1 : -1
        );

        boolean[] removedKeys = new boolean[arr.length];
        for (int i = 0; i < data.length; i++) {
            pq.add(data[i]);
            if (pq.size() > k) {
                Pair entityToRemove = pq.poll();
                //mark as removed
                removedKeys[entityToRemove.getFirst()] = true;
            }
        }

        List<Integer> result = new ArrayList<>(arr.length);
        for (Pair pair : data) {
            if (removedKeys[pair.getFirst()] == false) {
                result.add(arr[pair.getFirst()]);
            }
        }

        return result;
    }

    class Pair {
        private Integer first = 0;
        private Integer second = 0;

        public Pair(Integer first, Integer second) {
            this.first = first;
            this.second = second;
        }

        public Integer getFirst() {
            return first;
        }

        public Integer getSecond() {
            return second;
        }
    }
}
