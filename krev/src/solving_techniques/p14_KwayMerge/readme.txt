https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63a1eda0e79544420e750f36

https://github.com/Chanda-Abdul/Several-Coding-Patterns-for-Solving-Data-Structures-and-Algorithms-Problems-during-Interviews/blob/main/%E2%9C%85%20Pattern%2014%3A%20K-way%20merge.md

This pattern helps us solve problems that involve a list of sorted arrays.

Whenever we are given ?K? sorted arrays,
we can use a Heap to efficiently perform a sorted traversal of all the elements of all arrays.
We can push the smallest (first) element of each sorted array in a Min Heap to get the overall minimum.
While inserting elements to the Min Heap we keep track of which array the element came from.
We can, then, remove the top element from the heap to get the smallest element and push the next element from the same array,
to which this smallest element belonged, to the heap

Theory:
from https://www.geeksforgeeks.org/merge-k-sorted-linked-lists-set-2-using-min-heap/
1) Create a min-heap and insert the first element of all the ?k? linked lists.
2) As long as the min-heap is not empty, perform the following steps:
3) Remove the Root of the min-heap (which is the current minimum among all the elements in the min-heap) and add it to the result list.
3.1) If there exists an element (in the same linked list) next to the element that popped out in the previous step, then insert it into the min-heap.
4) Return the head node address of the merged list.

NOTE: hack to avoid if-else while storing the link to 'result' node - to create fake head + temp pointer that will link all nodes during the algorithm. and finally return head.next
    see MergeKSortedLists # mergeKListsGFG()

Sequence of problems:
1) Merge K Sorted Lists (medium) - done
2) Kth Smallest Number in M Sorted Lists (medium) - done
3) Kth Smallest Number in a Sorted Matrix (hard) - done
4) Smallest Number Range (hard) - todo
5) Problem Challenge 1: K Pairs with Largest Sums (hard) - todo
