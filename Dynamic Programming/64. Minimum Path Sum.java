Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example:

Input:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
Output: 7
Explanation: Because the path 1→3→1→1→1 minimizes the sum.

/*
与unique path 相似
典型DP，2D-array 上的路径问题。
考虑到没有负数，也不存在往回走的情况，因此这个问题的 optimal substructure 比较简单，只依赖于来路的两个位置。
dp[i][j]:从(i,j)到终点的min path sum
1. dp[i][j] = cost[i][j]+ Math.min(dp[i,j+1],dp[i+1,j])
2. 初始化最后一行 最后一列
3. return dp[0][0]
*/

class Solution {
    public int minPathSum(int[][] grid) {
      int row = grid.length;
      int col = grid[0].length;
      // intialize last row
      for(int j=col-2;j>=0;j--){
        grid[row-1][j]+= grid[row-1][j+1];
      }
      // intialize last column
      for(int i=row-2;i>=0;i--){
        grid[i][col-1] += grid[i+1][col-1];
      }

      for(int j=col-2;j>=0;j--){
        for(int i=row-2;i>=0;i--){
          grid[i][j]+= Math.min(grid[i+1][j],grid[i][j+1]);
        }
      }
      return grid[0][0];
    }
}
