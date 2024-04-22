https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a38826eed2aca55cd622d9

https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2015%3A%200-1%20Knapsack%20(Dynamic%20Programming).md

Theory:
https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming - todo: read it!
or
https://www.youtube.com/watch?v=xCbYmUPvc2Q
AND (only picture) https://www.youtube.com/watch?v=Q2vDTam9qMQ

NOTE: 0/1 means we cannot split the elements: we include some particular element or NOT

Problem:
there are some items. For each item:
i-th item i has weight (Wi) and value (Vi)
It is necessary to find the combination of the elements such as
1) total weight <= W
2) total value is maximum

Dynamic programming (bottom-up approach):
IF
    i - item, the row we are in
    W - maxWeight, the column we are in
THEN
    V[i,W] - looks like table

How to fill the table:
    1) IF i = 0 OR W = 0 THEN V(i,W)

    2) IF Wi <= W THEN V[i,W] = V[i-1,W]
       ELSE V[i,W] = max(V[i-1][W], V[i-1][W-Wi] + Vi

Dynamic programming (top-down approach):
    see src/solving_techniques/p15_0or1_Knapsack/SubsetSum.java

COMMON ALGORITHM:
1) memo[arr.length + 1][target + 1]
2) recursive helper method:
    base cases:
    target = 0
    target < 0 => Infinity or error or smth like that
    (target > 0) && curIdx = arr.length => Infinity or error or smth like that, since we reached the end of the array, but still did not reach the target

3) if the target is not reached at all, then out helper returns Infinity or Infinity + 1 => we just check it result < 0 => return, for example, -1

Additional info:
IF we use bottom-up approach (iterative) => tabulation - we have to calculate the results for ALL potential sub-problems!
IF we use top-down approach (recursive) => memoization -  we have to calculate the results only for sub-problems that are part of the target solution => it is more efficient!

https://www.geeksforgeeks.org/tabulation-vs-memoization/
    Memoization (recursive) - is used when the subproblems have overlapping subproblems
        we use a dictionary object called cache to store the results of function calls, and we use recursion to compute the results.

        Let?s describe a state for our DP problem to be dp[x] with dp[0] as base state and dp[n] as our destination state. So,  we need to find the value of destination state i.e dp[n].
        If we start our transition from our base state i.e dp[0] and follow our state transition relation to reach our destination state dp[n], we call it the Bottom-Up approach

    Tabulation (iterative) - is when the subproblems DO NOT overlap!
        we use an array called table to store the results of subproblems, and we use iteration to compute the results.

        If we need to find the value for some state say dp[n] and instead of starting from the base state that i.e dp[0]
        we ask our answer from the states that can reach the destination state dp[n] following the state transition relatio

Sequence of problems:
1) 0/1 Knapsack (medium) - done
2) Equal Subset Sum Partition (medium) - done
3) Subset Sum (medium) - done
4) Minimum Subset Sum Difference (hard) - done with TLE or OOM
5) Problem Challenge 1: Count of Subset Sum (hard) - done
6) Problem Challenge 2: Target Sum (hard) - done

7) https://leetcode.com/problems/partition-to-k-equal-sum-subsets/ (medium) - done
8) https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/ (medium) - done
9) https://leetcode.com/problems/find-subarrays-with-equal-sum/ (easy) - done
10) https://leetcode.com/problems/split-with-minimum-sum/ (easy) - done
11) https://leetcode.com/problems/maximum-rows-covered-by-columns/ (medium) - done
12) https://leetcode.com/problems/fair-distribution-of-cookies/ (medium) - done
13) https://leetcode.com/problems/minimum-number-of-coins-to-be-added/ - done
14) https://leetcode.com/problems/length-of-the-longest-subsequence-that-sums-to-target - done
15) https://leetcode.com/problems/ones-and-zeroes (medium) - done
16) https://leetcode.com/problems/last-stone-weight-ii (medium) - done






