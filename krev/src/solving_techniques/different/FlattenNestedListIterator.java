package solving_techniques.different;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 341. Flatten Nested List Iterator (medium)
 * https://leetcode.com/problems/flatten-nested-list-iterator
 * <p>
 * #Company: Yandex
 * <p>
 * You are given a nested list of integers nestedList.
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 * Implement an iterator to flatten it.
 * <p>
 * Implement the NestedIterator class:
 * NestedIterator(List<NestedInteger> nestedList)
 * Initializes the iterator with the nested list nestedList.
 * int next()
 * Returns the next integer in the nested list.
 * boolean hasNext()
 * Returns true if there are still some integers in the nested list and false otherwise.
 * <p>
 * Your code will be tested with the following pseudocode:
 * initialize iterator with nestedList
 * res = []
 * while iterator.hasNext()
 * append iterator.next() to the end of res
 * return res
 * If res matches the expected flattened list, then your code will be judged as correct.
 * <p>
 * Example 1:
 * Input: nestedList = [[1,1],2,[1,1]]
 * Output: [1,1,2,1,1]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * <p>
 * Example 2:
 * Input: nestedList = [1,[4,[6]]]
 * Output: [1,4,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * <p>
 * Constraints:
 * 1 <= nestedList.length <= 500
 * The values of the integers in the nested list is in the range [-10^6, 10^6].
 */
public class FlattenNestedListIterator {
    /**
     * NOT SOLVED by myself - frightened iterators
     * info: https://leetcode.com/problems/flatten-nested-list-iterator/solutions/4189535/best-java-solution-beats-100/
     * idea:
     * convert given List<NestedInteger> nestedList to flatten structure (ArrayList) that simply stores Integers
     * <p>
     * time to implement ~ 10 mins
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 100%
     */
    public interface NestedInteger {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    class NestedIterator implements Iterator<Integer> {
        private List<Integer> nums;
        private int idx;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nums = new ArrayList<>();
            this.idx = 0;
            flatten(nestedList);
        }

        private void flatten(List<NestedInteger> nestedList) {
            for (NestedInteger item : nestedList) {
                if (item.isInteger()) {
                    nums.add(item.getInteger());
                } else {
                    flatten(item.getList());
                }
            }
        }

        @Override
        public Integer next() {
            return nums.get(idx++);
        }

        @Override
        public boolean hasNext() {
            return idx < nums.size();
        }
    }
}
