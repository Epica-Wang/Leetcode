Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1 and return its area.

Example:

Input:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
//https://www.youtube.com/watch?v=vkFUB--OYy0
/*
O(n^2)solution:
突破点在于找出optimal subproblem
从top left向bottom right扩展
1. dp[i][j]表示：以(i,j)为右下端点，从（0，0）到（i，j）所能组成的最大正方形的边长
  dp[i][j]依赖于三个subproblem:
    1.dp[i][j-1] {代表从(i，j)这个点往左最多expend多少}
    2.dp[i-1][j] {代表从(i，j)这个点往上最多expend多少}
    3.dp[i-1][j-1] {代表从(i，j)这个点左上对角线最多expend多少}

  则有：
  dp[i][j]= 0： 若matrix[i][j]=0
  else:
  dp[i][j] = 1 + min(dp[i][j], dp[i][j], dp[i-1][j-1])

2. 初始化：第一行第一列
3. 计算dp[i][j]过程中update max  return max的平方
*/

/*
没考虑到的test case：
1.[]:row=0 return0
2.[[]]: col=0 return 0
3.[[1]]: intialize完没有更新maximal size
*/
class Solution {
    public int maximalSquare(char[][] matrix) {
      int maximalSize = 0;
      int row = matrix.length;
      if(row==0) return 0;
      int col = matrix[0].length;
      if(col==0) return 0;
      int[][] dp = new int[row][col];
      //initialize first row
      for(int j=0;j<col;j++){
        dp[0][j]= matrix[0][j]-'0';
        maximalSize = Math.max(maximalSize,dp[0][j]);
      }
      //intialize first column
      for(int i=0;i<row;i++){
        dp[i][0] = matrix[i][0]-'0';
        maximalSize = Math.max(maximalSize,dp[i][0]);
      }

      for(int i=1;i<row;i++){
        for(int j=1;j<col;j++){
          if(matrix[i][j]=='0'){
            dp[i][j] = 0;
          }else{
            dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]);
            maximalSize = Math.max(maximalSize,dp[i][j]);
          }
        }
      }
      return maximalSize*maximalSize;
    }
}
