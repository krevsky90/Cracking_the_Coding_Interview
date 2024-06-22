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

!!! READ: https://leetcode.com/problems/permutations/solutions/18239/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning/

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

2) for Permutations of unique elements:
     use 2 methods:
        NOTE: if we consider the combinations created by this way as numbers, then these numbers will be in increasing sequence!

        List<List<..>> method1(int[] arr) {
            helper(..)
        }

        private void permute(int[] nums, List<List<Integer>> result, List<Integer> tempList) {
            if (tempList.size() == nums.length) {
                result.add(new ArrayList<>(tempList));
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (tempList.contains(nums[i])) continue;   //idea #1: skip since all elements should be unique!

                    tempList.add(nums[i]);
                    permute(nums, result, tempList);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }

    OR
    use logic from solving_techniques/p10_Subsets/Permutations.java # permute3
    (see https://www.geeksforgeeks.org/print-all-possible-permutations-of-an-array-vector-without-duplicates-using-backtracking/)
    NOTE: it is slower, than the previous solution

    void permute(int[] nums, int left, int right, List<List<Integer>> result) {
            if (left == right) {
                result.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
                return;
            }

            for (int i = left; i < right; i++) {
                swap(nums, left, i);
                permute(nums, left + 1, right, result);
                swap(nums, left, i);
            }
        }

2.2) for Permutations with Duplicates:
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);  //idea #1: sort!
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                //idea #2: skip according to the logic below
                if(used[i]) continue;
                //NOTE: looks like "&& !used[i - 1]" is also ok, but I prefer used[i-1]
                if(i > 0 && nums[i] == nums[i-1] && used[i - 1]) continue;

                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }



Sequence of problems:
1) Subsets (easy) - done
2) Subsets With Duplicates (easy/medium) - done
3) Permutations (medium) - done
4) String Permutations by changing case (medium) - done
5) Balanced Parentheses (hard) - done
6) Unique Generalized Abbreviations (hard) - done
7) Problem Challenge 1: Evaluate Expression (hard) - done
8) Problem Challenge 2: Structurally Unique Binary Search Trees (hard) - done
9) Problem Challenge 3: Count of Structurally Unique Binary Search Trees (hard) - done

10) https://leetcode.com/problems/next-permutation (medium) - done
11) https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/ (medium) - done
12) https://leetcode.com/problems/combinations (medium) - done
13) https://leetcode.com/problems/palindrome-partitioning (medium) done
14) https://leetcode.com/problems/permutation-sequence (hard) - done
15) https://leetcode.com/problems/number-of-squareful-arrays (hard) - done