https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/636b1d083b22faa3e89b243c

Sliding Window Technique - Algorithmic Mental Models
https://www.youtube.com/watch?v=MK-NZ4hN7rs

1) Fixed sliding window
2) Dynamically resizable window - growing (move end point) and shrink (move start point)
    Brute force - iterating with each width of window => duplicate work => O(N*k), where k - width of window

CONDITIONS:
    Sliding window ONLY works if the range of nums is > 0. i.e. POSITIVE values!

TIPS:
    a) things we iterate over sequentially:
        - CONTIGUOUS sequence of elements
        - strings, arrays, linked lists
    b) min, max, longest, shortest, contained
        - maybe we need to calculate smth like running average
================
Question variants:
    Fixed length:
        - max sum subarray of size K - see solving_techniques/sliding_window/fixedWindowLength/MaxSumSubarray.java

    Dynamic length:
        - the smallest sum >= some value S - see solving_techniques/sliding_window/SmallestSubarrayWithGivenSum.java
                                           - see solving_techniques/sliding_window/SlidingWindowWithFlippingOrSwapping.java
        - with auxiliary data structure (HashMap, HashSet, additional array..)
            example #1: find the longest substring with no more than K distinct characters - see solving_techniques/sliding_window/LongestSubstringLengthWithKDistinctChars.java
                                                                                           - see solving_techniques/sliding_window/ShortestSubstringWithAllDesiredChars.java
                (distinct => hashMap/Set to track the existence of characters)
            example #2: string permutation: does string#2 exist as a permutation of string#1?

================
Algorithm:

int start = 0;
for (int end = 0; end < nums.length; end++) {
    //do iterative action
    p *= nums[end];

    //check whether we need to cut the beginning to the window in 'while'-loop
    //if yes - revert the changes that was brought by 'start' element and increment start
    while (start < end && p >= k) {
        p /= nums[start];
        start++;
    }

    //(re-)calculate the result basing on "end - start + 1"
    if (p < k) {
        result += (end - start) + 1;
    }
}


Commonalities (similarities):
1) everything grouped sequentially
2) longest/smallest/contains/max/min/
3) extra criteria (> K and so on)

=============================

Sequence of problems:
1) Maximum Sum Subarray of Size K (easy) = Cracking_the_Coding_Interview\krev\src\solving_techniques\sliding_window\fixedWindowLength\MaxSumSubarray.java
2) SmallestSubarrayWithGreaterSum
3) LongestSubstringLengthWithKDistinctChars
4) FruitIntoBaskets
5) Longest Substring with Same Letters after Replacement (hard) - todo
6) Longest Subarray with Ones after Replacement (hard) - todo
7) Problem Challenge 1: Permutation in a String (hard) - done
8) Problem Challenge 2: String Anagrams (hard) - done
9) Problem Challenge 3: Smallest Window containing Substring (hard) - todo
10) Problem Challenge 4: Words Concatenation (hard) - todo
11) https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero - done
12) https://leetcode.com/problems/minimum-window-substring (hard) - done
13) https://leetcode.com/problems/take-k-of-each-character-from-left-and-right - done
14) https://leetcode.com/problems/longest-repeating-character-replacement (medium) - done
15) https://leetcode.com/problems/longest-subarray-of-1s-after-deleting-one-element (medium) - done