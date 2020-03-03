In a given grid, each cell can have one of three values:

the value 0 representing an empty cell;
the value 1 representing a fresh orange;
the value 2 representing a rotten orange.
Every minute, any fresh orange that is adjacent (4-directionally) to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.  If this is impossible, return -1 instead.

/*

*/
class Solution {
    public int orangesRotting(int[][] grid) {
      int length = grid.length;
      int width = grid[0].length;
      // find all rotten orange at beginning. use queue to record rotten orange.
      // at beginning keep record of the size of current queue
      Queue<Integer> rotten = new LinkedList<>();
      for (int i = 0; i < length; i++){
        for (int j = 0; j < width; j++) {
          if (grid[i][j] == 2) {
            int location = i * width + j;
            rotten.add(location);
          }
        }
      }

      int minute = 0;
      while (!rotten.isEmpty()) {
        int currSize = rotten.size();
        while (currSize > 0) {
          // iterate through adj to add possible new rotting orange
          int location = rotten.remove();
          int column = location % width;
          int row = location / width;
          // up
          if ((row-1) >=0 && grid[row-1][column] == 1) {
            grid[row-1][column] = 2;
            int newLocation = (row-1) * width + column;
            rotten.add(newLocation);
          }
          // down
          if ((row+1) < length && grid[row+1][column] == 1) {
            grid[row+1][column] = 2;
            int newLocation = (row+1) * width + column;
            rotten.add(newLocation);
          }
          // left
          if ((column - 1) >= 0 && grid[row][column - 1] == 1) {
            grid[row][column - 1] = 2;
            int newLocation = row * width + column -1;
            rotten.add(newLocation);
          }
          // right
          if ((column + 1) < width && grid[row][column + 1] == 1) {
            grid[row][column + 1] = 2;
            int newLocation = row * width + column +1;
            rotten.add(newLocation);
          }
          currSize--;
          if (currSize == 0) {
            minute ++;
          }
        }
      }

      // finally iterate through whole grid to see if there is still fresh orange
      for (int[] row: grid) {
        for (int v: row) {
          if (v == 1){
            return -1;
          }
        }
      }

      return minute - 1;
    }
}
