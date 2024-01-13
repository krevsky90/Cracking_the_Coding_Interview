https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-dynamic-programming/doc/637f3802aad31bf9612b478a

Usage: Use this technique to select elements that give maximum profit from a given set with a limitation on capacity and that each element can be picked multiple times.

Theory:
the best!!! https://www.youtube.com/watch?v=jlCJqgSgXI4
and https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/

Given a knapsack weight W and a set of n items with certain value Vi and weight Wi,
We need to calculate the maximum amount that could make up this quantity exactly.
This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.

Note: N is always positive i.e greater than zero
===============================================================================
i - actual item
k - remaining capacity
N - amount of elements

Example:
values: [20,30,15,25,10]
weights: [6,13,5,10,3]
k = 20 - capacity
--------------------------------- APPROACH #1 ---------------------------------
time complexity ~ O(2^N)
space complexity ~ O(N)
                               knapsack(i, k) =
                         /-- maximum of two ways ---\
                        /                            \
                       / (we take i-th item)          \ (we do NOT take i-th item)
                      /                                \
    knapsack(i, k - Wi)                                knapsack(i + 1, k)

NOTE: when we take i-th item, we DO NOT increment i, since we can i-th element AGAIN!


                              knap(0, 20)
                             /           \
                   we take item0       we move to item1
                         /                \
                 knap(0, 14)              knap(1, 20)
                 /         \                  ..
      we take item0        we move to item1
               /              \
       knap(0, 8)            knap(1, 14)
        ..                       ..

--------------------------------- APPROACH #2 ---------------------------------
IF we have N items, then we can draw tree, where each node has N children
Let's draw horizontal tree, it is more convenient
time complexity ~ O(N^C)
space complexity ~ O(N)


                                      /--we take item0 --- knap(8)
                                     /---we take item1 --- knap(-6) => exclude this branch!
        /-- we take item0 --- knap(14) ...
       /--- we take item1 --- knap(7) ...
knap(20)--- we take item2 --- knap(15) ...
       \--- we take item3 --- knap(10) ...
        \-- we take item4 --- knap(17) ...

--------------------------------- APPROACH #2 Optimization ---------------------------------
Since we will have repeating elements, we can store knap(x) result where 0 < x <= capacity

===============================================================================





Example #1:
Input : W = 100
       values[]  = {1, 30}
       weights[] = {1, 50}
Output : 100
There are many ways to fill knapsack.
1) 2 instances of 50 unit weight item.
2) 100 instances of 1 unit weight item.
3) 1 instance of 50 unit weight item and 50 instances of 1 unit weight items.
We get maximum value with option 2.

Example #2:
Input : W = 8
       values[] = {10, 40, 50, 70}
       weights[]  = {1, 3, 4, 5}
Output : 110
We get maximum value with one unit of weight 5 and one unit of weight 3


Sequence of problems:
1) Unbounded Knapsack - done
2) Rod Cutting - done
3) Coin Change - done
4) Minimum Coin Change - todo
5) Maximum Ribbon Cut - todo