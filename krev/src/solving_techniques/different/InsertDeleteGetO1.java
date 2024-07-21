package solving_techniques.different;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)
 * https://leetcode.com/problems/insert-delete-getrandom-o1 (medium)
 * <p>
 * #Company: Yandex
 * <p>
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet()
 * Initializes the RandomizedSet object.
 * bool insert(int val)
 * Inserts an item val into the set if not present.
 * Returns true if the item was not present, false otherwise.
 * bool remove(int val)
 * Removes an item val from the set if present.
 * Returns true if the item was present, false otherwise.
 * int getRandom()
 * Returns a random element from the current set of elements
 * (it's guaranteed that at least one element exists when this method is called).
 * Each element must have the same probability of being returned.
 * <p>
 * You must implement the functions of the class such that each function works in average O(1) time complexity.
 * <p>
 * Example 1:
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * RandomizedSet randomizedSet = new RandomizedSet();
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2 randomly.
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now contains [2].
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * randomizedSet.getRandom(); // Since 2 is the only number in the set, getRandom() will always return 2.
 * <p>
 * Constraints:
 * <p>
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 10^5 calls will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 */
public class InsertDeleteGetO1 {
    public static void main(String[] args) {
        RandomizedSet rs = new RandomizedSet();
        System.out.println(rs.insert(1));   //expected true
        System.out.println(rs.remove(2));   //expected false
        System.out.println(rs.insert(2));   //expected true
        System.out.println("random = " + rs.getRandom());
        System.out.println(rs.remove(1));   //expected true
        System.out.println(rs.insert(2));   //expected false
        System.out.println("random = " + rs.getRandom());   //expected 2
    }

    /**
     * NOT SOLVED by myself - did not come to 2b
     * <p>
     * info: https://leetcode.com/problems/insert-delete-getrandom-o1/solutions/2860059/python3-easy-idea-dictionary-list-remember-to-delete-in-both-in-remove/
     * ideas:
     * 1. Use HashMap to quickly add and remove
     * 2. Use list/stack to help maintain the index for the "random" operation
     * 2b. Quick remove in list is achieved by swapping the elem's idx with the last one, and do the pop (stack's operation)
     * 3. CAUTIOUS: when doing the remove, remember to NOT ONLY remove the elem in list BUT also in dictionary!
     * 4. (KREVSKY addition) DO NOT forger to update Map for the value that was moved from the end of list to idx-th position!
     * time to solve ~ 40 mins
     */
    static class RandomizedSet {
        //idea #1 and #2
        private Map<Integer, Integer> valueToIndexMap;
        private List<Integer> list;

        public RandomizedSet() {
            valueToIndexMap = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            if (valueToIndexMap.containsKey(val)) return false;

            int pos = valueToIndexMap.size();
            valueToIndexMap.put(val, pos);
            list.add(val);
            return true;
        }

        public boolean remove(int val) {
            if (!valueToIndexMap.containsKey(val)) return false;

            int idx = valueToIndexMap.get(val);
            //idea #2b:
            // swap idx-th element and the last value in the list
            // and remove the last value (i.e. that cam from idx position) from the list - it costs O(1)
            int lastEl = list.get(list.size() - 1);
            list.set(idx, lastEl);
            list.remove(list.size() - 1);

            //idea #4: update index for moved (the last) element of the list
            valueToIndexMap.put(lastEl, idx);

            //idea #3:
            valueToIndexMap.remove(val);

            return true;
        }

        public int getRandom() {
            int r = new Random().nextInt(valueToIndexMap.size());   //or list.size(). does not matter
            return list.get(r);
        }
    }
}
