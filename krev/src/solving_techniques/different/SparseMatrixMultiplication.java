package solving_techniques.different;

import java.util.HashMap;
import java.util.Map;

/**
 * 311. Sparse Matrix Multiplication (medium) (locked)
 * https://leetcode.com/problems/sparse-matrix-multiplication
 * <p>
 * #Company (7.03.2025): 0 - 3 months Meta 5 Google 2 6 months ago Pinterest 7 Bloomberg 5 LinkedIn 3
 * <p>
 * Given two sparse matrices mat1 of size m x k and mat2 of size k x n, return the result of mat1 x mat2. You may assume that multiplication is always possible.
 * <p>
 * Example 1:
 * Input: mat1 = [[1,0,0],[-1,0,3]], mat2 = [[7,0,0],[0,0,0],[0,0,1]]
 * Output: [[7,0,0],[-7,0,3]]
 * <p>
 * Example 2:
 * Input: mat1 = [[0]], mat2 = [[0]]
 * Output: [[0]]
 * <p>
 * Constraints:
 * m == mat1.length
 * k == mat1[i].length == mat2.length
 * n == mat2[i].length
 * 1 <= m, n, k <= 100
 * -100 <= mat1[i][j], mat2[i][j] <= 100
 */
public class SparseMatrixMultiplication {
    /**
     * KREVSKY SOLUTION:
     * idea:
     * keep 1st sparse matrix as Map<rowIdx, Map<colIdx,value>> for non-zero values
     * keep 2nd sparse matrix as Map<colIdx, Map<rowIdx,value>> for non-zero values
     * for each i,j from (m,n) multiply map's values of corresponding index pairs
     * <p>
     * it will optimize the initial naive solution (which has time ~ O(m*n*k) to O(m*n*maxlenOfInternalMap)
     * <p>
     * time to solve ~ 24 mins (10 ot think)
     * <p>
     * 1 attempt
     * <p>
     * BEATS ~ 40%
     */
    public int[][] multiply(int[][] mat1, int[][] mat2) {
        //naive time ~ O(n*m*k)
        int m = mat1.length;
        int k = mat1[0].length;
        int n = mat2[0].length;

        Map<Integer, Map<Integer, Integer>> map1 = new HashMap<>();
        for (int i = 0; i < m; i++) {
            map1.putIfAbsent(i, new HashMap<>());
            for (int j = 0; j < k; j++) {
                if (mat1[i][j] != 0) {
                    map1.get(i).put(j, mat1[i][j]);
                }
            }
        }

        Map<Integer, Map<Integer, Integer>> map2 = new HashMap<>();
        for (int j = 0; j < n; j++) {
            map2.putIfAbsent(j, new HashMap<>());
            for (int i = 0; i < k; i++) {
                if (mat2[i][j] != 0) {
                    map2.get(j).put(i, mat2[i][j]);
                }
            }
        }

        int[][] result = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int res = 0;
                for (int idx : map1.get(i).keySet()) {
                    if (map2.get(j).containsKey(idx)) {
                        res += map1.get(i).get(idx) * map2.get(j).get(idx);
                    }
                }
                result[i][j] = res;
            }
        }

        return result;
    }

    // map1
    // each entry - row
    // 0 -> (0,1)
    // 1 -> (0,-1), (2,3)

    // map2:
    // each entry - column
    // 0 -> (0,7)
    // 1 -> empty
    // 2 -> (2,1)
}
