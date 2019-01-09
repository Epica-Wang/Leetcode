/*
Maze 的upgrade版本，output到9的最短路径的长度. 1可以走，0不可以走。如果不存在到9的路径，or没有9， output -1
输入:
[[1,0,0],
[1,0,1],
[1,9,1]]

output:3. 1->1>1->9
*/
import java.util.*;

public class MazeDistance {
  public int minDistance(List<List<Integer>> matrix, int numOfRow, int numOfCol){
    HashMap<String, Integer> visited = new HashMap<>();
    int minDistance = helper(matrix, 0, 0, numOfRow, numOfCol, visited);
    return minDistance == Integer.MAX_VALUE? -1 : minDistance;
  }

  public int helper(List<List<Integer>> matrix, int i, int j, int numOfRow, int numOfCol, HashMap<String, Integer> visited) {
    if (i<0 || i >= numOfRow || j<0 || j >= numOfCol) return Integer.MAX_VALUE;
    StringBuilder sb = new StringBuilder();
    sb.append(String.valueOf(i));
    sb.append("+");
    sb.append(String.valueOf(j));
    String key = sb.toString();
    if (matrix.get(i).get(j) == 9) {
      visited.put(key, 0);
      return 0;
    }
    if (matrix.get(i).get(j) == 0) {
      visited.put(key, Integer.MAX_VALUE);
      return Integer.MAX_VALUE;
    }
    if (visited.containsKey(key)) return visited.get(key);

    int localDistance = Integer.MAX_VALUE;
    /*
    here need to first put current point(key) to map,
    to prevent visit it again in its own dfs
    set its value to Integer.MAX_VALUE temporarily
    */
    visited.put(key, localDistance);
    int left = helper(matrix, i, j-1, numOfRow, numOfCol, visited);
    int right = helper(matrix, i, j+1, numOfRow, numOfCol, visited);
    int up = helper(matrix, i-1, j, numOfRow, numOfCol, visited);
    int down = helper(matrix, i+1, j, numOfRow, numOfCol, visited);

    if (left != Integer.MAX_VALUE) {
      System.out.format("%s 's left is not max ", key);
      localDistance = Math.min(localDistance, left);
    }
    if (right != Integer.MAX_VALUE) {
      System.out.format("%s 's right is not max ", key);
      localDistance = Math.min(localDistance, right);
    }
    if (up != Integer.MAX_VALUE) {
      System.out.format("%s 's up is not max ", key);
      localDistance = Math.min(localDistance, up);
    }
    if (down != Integer.MAX_VALUE) {
      System.out.format("%s 's down is not max ", key);
      localDistance = Math.min(localDistance, down);
    }
    localDistance = localDistance == Integer.MAX_VALUE ? localDistance : localDistance + 1;
    /*
    now finish visit this point, know the true value of localDistance
    update its value in map
    */
    visited.put(key, localDistance);
    return localDistance;
  }

  public static void main(String[] args){
    MazeDistance maze = new MazeDistance();
    List<Integer> row1 =  Arrays.asList(1,0,0);
    List<Integer> row2 =  Arrays.asList(1,0,1);
    List<Integer> row3 =  Arrays.asList(1,9,1);
    List<List<Integer>> matrix = new ArrayList<>();
    matrix.add(row1);
    matrix.add(row2);
    matrix.add(row3);
    int res = maze.minDistance(matrix, 3, 3);
    System.out.println(res);
  }
}
