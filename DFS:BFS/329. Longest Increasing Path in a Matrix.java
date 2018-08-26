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
