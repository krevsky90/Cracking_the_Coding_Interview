https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63937bd6122839c3d3b4f9ec

This pattern describes an interesting approach to deal with problems involving arrays containing numbers in a given range.

For example, take the following problem:
You are given an unsorted array containing numbers taken from the range 1 to n The array can have duplicates, which means that some numbers will be missing. Find all the missing numbers.

To efficiently solve this problem, we can use the fact that the input array contains numbers in the range of 1 to n.
For example, to efficiently sort the array, we can try placing each number in its correct place,
i.e., placing 1 at index 0, placing 2 at index 1, and so on.
Once we are done with the sorting,
we can iterate the array to find all indices that are missing the correct numbers.
These will be our required numbers.

one more theory:
https://leetcode.com/discuss/study-guide/2958275/cyclic-sort-important-pattern


Sequence of problems:
1) Cyclic Sort (easy) - done
2) Find the Missing Number (easy) - done
3) Find all Missing Numbers (easy) - done
4) Find the Duplicate Number (easy/medium) - done
5) Find all Duplicate Numbers (easy/medium) - done

6) https://leetcode.com/problems/set-mismatch (easy) - todo
7) https://leetcode.com/problems/first-missing-positive (hard) - todo