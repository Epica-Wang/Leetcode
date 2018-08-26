public class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    clearRestOfLand(grid, i, j);
                }
            }
        }
        return count;
    }

    private void clearRestOfLand(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[i].length || grid[i][j] == '0') return;

        grid[i][j] = '0';
        clearRestOfLand(grid, i+1, j);
        clearRestOfLand(grid, i-1, j);
        clearRestOfLand(grid, i, j+1);
        clearRestOfLand(grid, i, j-1);
        return;
    }
}

//////////////07.12.2018重写练习 dfs版
/*
每当访问到一个”1”后，继续dfs它四个方向
需要注意的是当访问过一个“1”的时候，需要把它改成别的。之后碰到的话就不再dfs它四个方向
不改的话可能会一直跑下去  1重复被visit
如：1 1 1
   1 1 1
*/
class Solution {
    public int numIslands(char[][] grid) {
      int row = grid.length;
      if(row==0) return 0;
      int col = grid[0].length;
      if(col==0) return 0;
      int count =0;
      for(int i=0;i<row;i++){
        for(int j=0;j<col;j++){
          if(grid[i][j]=='1'){
            count++;
            dfs(grid,i,j,row,col);
          }
        }
      }
      return count;
    }

    public void dfs(char[][] grid, int i, int j,int row, int col){
      if(i<0||i>=row||j<0||j>=col) return;
      if(grid[i][j]=='0'||grid[i][j]=='-1') return;
      if(grid[i][j]=='1'){
        grid[i][j]='-1';
        dfs(grid,i+1,j,row,col);
        dfs(grid,i-1,j,row,col);
        dfs(grid,i,j+1,row,col);
        dfs(grid,i,j-1,row,col);
      }
    }
}
