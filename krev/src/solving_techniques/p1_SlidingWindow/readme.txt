Sliding Window Technique - Algorithmic Mental Models
https://www.youtube.com/watch?v=MK-NZ4hN7rs

1) Fixed sliding window
2) Dynamically resizable window - growing (move end point) and shrink (move start point)
    Brute force - iterating with each width of window => duplicate work => O(N*k), where k - width of window

TIPS:
    a) things we iterate over sequentially:
        - CONTIGUOUS sequence of elements
        - strings, arrays, linked lists
    b) min, max, longest, shortest, contained
        - may be we need to calculate smth like running average

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

Commonalities (similarities):
1) everything grouped sequentially
2) longest/smallest/contains/max/min/
3) extra criteria (> K and so on)

=============================
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/636b1d083b22faa3e89b243c


Sequence of problems (from https://www.designgurus.io/course-play/grokking-the-coding-interview)
1) Maximum Sum Subarray of Size K (easy) = Cracking_the_Coding_Interview\krev\src\solving_techniques\sliding_window\fixedWindowLength\MaxSumSubarray.java
2) SmallestSubarrayWithGreaterSum
3) LongestSubstringLengthWithKDistinctChars
4) FruitIntoBaskets
5) Longest Substring with Same Letters after Replacement (hard) - todo
6) Longest Subarray with Ones after Replacement (hard) - todo
7) Problem Challenge 1: Permutation in a String (hard) = PermutationInString
8) Problem Challenge 2: String Anagrams (hard) - todo
9) Problem Challenge 3: Smallest Window containing Substring (hard) - todo
10) Problem Challenge 4: Words Concatenation (hard) - todo