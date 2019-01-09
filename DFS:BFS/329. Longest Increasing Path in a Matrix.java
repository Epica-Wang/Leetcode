Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums =
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums =
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
// 01/06/2019 重写练习
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
      HashMap<String, Integer> map = new HashMap<>();
      int longest = 0;
      for(int i=0;i<matrix.length;i++){
        for(int j=0;j<matrix[0].length;j++){  //对以每个element作为path开头 进行dfs尝试
          longest = Math.max(longest,helper(i,j,matrix,map));
        }
      }
      return longest;
    }

    // 返回 从(i, j)开始，longest increasing path的长度
    public int dfs(int i, int j, int[][] matrix, HashMap<String, Integer> map) {
      String keyString = Integer.toString(i)+" "+Integer.toString(j);
      if (map.containsKey(keyString)) return map.get(keyString);
      int localMax = 1; //current element

      if (i-1 >=0 && matrix[i-1][j] > matrix[i][j]) {  // only travel up if...
        localMax = Math.max(localMax, 1 + dfs(i-1, j, matrix, map));
      }
      // travel down
      if (i+1 < matrix.length && matrix[i+1][j] > matrix[i][j]) {
        localMax = Math.max(localMax, 1 + dfs(i+1, j, matrix, map));
      }
      //travel left
      if (j-1 >=0 && matrix[i][j-1] > matrix[i][j]) {
        localMax = Math.max(localMax, 1 + dfs(i, j-1, matrix, map));
      }
      //travel right
      if (j+1 < matrix[0].length && matrix[i][j+1] > matrix[i][j]) {
        localMax = Math.max(localMax, 1 + dfs(i, j+1, matrix, map));
      }

      map.put(keyString, localMax);
      return localMax;
    }
}

// version2, logic same as ablove
class Solution {
    public int longestIncreasingPath(int[][] matrix) {
      HashMap<String, Integer> map = new HashMap<>();
      int longest = 0;
      for(int i=0;i<matrix.length;i++){
        for(int j=0;j<matrix[0].length;j++){
          longest = Math.max(longest,helper(i,j,Integer.MIN_VALUE,matrix,map));
        }
      }
      return longest;
    }

    // 如果(i, j) 大于prev，则返回从（i, j)开始longest increasing的长度
    public int helper(int i, int j, int prev, int[][] matrix, HashMap<String,Integer> map){
      if(i<0||j<0||i>=matrix.length||j>=matrix[0].length||matrix[i][j]<=prev) return 0;

      String keyString = Integer.toString(i)+" "+Integer.toString(j);
      if(map.containsKey(keyString)) return map.get(keyString);

      int longestPath = 1 + getMax(helper(i+1,j,matrix[i][j],matrix,map),
                                  helper(i-1,j,matrix[i][j],matrix,map),
                                  helper(i,j+1,matrix[i][j],matrix,map),
                                  helper(i,j-1,matrix[i][j],matrix,map));
      map.put(keyString, longestPath);
      return longestPath;
    }

    public int getMax(int n1, int n2, int n3, int n4){
      int max1 = Math.max(n1, n2);
      int max2 = Math.max(n3,n4);
      return Math.max(max1, max2);
    }
}
