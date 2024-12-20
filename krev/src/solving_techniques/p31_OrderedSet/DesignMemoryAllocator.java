package solving_techniques.p31_OrderedSet;

import java.util.*;

/**
 * 2502. Design Memory Allocator (medium)
 * https://leetcode.com/problems/design-memory-allocator
 *
 * #Company: 0 - 3 months Uber 3 Two Sigma 2 0 - 6 months Microsoft 4 Amazon 2 6 months ago Meta 3
 *
 * You are given an integer n representing the size of a 0-indexed memory array. All memory units are initially free.
 *
 * You have a memory allocator with the following functionalities:
 *
 * Allocate a block of size consecutive free memory units and assign it the id mID.
 * Free all memory units with the given id mID.
 * Note that:
 *
 * Multiple blocks can be allocated to the same mID.
 * You should free all the memory units with mID, even if they were allocated in different blocks.
 * Implement the Allocator class:
 *
 * Allocator(int n) Initializes an Allocator object with a memory array of size n.
 * int allocate(int size, int mID) Find the leftmost block of size consecutive free memory units and allocate it with the id mID.
 *      Return the block's first index. If such a block does not exist, return -1.
 * int freeMemory(int mID) Free all memory units with the id mID. Return the number of memory units you have freed.
 *
 * Example 1:
 * Input
 * ["Allocator", "allocate", "allocate", "allocate", "freeMemory", "allocate", "allocate", "allocate", "freeMemory", "allocate", "freeMemory"]
 * [[10], [1, 1], [1, 2], [1, 3], [2], [3, 4], [1, 1], [1, 1], [1], [10, 2], [7]]
 * Output
 * [null, 0, 1, 2, 1, 3, 1, 6, 3, -1, 0]
 */
public class DesignMemoryAllocator {
    /**
     * info: https://leetcode.com/problems/design-memory-allocator/?envType=company&envId=uber&favoriteSlug=uber-three-months
     * idea:
     * 1) use TreeMap: id -> set if starts
     * 2) use TreeMap: start -> end     where end is not included into the interval!
     * 3) hack: set (n,n) as hack to TreeMap from p.2
     * <p>
     * time ~ O(logN)
     * space ~ O(logN)
     *
     * BEATS ~ 71%
     */
    class Allocator2 {
        // map: mId -> set of start indices of the ranges
        private TreeMap<Integer, Set<Integer>> idToStart;   //sorted by id

        private TreeMap<Integer, Integer> startToEnd;       //sorted by start index. NOTE: end position is NOT included into interval!
        private int capacity;

        public Allocator2(int n) {
            idToStart = new TreeMap<>();
            startToEnd = new TreeMap<>();
            startToEnd.put(n, n);   //hack
            this.capacity = n;
        }

        public int allocate(int size, int mID) {
            int idx = 0;
            for (int start : startToEnd.keySet()) {
                int endIdx = idx + size;
                if (endIdx <= start) {
                    //insert
                    idToStart.putIfAbsent(mID, new HashSet<>());
                    idToStart.get(mID).add(idx);
                    startToEnd.put(idx, endIdx);
                    return idx;
                }

                //try to allocate block starting from the end of (start,end) interval
                idx = startToEnd.get(start);
            }

            return -1;
        }

        public int freeMemory(int mID) {
            Set<Integer> starts = idToStart.getOrDefault(mID, new HashSet<>());
            idToStart.remove(mID);

            int counter = 0;
            for (int s : starts) {
                counter += startToEnd.get(s) - s;
                startToEnd.remove(s);
            }

            return counter;
        }
    }

    /**
     * KREVSKY SOLUTION:
     * time to solve ~ 30 mins
     * idea:
     * 1) use Map mId -> set of indices that have mId
     * 2) use filledIndices[]
     * <p>
     * time ~ O(n) for allocation and for free-operation
     * space ~ O(n)
     * <p>
     * 2 attempts:
     * - forgot to change "j <= n" to j < n"
     * <p>
     * BEATS ~ 5%
     */
    class Allocator {
        // map: mId -> set of indices that have mId
        private Map<Integer, Set<Integer>> idToIndices;
        private boolean[] filledIndices;
        private int n;

        public Allocator(int n) {
            idToIndices = new HashMap<>();
            filledIndices = new boolean[n];
            this.n = n;
        }

        public int allocate(int size, int mID) {
            //find block O(n)
            int start = -1;
            for (int i = 0; i < n; i++) {
                if (filledIndices[i]) continue;

                int j = i;
                while (j < n && !filledIndices[j]) {
                    if (j - i + 1 == size) {
                        start = i;
                        break;
                    } else {
                        j++;
                    }
                }

                if (start != -1) break;
            }

            if (start != -1) {
                //allocate
                idToIndices.putIfAbsent(mID, new HashSet<>());
                for (int i = start; i < start + size; i++) {
                    filledIndices[i] = true;
                    idToIndices.get(mID).add(i);
                }
            }

            return start;
        }

        public int freeMemory(int mID) {
            Set<Integer> indices = idToIndices.getOrDefault(mID, new HashSet<>());
            for (int i : indices) {
                filledIndices[i] = false;
            }
            idToIndices.remove(mID);
            return indices.size();
        }
    }
}
