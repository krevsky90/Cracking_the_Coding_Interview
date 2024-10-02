package solving_techniques.p13_TopKElements;

import java.util.*;

/**
 * 658. Find K Closest Elements (medium)
 * https://leetcode.com/problems/find-k-closest-elements
 *
 * #Company: Amazon Apple Bloomberg Meta Google Microsoft Snapchat Uber Yandex
 */
public class FindKClosestNumbers {
    public static void main(String[] args) {
        FindKClosestNumbers obj = new FindKClosestNumbers();
        int[] arr1 = {0, 1, 1, 1, 2, 3, 6, 7, 8, 9};
        int x1 = 4;
        int k1 = 9;
        System.out.println(Arrays.toString(obj.findClosestElements2(arr1, k1, x1).toArray()));
        System.out.println(Arrays.toString(obj.findClosestElements(arr1, k1, x1).toArray()));
    }

    /**
     * KREVSKY SOLUTION #2:
     * idea:
     * 1) find the closest element that is >= than x (using binary search)
     * 2) set initial window (at most 2k) based on x-h position
     * 3) reduce the window until its size becomes k
     * 4) return the elements of this window
     *
     * time to solve ~ 50 mins
     * time ~ O(LogN + k)
     * space ~ O(k) if we consider the size of 'result'
     *
     * BEATS ~ 95%
     *
     */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<>();

        // if (x < arr[0]) {
        //     for (int i = 0; i < k; i++) {
        //         result.add(arr[i]);
        //     }
        //     return result;
        // } else if (x > arr[arr.length - 1]) {
        //     for (int i = arr.length - k; i < arr.length; i++) {
        //         result.add(arr[i]);
        //     }
        //     return result;
        // }

        //find idx using binary search
        int left = 0;
        int right = arr.length - 1;
        int idxX = -1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        idxX = left;
        // x = 6
        // k = 3
        // 1 2 3 4 5   7 8 9
        //idx = 6 (element = 7)
        //


        int start = Math.max(0, idxX - k);
        int end = Math.min(arr.length - 1, idxX + (k - 1));
        while (end - start + 1 > k) {
            if (Math.abs(x - arr[start]) <= Math.abs(x - arr[end])) {
                end--;
            } else {
                start++;
            }
        }

        for (int i = start; i <= end; i++) {
            result.add(arr[i]);
        }

        return result;
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
     * KREVSKY SOLUTION very inefficient!:
     * NOTE! to solve this problem via Priority Queue is VERY INCONVENIENT!
     * idea:
     * 1) store array of Pairs (call it 'data'): idx of the element from arr -> distance between arr[idx] and x
     * NOTE: since we traverse though sorted 'arr' => when we will traverse through the 'data' and the fetch the values by idx => we will get sorted array of these values
     * 2) use PriorityQueue with custom comparator to store the pairs with right priority:
     * NOTE: Pair does not contain the value from arr, but it can be easily fetched by idx!
     * 3) if we throw smth from the Queue, we also mark this index (idx) as removed.
     * 4) traverse through data of Pairs (see p.1 - the values are already sorted by default) and put them to 'result',
     * if i-th index is not marked as 'removed'
     * <p>
     * time to solve ~ 55 mins
     * <p>
     * a lot of attempts
     *
     * BEATS ~ 25%
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
