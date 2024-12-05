https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63949020f34bef51e103b71b

6. In-place Reversal of a LinkedList
Usage: This technique describes an efficient way to reverse the links between a set of nodes of a LinkedList.
Often, the constraint is that we need to do this in-place, i.e., using the existing node objects and without using extra memory.

======== How to  reverse sublist ========
1) create prev, cur, next pointers
2) initiate:
    prev = null
    cur = head
3) while (cur != null):
        next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
4) return prev

Good patterns and solutions:
https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20%20Pattern%2006:%20In-place%20Reversal%20of%20a%20LinkedList.md

======== How to to reverse sublist(s), we do the following ========
store:
- current (initially = head)
- previous (initially = null)
- lastNodeOfPreviousPart (initially = null). To link previous part and just reversed
- headOfSubList ( = current, then it will become end node of reversed sublist). To link just reversed part to the following part (i.e. 'current' node)
idea:
1) reverse sublist => 'previous' is new head of this sublist
2) connect 'lastNodeOfPreviousPart' and 'previous'
3) connect 'headOfSubList' (former head of sublist, after reversing - tail of sublist) to 'current' (i.e. the next part of list)
4) move 'lastNodeOfPreviousPart' (to the last element of reversed part, if we reverse many sublists one-by-one)
5) set headOfSubList = current (usually in the beginning of while loop)


Sequence of problems:
1) Reverse a LinkedList (easy) - done
2) Reverse a Sub-list (medium) - done
3) Reverse every K-element Sub-list (medium) = https://leetcode.com/problems/reverse-nodes-in-k-group/ (hard) - done
4) Problem Challenge 1: Reverse alternating K-element Sub-list (medium) - done
5) Problem Challenge 2: Rotate a LinkedList (medium) - done

6) https://leetcode.com/problems/swap-nodes-in-pairs (medium) - done
7) https://leetcode.com/problems/reverse-nodes-in-even-length-groups (medium) - done
8) https://leetcode.com/problems/odd-even-linked-list (medium) - done