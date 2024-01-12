https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f5929a842de869cd616c5

Theory:
info: https://www.youtube.com/watch?v=UZRkpGk943Q
1) i - iterator for string s1, j - iterator for string s2
2) create int[][] dp = new int[s1.len + 1][s2.len + 1] and fill 0-th row and column by 0
3) fill dp table: IF s1.charAt(i-1) == s2.charAt(j-1) THEN dp[i][j] = dp[i-1][j-1] + 1
4) maxLength = max(maxLength, dp[i][j])
NOTE: the space can be optimized since i-th row depends only on i-1-th


Sequence of problems:
1) Longest Common Substring - done
2) Longest Common Subsequence - done
3) Minimum Deletions & Insertions to Transform a String into another - todo
    can't find on leetcode
    only https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/
    and https://leetcode.com/discuss/general-discussion/1274591/minimum-no-of-deletions-insertions-to-transform-1-string-into-another
4) Longest Increasing Subsequence - done
5) Maximum Sum Increasing Subsequence - todo
    can't find on leetcode
    only
6) Shortest Common Super-sequence - todo
7) Minimum Deletions to Make a Sequence Sorted - todo
8) Longest Repeating Subsequence - todo
9) Subsequence Pattern Matching - todo
10) Longest Bitonic Subsequence - todo
11) Longest Alternating Subsequence - todo
12) Edit Distance - todo
13) Strings Interleaving - todo