There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[17,2,17],[16,16,5],[14,3,19]]
Output: 10
Explanation: Paint house 0 into blue, paint house 1 into green, paint house 2 into blue.
             Minimum cost: 2 + 5 + 3 = 10.

/*
3 colors, different cost of paint house with diff colors. No same color of two adjacent.
find minimum cost.
paint i只跟i-1有关，与i-1不同色，从剩下两个颜色中找到total cost最小的
可见对于每个house i，都得保存用R B G三色涂i的 min total cost
dp[i][j] :用颜色j涂i house的min total cost

1. dp[i][0]= cost[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
  dp[i][1]= cost[i][1] + Math.min(dp[i-1][0],dp[i-1][2]);
  dp[i][2]= cost[i][2] + Math.min(dp[i-1][0],dp[i-1][1]);
2. return dp[n][0~2]中最小值
3. 初始化dp[0][0],dp[0][1],dp[0][2] 为cost[i][j]

时间复杂度：n*3(# of colors) *2 (为确定每个dp[i][j], 都要visit dp[i-1]中的两个值)
优化空间：
1.由于只用保存i和i-1两个状态， 存dp[0~1][0~2]就够了
2.或者直接修改costs array 如果允许的话
*/
class Solution {
    public int minCost(int[][] costs) {
      if(costs.length==0) return 0;
      //use 第一种空间优化
      int[][] dp = new int[2][3];
      //initialization
      dp[0][0] = costs[0][0];
      dp[0][1] = costs[0][1];
      dp[0][2] = costs[0][2];

      for(int i=1;i<n;i++){
        dp[i%2][0] = costs[i][0] + Math.min(dp[(i-1)%2][1], dp[(i-1)%2][2]);
        dp[i%2][1] = costs[i][1] + Math.min(dp[(i-1)%2][0], dp[(i-1)%2][2]);
        dp[i%2][2] = costs[i][2] + Math.min(dp[(i-1)%2][0], dp[(i-1)%2][1]);
      }
      return Math.min(dp[(n-1)%2][0], Math.min(dp[(n-1)%2][1], dp[(n-1)%2][2]));
    }
}
