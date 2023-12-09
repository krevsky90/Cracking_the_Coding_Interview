https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a38826eed2aca55cd622d9

Theory:
https://leetcode.com/discuss/study-guide/1152328/01-Knapsack-Problem-and-Dynamic-Programming - todo: read it!
or
https://www.youtube.com/watch?v=xCbYmUPvc2Q

NOTE: 0/1 means we cannot split the elements: we include some particular element or NOT

Problem:
there are some items. For each item:
i-th item i has weight (Wi) and value (Vi)
It is necessary to find the combination of the elements such as
1) total weight <= W
2) total value is maximum

Dynamic programming:
IF
    i - item, the row we are in
    W - maxWeight, the column we are in
THEN
    V[i,W] - looks like table

How to fill the table:
    1) IF i = 0 OR W = 0 THEN V(i,W)

    2) IF Wi <= W THEN V[i,W] = V[i-1,W]
       ELSE V[i,W] = max(V[i-1][W], V[i-1][W-Wi] + Vi

Sequence of problems:
1) 0/1 Knapsack (medium) - done
2) Equal Subset Sum Partition (medium) - done
3) Subset Sum (medium) - todo
4) Minimum Subset Sum Difference (hard) - todo
5) Problem Challenge 1: Count of Subset Sum (hard) - todo
6) Problem Challenge 2: Target Sum (hard) - todo





