给个array,其中只有一格是9，其他格子是0或1，0表示此路不通，1表示可以走，
判断从（0,0) 点开始上下左右移动能否找到这个是9的格子

import java.util.LinkedList;
import java.util.Queue;

public class Maze {
  public int Solution(int[][] matrix) {
    if (matrix == null) return 0;
    int m = matrix.length;
    if (m == 0) return 0;
    int n = matrix[0].length;
    if (n == 0) return 0;
    boolean[][] visited = new boolean[m][n];
    return helper(0, 0, m, n, matrix, visited);
    }

    public boolean helper(int i, int j, int m, int n, int[][] matrix, boolean[][] visited) {
      if (i>=m || i <0 || j >=n || j<0) return false;
      if (visited[i][j]) return false;
      if (matrix[i][j] == 1) return false;
      if (matrix[i][j] == 9) return true;
      visited[i][j] = true;
      return helper(i-1, j, m, n, matrix, visited) ||
      helper() ||
      helper() ||
      helper();
    }
  }

}
/*
public class Maze {
    private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    public int Solution(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        if (matrix[0][0] == 9)
            return 1;
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0, 0});
        matrix[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] next = {cur[0] + dx[i], cur[1] + dy[i]};
                if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n) {
                    if (matrix[next[0]][next[1]] == 9)
                            return 1;
                    else if (matrix[next[0]][next[1]] == 0) {
                        queue.offer(next);
                        matrix[next[0]][next[1]] = 1;
                    }
                }
            }
        }
        return 0;
    }
}
*/
