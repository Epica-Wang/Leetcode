//An obstacle and empty space is marked as 1 and 0 respectively in the grid.
/*不能像62题一样直接intialize最右边列和最下面一行
考虑例子：
[[0,0],
[1,1],
[0,0]]
这时候像62那样初始化方式是不对的

*/
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      // convert obstacle 1 to -1.
      int n = obstacleGrid.length;
      int m = obstacleGrid[0].length;
      if(obstacleGrid[0][0]==1) return 0;

      //必须先初始化[n-1][m-1]
      if(obstacleGrid[n-1][m-1]==1){
        return 0;
      }else{
        obstacleGrid[n-1][m-1]=1;
      }

      //initialize last row, each location depends on its right location
      for(int col=m-2;col>=0;col--){
        // itself is obstacle or its right is obstacle
        if(obstacleGrid[n-1][col]==1 || obstacleGrid[n-1][col+1]==-1){
          obstacleGrid[n-1][col]=-1;
        }else{
          obstacleGrid[n-1][col] = obstacleGrid[n-1][col+1];
        }
      }
      //initialize last col, each location depends on its below location
      for(int row=n-2;row>=0;row--){
        if(obstacleGrid[row][m-1]==1 || obstacleGrid[row+1][m-1]==-1){
          obstacleGrid[row][m-1]=-1;
        }else{
          obstacleGrid[row][m-1]= obstacleGrid[row+1][m-1];
        }
      }
//////
      for(int row=n-2;row>=0;row--){
        for(int col=m-2;col>=0;col--){
          if(obstacleGrid[row][col]==1){
            obstacleGrid[row][col]=-1;
            continue;
          }else{
            obstacleGrid[row][col]= (obstacleGrid[row+1][col]==-1? 0:obstacleGrid[row+1][col]) + (obstacleGrid[row][col+1]==-1? 0:obstacleGrid[row][col+1]);
          }
        }
      }
      return obstacleGrid[0][0]==-1? 0:obstacleGrid[0][0];
    }
}

/////////////////// 7.9重写练习
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
      int row = obstacleGrid.length;
      int col = obstacleGrid[0].length;
      if(obstacleGrid[row-1][col-1]==1){
        return 0;
      }
      //则最后一个位置是到自己的路径是1
      obstacleGrid[row-1][col-1]=1;
      //initialize last row
      for(int i=col-2;i>=0;i--){
        if(obstacleGrid[row-1][i]==1){
          obstacleGrid[row-1][i]=-1;
        }else{
        obstacleGrid[row-1][i]= obstacleGrid[row-1][i+1]==-1? -1 :1;
        }
      }
      //initialize last col
      for(int j=row-2;j>=0;j--){
        if(obstacleGrid[j][col-1]==1){
          obstacleGrid[j][col-1]=-1;
        }else{
          obstacleGrid[j][col-1]= obstacleGrid[j+1][col-1]==-1? -1:1;
        }
      }

      for(int i=row-2;i>=0;i--){
        for(int j=col-2;j>=0;j--){
          if(obstacleGrid[i][j]==1){
            obstacleGrid[i][j]=-1;
            continue;
          }
          if(obstacleGrid[i+1][j]==-1 && obstacleGrid[i][j+1]==-1){
            obstacleGrid[i][j]=-1;
          }else if(obstacleGrid[i+1][j]==-1){
            obstacleGrid[i][j] = obstacleGrid[i][j+1];
          }else if(obstacleGrid[i][j+1]==-1){
            obstacleGrid[i][j] = obstacleGrid[i+1][j];
          }else{
            obstacleGrid[i][j] = obstacleGrid[i+1][j]+obstacleGrid[i][j+1];
          }
        }
      }
      return obstacleGrid[0][0]==-1? 0:obstacleGrid[0][0];
    }
}
