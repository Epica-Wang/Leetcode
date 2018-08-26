Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1s and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
/*
受221启发 不过三维dp  因为是矩形 所以每个index得记录长，宽两个值
dp[i][j][0]: 以(i,j)为右下端点，从（0，0）到（i，j）所能组成的最大矩形的长
dp[i][j][1]: 以(i,j)为右下端点，从（0，0）到（i，j）所能组成的最大矩形的宽

1.dp[i][j] : 依赖于三个子问题：dp[i-1][j], dp[i][j-1], dp[i-1][j-1]
  dp[i][j]的长：dp[i][j][0] = 1 + min(dp[i][j-1][0], dp[i-1][j-1][0])
  dp[i][j]的高：dp[i][j][1] = 1 + min(dp[i-1][j][1], dp[i-1][j-1][1])

2. 初始化。仍然第一行 第一列  但第一行每个位置与前一个位置有关，第一列每个位置与上一个位置有关
所以在初始化的时候，还需要先初始化dp[0][0][0]和dp[0][0][1]
*/
/*
class Solution {
    public int maximalRectangle(char[][] matrix) {
      int row = matrix.length;
      if(row==0) return 0;
      int col = matrix[0].length;
      if(col==0) return 0;
      int maximalSize = 0;
      int[][][] dp = new int[row][col][2];

      if(matrix[0][0]=='0'){
        dp[0][0][0]=0;
        dp[0][0][1]=0;
      }else{
        dp[0][0][0]=1;
        dp[0][0][1]=1;
        maximalSize = 1;
      }
      //initialize first row
      for(int j=1;j<col;j++){
        if(matrix[0][j]=='0'){
          dp[0][j][0]=0;
          dp[0][j][0] = 0;
        }else{
          dp[0][j][0]= 1+dp[0][j-1][0];
          dp[0][j][1]= 1;
        }
        maximalSize = Math.max(maximalSize,dp[0][j][0]*dp[0][j][1]);
      }
      //intialize first column
      for(int i=1;i<row;i++){
        if(matrix[i][0]=='0'){
          dp[i][0][0] = 0;
          dp[i][0][1] = 0;
        }else{
          dp[i][0][0] = 1;
          dp[i][0][1] = 1+dp[i-1][0][1];
        }
        maximalSize = Math.max(maximalSize,dp[i][0][0]*dp[i][0][1]);
      }

      for(int i=1;i<row;i++){
        for(int j=1;j<col;j++){
          if(matrix[i][j]=='0'){
            dp[i][j][0]=0;
            dp[i][j][1]=1;
          }else{
            dp[i][j][0] = 1+Math.min(dp[i][j-1][0], dp[i-1][j-1][0]);
            dp[i][j][1] = 1+Math.min(dp[i-1][j][1], dp[i-1][j-1][1]);
            maximalSize = Math.max(maximalSize,dp[i][j][0]*dp[i][j][1]);
          }
        }
      }
      return maximalSize;
    }
}
*/
