There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5;
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5.
Follow up:
Could you solve it in O(nk) runtime?

/*
与256 paint house I相似，但有k个color。
若还是按照I中的方法，更新当前行的某个值时需要搜索上一行和当前不同列的所有可能，则当k很大时，会十分耗费时间。
时间复杂度为：O(n*k*k)

优化的方法为：
1. 只记录6个值，前一行最小值，第二小值，最小值的index, 目前行最小值，第二小值，最小值的index。
2. 更新当前行元素，当前行元素记录的是当前行房子涂该种颜色时的最小值，只可能由该元素与上一行的最小值或次小值加和得到。
  遍历当前行的每个元素，若该元素的列和前一行最小值index不同，则更新为当前行元素值＋上一行最小值，
  若和上一行最小值index相同，则更新为当前元素值＋上一行次小值。
  同时将该值和当前行的最小值和次小值比较，更新当前行的最小值，次小值和最小值的index。
3.重复2直到遍历所有行。

时间O(N=nk)，空间O(1)。
*/

class Solution {
    public int minCostII(int[][] costs) {
      int lastMin = Integer.MAX_VALUE;
      int lastSecMin = Integer.MAX_VALUE;
      int lastMinIndex = 0;

      int n = costs.length;
      int k = costs[0].length;
      int[][] dp = new int[2][k];
      //initialization dp[0] and lastMin lastSecMin lastMinIndex
      for(int i=0;i<k;i++){
        dp[0][i] = costs[0][i];
        if(dp[0][i]<= lastSecMin){
          if(dp[0][i]<= lastMin){
            lastSecMin = lastMin;
            lastMin = dp[0][i];
            lastMinIndex = i;
          }else{
            lastSecMin = dp[0][i];
          }
        }
      }
      // build dp from house 1
      for(int house = 1;house<n;house++){
        // reset curMin curSecMin curMinIndex for each new house
        int curMin = Integer.MAX_VALUE;
        int curSecMin = Integer.MAX_VALUE;
        int curMinIndex = 0;

        for(int color = 0;color<k;color++){
          // update dp[house%2][color]
          if(color!=lastMinIndex){
            dp[house%2][color] = costs[house][color]+ lastMin;
          }else{
            dp[house%2][color] = costs[house][color] + lastSecMin;
          }
          //check if need to update curMin curSecMin curMinIndex
          if(dp[house%2][color]<=curSecMin){
            if(dp[house%2][color]<=curMin){
              curSecMin = curMin;
              curMin = dp[house%2][color];
              curMinIndex = color;
            }else{
              curSecMin = dp[house%2][color];
            }
          }
        }
        // after finish this house, change cur to last
        lastMin = curMin;
        lastSecMin = curSecMin;
        lastMinIndex = curMinIndex;
      }
      return lastMin;
    }
}
