There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

Note:
n and k are non-negative integers.

Example:

Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:

            post1  post2  post3
 -----      -----  -----  -----
   1         c1     c1     c2
   2         c1     c2     c1
   3         c1     c2     c2
   4         c2     c1     c1
   5         c2     c1     c2
   6         c2     c2     c1

/*
k colors, 注意限制是no more than two adjacent fence have the same color. 相邻的两个可以是same color
dp[i]表示涂到第i个fence时有多少种方法，i与i-1, i-2有关。
1. i has same color with i-1
dp[i] = (k-1) * dp[i-2];

2. i has different color with i-1
dp[i] = (k-1) * dp[i-1];
所以in total dp[i]等于上面两种加起来
*/

class Solution {
    public int numWays(int n, int k) {
      if(n==0||k==0) return 0;

      int[] dp = new int[n];
      dp[0] = k;
      if(n==1) return dp[0];
      
      dp[1] = dp[0]+ (k-1)*dp[0];
      for(int i=2;i<n;i++){
        //i has same color with i-1
        dp[i] += (k-1) * dp[i-2];
        //i has different color with i-1
        dp[i] += (k-1) * dp[i-1];
      }
      return dp[n-1];
    }
}
