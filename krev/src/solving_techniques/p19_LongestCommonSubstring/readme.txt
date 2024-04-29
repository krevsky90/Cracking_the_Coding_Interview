https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f5929a842de869cd616c5

Theory:
info: https://www.youtube.com/watch?v=UZRkpGk943Q
1) i - iterator for string s1, j - iterator for string s2
2) create int[][] dp = new int[s1.len + 1][s2.len + 1] and fill 0-th row and column by 0
3) fill dp table: IF s1.charAt(i-1) == s2.charAt(j-1) THEN dp[i][j] = dp[i-1][j-1] + 1
4) maxLength = max(maxLength, dp[i][j])
NOTE: the space can be optimized since i-th row depends only on i-1-th

OR it is better to check the technique here
https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns#DP-on-Strings

Sequence of problems:
1) Longest Common Substring - done
2) Longest Common Subsequence - done
3) Minimum Deletions & Insertions to Transform a String into another - done
    can't find on leetcode
    only https://www.geeksforgeeks.org/minimum-number-deletions-insertions-transform-one-string-another/
    and https://leetcode.com/discuss/general-discussion/1274591/minimum-no-of-deletions-insertions-to-transform-1-string-into-another
4) Longest Increasing Subsequence - done
5) Maximum Sum Increasing Subsequence - done
6) Shortest Common Super-sequence (hard on leetcode) - done
7) Minimum Deletions to Make a Sequence Sorted - done
8) Longest Repeating Subsequence - done
9) Subsequence Pattern Matching - done
10) Longest Bitonic Subsequence - done
11) Longest Alternating Subsequence - done
12) Edit Distance - done
13) Strings Interleaving - done

14) https://leetcode.com/problems/number-of-matching-subsequences (medium) - done
15) https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings (medium) - done
16) https://leetcode.com/problems/maximum-length-of-pair-chain (medium) - done
17) https://leetcode.com/problems/distinct-subsequences (hard) - todo
18) https://leetcode.com/problems/number-of-longest-increasing-subsequence - done
19) https://leetcode.com/problems/longest-increasing-subsequence-ii (hard) - todo
20) https://leetcode.com/problems/longest-continuous-increasing-subsequence (easy) - done
21) https://leetcode.com/problems/longest-substring-of-one-repeating-character (hard) - todo
22) https://leetcode.com/problems/delete-operation-for-two-strings (medium) - done
23) https://leetcode.com/problems/distinct-subsequences (hard) - todo