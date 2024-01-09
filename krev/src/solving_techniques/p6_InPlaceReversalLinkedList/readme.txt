https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63949020f34bef51e103b71b

6. In-place Reversal of a LinkedList
Usage: This technique describes an efficient way to reverse the links between a set of nodes of a LinkedList.
Often, the constraint is that we need to do this in-place, i.e., using the existing node objects and without using extra memory.

Idea:
to create prev, cur, next pointers

//initially
prev = null
cur = head

//each iteration (while (cur != null))
next = cur.next;
cur.next = prev;
prev = cur;
cur = next;

//
return prev

Sequence of problems:
1) Reverse a LinkedList (easy) - done
2) Reverse a Sub-list (medium) - done
3) Reverse every K-element Sub-list (medium) = https://leetcode.com/problems/reverse-nodes-in-k-group/ (hard) - done
4) Problem Challenge 1: Reverse alternating K-element Sub-list (medium) - todo
5) Problem Challenge 2: Rotate a LinkedList (medium) - done