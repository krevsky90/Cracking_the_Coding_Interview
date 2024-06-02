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
+video to it https://www.youtube.com/watch?v=JfinxytTYFQ

time ~ (n-1 swapping is worst case) + (n steps traversing through sorted elements) ~ n-1 + n ~ 2n - 1 => O(n)
space ~ O(1)

Example:
5 3 1 2 4 | put 5 to 5-1=4th index => swap 5 and 4
4 3 1 2 5
2 3 1 4 5
3 2 1 4 5
1 2 3 4 5

Algorithm:
    public void cyclicSort(int[] arr) {
        int start = 0;
        while (start < arr.length) {
            int correctIdx = arr[start] - 1;
            if (arr[start] == arr[correctIdx]) {
                start++;
            } else {
                // if the current element is not equal to the element present at the current index
                // put the element in the correct index by swapping
                int temp = arr[start];
                arr[start] = arr[correctIdx];
                arr[correctIdx] = temp;
            }
        }
    }

Additional ideas:
1) for all problems we perform "start++" in case if correctIdx is NOT in the range of the indexes of given array
    i.e. too big ( >= arr.length) or too small (< 0)
1.2) we check if correctIdx is in the range of array's indexes BEFORE checking "arr[start] == arr[correctIdx]"
    to avoid IndexOutOfBoundException
2)
    if given array contains 0...n-1 => correctIdx = arr[start]
    if given array contains 1...n => correctIdx = arr[start] - 1


Sequence of problems:
1) Cyclic Sort (easy) - done
2) Find the Missing Number (easy) - done
3) Find all Missing Numbers (easy) - done
4) Find the Duplicate Number (easy/medium) - done
5) Find all Duplicate Numbers (easy/medium) - done

6) https://leetcode.com/problems/set-mismatch (easy) - done
7) https://leetcode.com/problems/first-missing-positive (hard) - done
8) Problem Challenge 3: Find the First K Missing Positive Numbers (hard) - todo