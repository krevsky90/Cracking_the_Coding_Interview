info: https://leetcode.com/discuss/general-discussion/458695/Dynamic-Programming-Patterns#Minimum-(Maximum)-Path-to-Reach-a-Target

Generate problem statement for this pattern

============== Statement ==============
Given a target find minimum (maximum) cost / path / sum to reach the target.

============== Approach ==============
Choose minimum (maximum) path among all possible paths before the current state, then add value for the current state.

routes[i] = min(routes[i-1], routes[i-2], ... , routes[i-k]) + cost[i]
Generate optimal solutions for all values in the target and return the value for the target.

============== Top-Down ==============
for (int j = 0; j < ways.size(); ++j) {
    result = min(result, topDown(target - ways[j]) + cost/ path / sum);
}
return memo[/*state parameters*/] = result;

============== Bottom-Up ==============
for (int i = 1; i <= target; ++i) {
   for (int j = 0; j < ways.size(); ++j) {
       if (ways[j] <= i) {
           dp[i] = min(dp[i], dp[i - ways[j]] + cost / path / sum) ;
       }
   }
}

return dp[target]

---------------------------------------------------------
Problem list: https://leetcode.com/list/55ac4kuc

1) 64. Minimum Path Sum https://leetcode.com/problems/minimum-path-sum - done
2) 120. Triangle https://leetcode.com/problems/triangle - done
3) 174. Dungeon Game https://leetcode.com/problems/dungeon-game (hard) - todo
4) 221. Maximal Square https://leetcode.com/problems/maximal-square - done
5) 279. Perfect Squares https://leetcode.com/problems/perfect-squares - done
6) Coin change - done
7) 474. Ones and Zeroes https://leetcode.com/problems/ones-and-zeroes
8) 650. 2 Keys Keyboard https://leetcode.com/problems/2-keys-keyboard - done
9) 746. Min Cost Climbing Stairs https://leetcode.com/problems/min-cost-climbing-stairs - done (src/solving_techniques/p17_FibonacciNumbers/MinimumJumpsWithFee.java)
10)
11) 931. Minimum Falling Path Sum
12) imum Cost For Tickets