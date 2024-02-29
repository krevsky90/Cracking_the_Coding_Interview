https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1c95119e6a3ce13ceda10

Any problem that asks us to find the top/smallest/frequent ?K? elements among a given set falls under this pattern.
The best data structure that comes to mind to keep track of ?K? elements is Heap.
This pattern will make use of the Heap to solve multiple problems dealing with ?K? elements at a time from a set of given elements.

Explanation how does the heap works:
https://www.youtube.com/watch?v=g9YK6sftDi0&

Short summary:

min heap - min element on the top
max heap - max element on the top

K-th smallest element => use max heap. Add (offer) element + remove (poll) top of heap. Finally return top of heap
K-th largest element => use min heap.  Add (offer) element + remove (poll) top of heap. Finally return top of heap

NOTE: PriorityQueue - min heap

Sequence of problems:
1) Top 'K' Numbers (easy) - todo
2) Kth Smallest Number (easy) - done
3) 'K' Closest Points to the Origin (easy/medium) - done
4) Connect Ropes (easy) - done
5) Top 'K' Frequent Numbers (medium) - done
6) Frequency Sort (medium) - done
7) Kth Largest Number in a Stream (medium) - done
8) 'K' Closest Numbers (medium) - done
9) Maximum Distinct Elements (medium) - done
10) Sum of Elements (medium) - todo
11) Rearrange String (hard) - todo
12) Problem Challenge 1: Rearrange String K Distance Apart (hard) - todo
13) Problem Challenge 2: Scheduling Tasks (hard) - todo
14) Problem Challenge 3: Frequency Stack (hard) - todo