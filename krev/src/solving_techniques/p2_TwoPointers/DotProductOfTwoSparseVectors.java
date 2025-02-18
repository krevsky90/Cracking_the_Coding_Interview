package solving_techniques.p2_TwoPointers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 1570 - Dot Product of Two Sparse Vectors (medium) (locked)
 * https://leetcode.com/problems/dot-product-of-two-sparse-vectors
 * OR
 * https://leetcode.ca/2020-03-18-1570-Dot-Product-of-Two-Sparse-Vectors/
 * <p>
 * #Company (18.02.2025: 0 - 3 months Meta 55 Bloomberg 2 Nvidia 2 0 - 6 months Amazon 2 6 months ago Apple 3 Google 2 Microsoft 2 TikTok 2
 * <p>
 * Given two sparse vectors, compute their dot product.
 * <p>
 * Implement class SparseVector:
 * <p>
 * SparseVector(nums) Initializes the object with the vector nums
 * dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
 * A sparse vector is a vector that has mostly zero values,
 * you should store the sparse vector efficiently and compute the dot product between two SparseVector.
 * <p>
 * Follow up: What if only one of the vectors is sparse?
 * <p>
 * Example 1:
 * Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
 * Output: 8
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
 * <p>
 * Example 2:
 * Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
 * Output: 0
 * Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
 * v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
 * <p>
 * Example 3:
 * Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
 * Output: 6
 * <p>
 * Constraints:
 * n == nums1.length == nums2.length
 * 1 <= n <= 10^5
 * 0 <= nums1[i], nums2[i] <= 100
 */
public class DotProductOfTwoSparseVectors {
    public static void main(String[] args) {
        DotProductOfTwoSparseVectors obj = new DotProductOfTwoSparseVectors();
        obj.test();
    }

    public void test() {
        int[] nums1 = {1, 0, 0, 2, 3};
        int[] nums2 = {0, 3, 0, 4, 0};
        SparseVector2followUp s21 = new SparseVector2followUp(nums1);
        SparseVector2followUp s22 = new SparseVector2followUp(nums2);
        System.out.println(s21.dotProduct(s22));    //expected 8
    }

    /**
     * SOLUTION #2:
     * ATTENTION! this solution is accepted in Meta! whereas solution based on HashMap is NOT accepted!
     * WHAT if hash-function causes collision???
     * <p>
     * info:
     * https://www.youtube.com/watch?v=sQNN4xKC1mA&list=PLUPSMCjQ-7od5IVz8ug6D-apxFLkDTsoy&index=36
     * idea #3: use array of Pair<index, value> instead of using HashMap
     * idea #4: use two-pointers to calculate 'dotProduct' in time ~ O(n), where n = max length of lists from SparseVector2's
     * <p>
     * time ~ O(n1 + n2), when n1 - length of nums1, n2 - .. nums2
     * space ~ O(n1 + n2) - when vector is not sparse vector
     * worst case - when both vectors are no sparse
     */
    class SparseVector2 {
        public List<int[]> list;

        SparseVector2(int[] nums) {
            list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    list.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector2 vec) {
            int product = 0;
            int i = 0;
            int j = 0;
            while (i < list.size() && j < vec.list.size()) {
                if (list.get(i)[0] == vec.list.get(j)[0]) {
                    product += list.get(i)[1] * vec.list.get(j)[1];
                    i++;
                    j++;
                } else if (list.get(i)[0] > vec.list.get(j)[0]) {
                    j++;
                } else {
                    i++;
                }
            }

            return product;
        }
    }

    /**
     * NOTE: reg follow-up question:
     * if some vector is not sparse => we will get long list from it
     * so we will have short list of pairs <index -> value> and long list
     * then we will traverse through short list and for each index (obtained from pair) we can find corresponding index in long list using BINARY SEARCH!
     * time O(n * logm), where n - length of short list, m - ... long list
     *
     * BEATS ~ 79%
     */
    class SparseVector2followUp {
        public List<int[]> list;

        SparseVector2followUp(int[] nums) {
            list = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    list.add(new int[]{i, nums[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector2followUp vec) {
            int product = 0;
            List<int[]> longList = list;
            List<int[]> shortList = vec.list;
            if (longList.size() < shortList.size()) {
                List temp = longList;
                longList = shortList;
                shortList = temp;
            }

            //ONLY if shortLst.size() << longList.size()
            for (int i = 0; i < shortList.size(); i++) {
                int idx = binarySearch(shortList.get(i)[0], longList);
                if (idx != -1) {
                    product += shortList.get(i)[1] * longList.get(idx)[1];
                }
            }

            return product;
        }

        private int binarySearch(int i, List<int[]> longList) {
            int low = 0;
            int high = longList.size() - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (longList.get(mid)[0] == i) {
                    return mid;
                } else if (longList.get(mid)[0] > i) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            return -1;
        }
    }

    /**
     * SOLUTION #1: = KREVSKY SOLUTION
     * info:
     * https://leetcode.ca/2020-03-18-1570-Dot-Product-of-Two-Sparse-Vectors/
     * idea #1: store nums into map: index -> value
     * idea #2: need to traverse through keySet() of the Map which has minimum length
     * <p>
     * time ~ O(n1 + n2), when n1 - length of nums1, n2 - .. nums2
     * space ~ O(n1 + n2) - when vector is not sparse vector
     * worst case - when both vectors are no sparse
     */
    class SparseVector1 {
        public Map<Integer, Integer> d = new HashMap<>();

        public SparseVector1(int[] nums) {
            // idea #1:
            for (int i = 0; i < nums.length; ++i) {
                if (nums[i] != 0) {
                    d.put(i, nums[i]);
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector1 vec) {
            Map<Integer, Integer> a = d;
            Map<Integer, Integer> b = vec.d;

            // idea #2
            if (b.size() < a.size()) {
                Map t = a;
                a = b;
                b = t;
            }

            int ans = 0;
            for (Map.Entry<Integer, Integer> e : a.entrySet()) {
                int i = e.getKey(), v = e.getValue();
                ans += v * b.getOrDefault(i, 0);
            }

            return ans;
        }
    }
}