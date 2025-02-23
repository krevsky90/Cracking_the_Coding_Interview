package solving_techniques.p2_TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1868 - Product of Two Run-Length Encoded Arrays (medium) (locked)
 * https://leetcode.com/problems/product-of-two-run-length-encoded-arrays
 * #Company (23.02.2025): 0 - 3 months Meta 2 6 months ago Amazon 2 Yandex 2
 * OR
 * https://leetcode.ca/2021-07-17-1868-Product-of-Two-Run-Length-Encoded-Arrays/
 * <p>
 * Run-length encoding is a  compression algorithm that allows for an integer array nums
 * with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded.
 * Each encoded[i] = [val_i, freq_i] describes the i-th segment of repeated numbers in nums where val_i is the value that is repeated freq_i times.
 * <p>
 * For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]].
 * Another way to read this is “three 1s followed by five 2s”.
 * The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:
 * <p>
 * Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
 * Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
 * Compress prodNums into a run-length encoded array and return it.
 * <p>
 * You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively.
 * Both nums1 and nums2 have the same length.
 * Each encoded1[i] = [val_i, freq_i] describes the i-th segment of nums1,
 * and each encoded2[j] = [val_j, freq_j] describes the j-th segment of nums2.
 * <p>
 * Return the product of encoded1 and encoded2.
 * <p>
 * Note:  Compression should be done such that the run-length encoded array has the minimum possible length.
 * <p>
 * Example 1:
 * Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
 * Output: [[6,6]]
 * <p>
 * Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
 * prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].
 * <p>
 * Example 2:
 * Input: encoded1 = [[1,3],[2,1],[3,2]], encoded2 = [[2,3],[3,3]]
 * Output: [[2,3],[6,1],[9,2]]
 * <p>
 * Explanation: encoded1 expands to [1,1,1,2,3,3] and encoded2 expands to [2,2,2,3,3,3].
 * prodNums = [2,2,2,6,9,9], which is compressed into the run-length encoded array [[2,3],[6,1],[9,2]].
 * <p>
 * Constraints:
 * 1 <= encoded1.length, encoded2.length <= 10^5
 * encoded1[i].length == 2
 * encoded2[j].length == 2
 * 1 <= val_i, freq_i <= 10^4 for each encoded1[i].
 * 1 <= val_j, freq_j <= 10^4 for each encoded2[j].
 * The full arrays that encoded1 and encoded2 represent are the same length.
 */
public class ProductOfTwoRunLengthEncodedArrays {
    public static void main(String[] args) {
        ProductOfTwoRunLengthEncodedArrays obj = new ProductOfTwoRunLengthEncodedArrays();
        int[][] encoded11 = {{1, 3}, {2, 3}};
        int[][] encoded12 = {{6, 3}, {3, 3}};
        List<List<Integer>> res = obj.findRLEArray(encoded11, encoded12);
        for (List<Integer> pair : res) {
            System.out.println(pair.get(0) + ", " + pair.get(1));
        }
        System.out.println("--------");
        int[][] encoded21 = {{1, 3}, {2, 1}, {3, 2}};
        int[][] encoded22 = {{2, 3}, {3, 3}};
        res = obj.findRLEArray(encoded21, encoded22);
        for (List<Integer> pair : res) {
            System.out.println(pair.get(0) + ", " + pair.get(1));
        }
    }

    /**
     * KREVSKY SOLUTION:
     * idea:
     * <p>
     * time to solve ~ 30 mins + 10 mins to debug
     * <p>
     * time ~ O(n), where n = length of decoded array
     * space ~ O(n)
     * <p>
     * 1 attempt
     */
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> result = new ArrayList<>();

        int i1 = 0;
        int i2 = 0;
        int j1 = 1;
        int j2 = 1;
        int k = 1;
        int tempProd = encoded1[i1][0] * encoded2[i2][0];
        int prevProd = tempProd;

        while (i1 < encoded1.length && i2 < encoded2.length) {
            boolean over1 = j1 >= encoded1[i1][1];
            if (over1) {
                i1++;
                if (i1 == encoded1.length) {
                    break;
                }
                j1 = 0;
            }

            boolean over2 = j2 >= encoded2[i2][1];
            if (over2) {
                i2++;
                if (i2 == encoded2.length) {
                    break;
                }
                j2 = 0;
            }

            if (!over1 && !over2) {
                k++;
            } else {
                tempProd = encoded1[i1][0] * encoded2[i2][0];
                if (prevProd == tempProd) {
                    k++;
                } else {
                    result.add(Arrays.asList(prevProd, k));

                    k = 1;
                    prevProd = tempProd;
                }
            }

            j1++;
            j2++;
        }

        result.add(Arrays.asList(prevProd, k));
        return result;
    }

    /**
     * info:
     * https://leetcode.ca/2021-07-17-1868-Product-of-Two-Run-Length-Encoded-Arrays/
     * idea is the same more simple and beauty
     * 1.1) calculate freq = min(j1, j2)
     * 1.2) calculate product of encoded1[i1][0] and encoded2[i2][0]
     * etc
     */
    public List<List<Integer>> findRLEArray2(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> products = new ArrayList<>();
        int product = 0;
        int count = 0;
        int i1 = 0;
        int i2 = 0;
        while (i1 < encoded1.length && i2 < encoded2.length) {
            int val1 = encoded1[i1][0];
            int val2 = encoded2[i2][0];
            int freq = Math.min(encoded1[i1][1], encoded2[i2][1]);
            encoded1[i1][1] -= freq;
            encoded2[i2][1] -= freq;
            int curProduct = val1 * val2;
            if (curProduct == product)
                count += freq;
            else {
                if (count > 0) {
                    products.add(Arrays.asList(product, count));
                }
                product = curProduct;
                count = freq;
            }
            if (encoded1[i1][1] == 0)
                i1++;
            if (encoded2[i2][1] == 0)
                i2++;
        }
        products.add(Arrays.asList(product, count));
        return products;
    }
}