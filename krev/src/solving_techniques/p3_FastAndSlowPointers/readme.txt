https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639104b92e1c0a1cfe6c1e68

3. Fast & Slow Pointers
Usage: Also known as Hare & Tortoise algorithm. In this technique, we use two pointers that traverse the input data at a different speed.

Tips:
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;
}
for even number of nodes we break while-loop since fast = null
for odd number of nodes we break while-loop since fast.next = null

Sequence of problems:
1) LinkedListCycle - done
2) MiddleOfLinkedList - done
3) Cracking_the_Coding_Interview\krev\src\data_structures\chapter2_linked_lists\Problem2_8.java - done
4) HappyNumber - done
5) ProblemChallenge1_PalindromeLinkedList - done
6) ProblemChallenge2_RearrangeLinkedList - done
7) ProblemChallenge3_CycleInCircularArray (hard) - todo

8) https://leetcode.com/problems/delete-the-middle-node-of-a-linked-list - done
9) https://leetcode.com/problems/maximum-twin-sum-of-a-linked-list - done
10) https://leetcode.com/problems/remove-nth-node-from-end-of-list (medium)- done
11) https://leetcode.com/problems/swapping-nodes-in-a-linked-list - todo