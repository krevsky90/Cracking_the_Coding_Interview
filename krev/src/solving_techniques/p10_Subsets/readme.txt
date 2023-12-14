https://interviewnoodle.com/grokking-leetcode-a-smarter-way-to-prepare-for-coding-interviews-e86d5c9fe4e1
https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/639ca67e585e4a974dfff560

A huge number of coding interview problems involve dealing with Permutations and Combinations of a given set of elements.
This pattern describes an efficient Breadth First Search (BFS) approach to handle all these problems.

Theory:
https://www.youtube.com/watch?v=VKTKKPKX_BU

Combinatorics
    Subsets (combinations) - order is NOT significant
            with repetitions
            without repetitions
    Permutations (order is significant)
        with repetitions
        without repetitions

KREVSKY's observations:
1) for SubSets:
    use 2 methods:
        List<List<..>> method1(int[] arr) {..}

        void helper(int[] arr, List<List<..>> result, List<..> tempList, int start) {
            if (tempList.length == arr.length) result.add(new ArrayList<>(tempList));

            for (i = start; ..) {
                tempList.add(nums[i]);
                helper(arr, result, tempList, start + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

2) for Permutations:
     use 2 methods:
        List<List<..>> method1(int[] arr) {..}

        void helper(int[] arr, List<List<..>> result, List<..> tempList) {
            if (tempList.length = arr.length) result.add(new ArrayList<>(tempList));

            for (i = 0; ..) {
                tempList.add(nums[i]);
                helper(arr, result, tempList, start + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

Sequence of problems:
1) Subsets (easy) - done
2) Subsets With Duplicates (easy/medium) - done
3) Permutations (medium) - done
4) String Permutations by changing case (medium) - done
5) Balanced Parentheses (hard) - done
6) Unique Generalized Abbreviations (hard) - todo
7) Problem Challenge 1: Evaluate Expression (hard) - todo
8) Problem Challenge 2: Structurally Unique Binary Search Trees (hard) - todo
9) Problem Challenge 3: Count of Structurally Unique Binary Search Trees (hard) - todo