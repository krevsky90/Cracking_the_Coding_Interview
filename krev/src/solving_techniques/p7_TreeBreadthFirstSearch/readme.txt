https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/6394fc8223a10518807b1643

7. Tree Breadth-First Search
Usage: As the name suggests, this technique is used to solve problems involving traversing trees in a breadth-first search manner.

Theory:
    Queue<Integer> my = new LinkedList<>();
    my.add(1);
    my.add(2);
    my.add(3);
    System.out.println(my.poll());  //returns the first element, i.e. 1
    my.add(4);
    System.out.println(my.poll());  //returns the first element, i.e. 2

For Deque we use:
    offerFirst(el) - i.e. add to the head
    pollFirst() - i.e. get from the head
    offerLast(el) - i.e. add to the tail
    pollLast() - i.e. get from the tail


Sequence of problems:
1) Binary Tree Level Order Traversal (easy) - done
2) Reverse Level Order Traversal (easy) - done
3) Zigzag Traversal (medium) - done
4) Level Averages in a Binary Tree (easy) - done
5) Minimum Depth of a Binary Tree (easy) - done
6) Level Order Successor (easy) - done
7) Connect Level Order Siblings (medium) - done
8) Problem Challenge 1: Connect All Level Order Siblings (medium) - done
9) Problem Challenge 2: Right View of a Binary Tree (easy) - done
10) https://leetcode.com/problems/n-ary-tree-level-order-traversal - done
11) https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level - done
12) https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii - done
13) https://leetcode.com/problems/check-completeness-of-a-binary-tree - done