package data_structures.heap;

import java.util.*;

/**
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * https://www.youtube.com/watch?v=6iLb_Z-osmQ
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        //test comparator:
        //to sort from the lowest to the largest: (o1, o2) -> o1 - o2 => may be useful for min Heap
        //to sort from the largest to the lowest: (o1, o2) -> o2 - o1 => may be useful for max Heap
        List<Integer> list = Arrays.asList(1,5,3,8,9,8,4);
        sortArr(list, (o1, o2) -> (Integer) o2 - (Integer) o1);
        System.out.println();

        int[] arr = new int[]{1,1,1,2,2,3};
        int k = 2;
        int[] result = topKFrequent(arr, k);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            int value = map.getOrDefault(n, 0);
            map.put(n, value + 1);
        }

        //find top K keys of the map using heap
        //top = max => should use min Heap => PriorityQueue
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        //gather keys of the elements that are in queue
        int[] result = new int[k];
        int i = 0;
        while (!pq.isEmpty()) {
            result[i] = pq.poll().getKey();
            i++;
        }

        return result;
    }

    private static List<Integer> sortArr(List<Integer> list, Comparator c) {
        Collections.sort(list, c);
        return list;
    }

}
