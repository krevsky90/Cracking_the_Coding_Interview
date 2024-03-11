package solving_techniques.p2_TwoPointers;

/**
 * https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/638fa5205844e928cbf004bf
 * OR similar
 * 1574. Shortest Subarray to be Removed to Make Array Sorted
 * https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted
 */
public class ProblemChallenge3_MinimumWindowSort {
    /**
     * PROBLEM from designgurus
     * todo
     */
    /**
     * PROBLEM from leetcode
     * NOT solved by myself
     * time ~ 90 mins
     * idea: https://leetcode.com/problems/shortest-subarray-to-be-removed-to-make-array-sorted/solutions/936634/java-100-faster-with-explanation/
     */
    //3 cases:
    //subarray in beginning
    //subarray at the end
    //subarray in middle
    public int findLengthOfShortestSubarray(int[] arr) {
        int start = 0;
        int end = arr.length - 1;

        //case 1: subarray at the end
        while (start + 1 < arr.length && arr[start] <= arr[start + 1]) {
            start++;
        }

        //base case: non-decreasing sequence
        if (start + 1 == arr.length) return 0;

        //case 2: subarray in the beginning
        while (end - 1 >= 0 && arr[end - 1] <= arr[end]) {
            end--;
        }

        //calculate min length of subarray to be removed for cases 1 and 2
        //12387
        //start = 3 => minLength = 5 - 3 - 1 = 1 (i.e. "7")
        //end = 4
        int minLength = Math.min(arr.length - start - 1, end);

        //case 3: subarray in the middle
        //todo: draw to understand!
        while (start >= 0) {
            for (int i = end; i < arr.length; i++) {
                if (arr[start] > arr[i]) {
                    continue;
                }

                minLength = Math.min(minLength, i - start - 1);
                break;
            }
            start--;
        }

        return minLength;
    }
}
