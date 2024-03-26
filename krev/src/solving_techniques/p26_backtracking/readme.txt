https://www.designgurus.io/course-play/grokking-the-coding-interview/doc/63d3bcd7f81b8e2fe5ded81c

Backtracking is an algorithmic technique that uses brute-force approach to solve a problem.

Brute-force approach states that for any problem,
we should try out all possible solutions and pick up those solutions that satisfy the problem constraints.

In backtracking, we build a solution incrementally and follow the approach that if the current solution can?t lead to a valid solution,
 abandon it and backtrack (or go back) to try another solution.
 Because of this, recursion becomes a suitable technique for solving backtracking problems.

Dynamic Programming (DP) uses a similar approach where we try out all possible solutions (using Memoization)
 to pick up the most optimized solution. DP is used to solve optimization problems; backtracking,
 on the other hand, is mostly used when multiple valid solutions are possible for a problem.

Template for problem like 'find all combinations':
    private void helper(int[] candidates, int target, List<Integer> tempResult, List<List<Integer>> result, int startIdx) {
        if (target == 0) {
            result.add(new ArrayList<>(tempResult));
        } else if (target < 0) {
            return; //no solution
        } else {
             for (int i = startIdx; i < candidates.length; i++) {
                 //<here might be some code specific to the problem (like duplicate validation etc)>
                 tempResult.add(candidates[i]);
                 //where XXX = i in case if the elements (of candidates) can be used multiple times
                 //XXX = i + 1 in case if  the elements (of candidates) can be used only once
                 helper(candidates, target - candidates[i], tempResult, result, XXX);
                 tempResult.remove(tempResult.size() - 1);
             }
        }
    }
    //NOTE: validation "startIdx >= candidates.length" is NOT necessary since in case if startIdx >= candidates.length
    //we will execute 'else' block and for-loop will not be executed at all since the condition "i < candidates.length" is false

Sequence of problems:
1) Combination Sum (medium) - done
2) Word Search (medium) - done
3) Sudoku Solver (hard) - done
4) Factor Combinations (medium) - done
5) Split a String Into the Max Number of Unique Substrings (medium) - done

6) https://leetcode.com/problems/combination-sum-ii (medium) - done
7) https://leetcode.com/problems/combination-sum-iii (medium) - done

Common list of problems is here: https://leetcode.com/tag/backtracking