https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639b680a0dbaa5118a4b6326

In many problems, where we are given a set of elements such that we can divide them into two parts.
To solve the problem, we are interested in knowing the smallest element in one part and the biggest element in the other part.
This pattern is an efficient approach to solve such problems.

This pattern uses two Heaps to solve these problems;
A Min Heap to find the smallest element and a Max Heap to find the biggest element.

Key-Idea of 'add' method:
    private void addElementToHeaps(PriorityQueue<Integer> leftMaxHeap, PriorityQueue<Integer> rightMinHeap, int n) {
        if (leftMaxHeap.size() == 0 || leftMaxHeap.peek() >= n) {
            leftMaxHeap.offer(n);
        } else {
            rightMinHeap.offer(n);
        }

        rebalance(leftMaxHeap, rightMinHeap);
    }


Sequence of problems:
1) Find the Median of a Number Stream (medium) - done
2) Sliding Window Median (hard) - done
3) Maximize Capital (hard) - todo
4) Problem Challenge 1: Next Interval (hard) - todo