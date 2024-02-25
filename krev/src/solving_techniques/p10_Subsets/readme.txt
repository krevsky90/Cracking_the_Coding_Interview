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

        ============== DEBUG EXAMPLE (START) ==========
        arr = 1 2 3
        result ={{},{1},{1,2},{1,2,3},{1,3},{2},{2,3},{3}}


        helper(123,res,{},0)
            i = 0:
                tempList = {1}
                helper(123,res,{1},1)
                    i = 1:
                        tempList = {1,2}
                        helper(123,res,{1,2},2)
                            i = 2:
                                tempList = {1,2,3}
                                helper(123,res,{1,2,3},3)
                    i = 2:
                        tempList = {1,3}
                        helper(123,res,{1,3},3)

            i = 1:
                tempList = {2}
                helper(123,res,{2},2)
                    i = 2:
                        tempList = {2,3}
                        helper(123,res,{2,3},3)

            i = 2:
                tempList = {3}
                helper(123,res,{3},3)
        ============== DEBUG EXAMPLE (END) ==========


        void helper(int[] arr, List<List<..>> result, List<..> tempList, int start) {
            result.add(new ArrayList(tempList));
            for (i = start; ..) {
                tempList.add(nums[i]);
                helper(arr, result, tempList, i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

2) for Permutations:
     use 2 methods:
        List<List<..>> method1(int[] arr) {..}

        void helper(int[] arr, List<List<..>> result, List<..> tempList) {
            if (tempList.length = arr.length) result.add(new ArrayList(tempList));

            for (i = 0; ..) {
                if (tempList.contains(nums[i])) continue;   //idea #1: skip since all elements should be unique!

                tempList.add(nums[i]);
                helper(arr, result, tempList, start + 1);
                tempList.remove(tempList.size() - 1);
            }
        }

    OR
    use logic from solving_techniques/p10_Subsets/Permutations.java # permute3
    (see https://www.geeksforgeeks.org/print-all-possible-permutations-of-an-array-vector-without-duplicates-using-backtracking/)

Sequence of problems:
1) Subsets (easy) - done
2) Subsets With Duplicates (easy/medium) - done
3) Permutations (medium) - done
4) String Permutations by changing case (medium) - done
5) Balanced Parentheses (hard) - done
6) Unique Generalized Abbreviations (hard) - todo
7) Problem Challenge 1: Evaluate Expression (hard) - todo
8) Problem Challenge 2: Structurally Unique Binary Search Trees (hard) - done
9) Problem Challenge 3: Count of Structurally Unique Binary Search Trees (hard) - done

10) https://leetcode.com/problems/next-permutation (medium) - done
11) https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/ (medium) - done
12) https://leetcode.com/problems/combinations/description/ (medium) - done
13) https://leetcode.com/problems/palindrome-partitioning/ (medium) done