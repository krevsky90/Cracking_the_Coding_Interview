https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1eda0e79544420e750f36

This pattern helps us solve problems that involve a list of sorted arrays.

Whenever we are given ?K? sorted arrays,
we can use a Heap to efficiently perform a sorted traversal of all the elements of all arrays.
We can push the smallest (first) element of each sorted array in a Min Heap to get the overall minimum.
While inserting elements to the Min Heap we keep track of which array the element came from.
We can, then, remove the top element from the heap to get the smallest element and push the next element from the same array,
to which this smallest element belonged, to the heap ...

Sequence of problems:
1) Merge K Sorted Lists (medium) - done
2) Kth Smallest Number in M Sorted Lists (medium) - todo
3) Kth Smallest Number in a Sorted Matrix (hard) - todo
4) Smallest Number Range (hard) - todo
5) Problem Challenge 1: K Pairs with Largest Sums (hard) - todo
