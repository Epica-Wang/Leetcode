/*
3 2
2 1
1 0
subproblem: 每个位置的# path = 右边+下边
dp[i][j] = dp[i][j+1](right) + dp[i+1][j](below)
initial condition: dp[n-1][m-1]=1; dp[n-1][0~m-2]=1(最下面一行)=1; dp[0~n-2][m-1]=1 (最右边一列)
return: dp[0][0]
得从倒数第二行开始  一行一行往上填   每行填的方向从右向左
*/

class Solution {
    public int uniquePaths(int m, int n) {
      //initialize
      int[][] dp = new int[n][m];
      for(int i=0;i<m;i++){
        dp[n-1][i] = 1;
      }
      for(int j=0; j<n;j++){
        dp[j][m-1]=1;
      }

      for(int row=n-2;row>=0;row--){
        for(int col=m-2;col>=0;col--){
          dp[row][col] = dp[row][col+1] + dp[row+1][col];
        }
      }
      return dp[0][0];
    }
}

// 一个recursive的写法
class Solution {
  public int uniquePaths(int m, int n) {
    if (m == 1 || n == 1) {
      return 1;
    }
    return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
  }
}
