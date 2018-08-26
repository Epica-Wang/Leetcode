Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1 (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
Given the above grid, return 6. Note the answer is not 11, because the island must be connected 4-directionally.
Example 2:
[[0,0,0,0,0,0,0,0]]
Given the above grid, return 0.
Note: The length of each dimension in the given grid does not exceed 50.

//跟number of island一样 不过不用count island，而是更新maxArea

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
      int maxArea = 0;
      int row = grid.length;
      if(row==0) return 0;
      int col = grid[0].length;
      if(col==0) return 0;
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
          if(grid[i][j]==1){
            maxArea = Math.max(dfs(grid,i,j,row,col), maxArea);
          }
        }
      }
      return maxArea;
    }
    public int dfs(int[][] grid, int i, int j,int row, int col){
      if(i<0||i>=row||j<0||j>=col) return 0;
      if(grid[i][j]==0||grid[i][j]==2) return 0;
      grid[i][j]=2;
      return 1 + dfs(grid,i+1,j,curArea,row,col)+
      dfs(grid,i-1,j,curArea,row,col)+
      dfs(grid,i,j+1,curArea,row,col)+
      dfs(grid,i,j-1,curArea,row,col);
    }
}
/*
下面是个错误写法  不能把current Area当成参数传给dfs
current Area对于dfs的四个方向来说应该是累加的 不能把current Area当成参数传给dfs
考虑栗子：
1 1
1 0
左上角第一个1visit完后，传给右1和下1的current area都是1   右1和下1分别都给current area加了1  所以最后的current area是2 而不是3
class Solution {
    private int maxArea=0;
    public int maxAreaOfIsland(int[][] grid) {
      int row = grid.length;
      if(row==0) return 0;
      int col = grid[0].length;
      if(col==0) return 0;
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
          if(grid[i][j]==1){
            dfs(grid,i,j,0,row,col);
          }
        }
      }
      return maxArea;
    }
    public void dfs(int[][] grid, int i, int j, int curArea,int row, int col){
      if(i<0||i>=row||j<0||j>=col) return;
      if(grid[i][j]==0||grid[i][j]==2) return;
      grid[i][j]=2;
      curArea +=1;
      maxArea = Math.max(curArea,maxArea);
      dfs(grid,i+1,j,curArea,row,col);
      dfs(grid,i-1,j,curArea,row,col);
      dfs(grid,i,j+1,curArea,row,col);
      dfs(grid,i,j-1,curArea,row,col);
    }
}
*/
