https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639f1a1e44223ca42ca4a628

Whenever we are given a sorted Array or LinkedList or Matrix,
and we are asked to find a certain element, the best algorithm we can use is the Binary Search.

NOTE: be careful with left = mid OR mid + 1!
    Consider the case for sub-array that has (right - left) = 1, because mid = left in this case => we need to add +1

Usually the algorithm:
int low = 0;
int high = nums.length - 1;
while (low <= high) {
    int mid = (low + high)/2;
    if (nums[mid] == target) return mid;

    if (nums[mid] > target) {
        high = mid - 1; //NOTE: not always, but often -1
    } else {
        low = mid + 1;  //NOTE: always +1
    }
}

Sequence of problems:
1) Order-agnostic Binary Search (easy) - done
2) Ceiling of a Number (medium) - done
3) Next Letter (medium) - done
4) Number Range (medium) - done
5) Search in a Sorted Infinite Array (medium) - done
6) Minimum Difference Element (medium) - done
7) Bitonic Array Maximum (easy) - done
8) Problem Challenge 1: Search Bitonic Array (medium) - done
9) Problem Challenge 2: Search in Rotated Array (medium) - done
10) Problem Challenge 3: Rotation Count (medium) - done

11) https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ (medium) - done
12) https://leetcode.com/problems/find-peak-element (medium) - done
13) https://leetcode.com/problems/number-of-recent-calls (easy) - done
14) https://leetcode.com/problems/koko-eating-bananas (medium) - done
15) https://leetcode.com/problems/cutting-ribbons (medium) - done
16) https://leetcode.com/problems/capacity-to-ship-packages-within-d-days (medium) - done