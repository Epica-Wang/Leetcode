/*
题目： http://www.lintcode.com/en/problem/backpack-ii/
题解： https://zhengyang2015.gitbooks.io/lintcode/backpack_ii_125.html

Given n items with size Ai and value Vi, and a backpack with size m.
What's the maximum value can you put into the backpack?
Example
Given 4 items with size [2, 3, 5, 7] and value [1, 5, 2, 4],
and a backpack with size 10. The maximum value is 9.

O(n x m) memory is acceptable, can you do it in O(m) memory?
*/
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
      if(m == 0 || A == null || A.length == 0){
          return 0;
      }
      int n = A.length;
      int[][] value = new int[m + 1][n + 1];
      for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(i -A[j - 1] >= 0){
                    value[i][j] = Math.max(value[i][j - 1], value[i - A[j - 1]][j - 1] + V[j - 1]);
                }else{
                    value[i][j] = value[i][j - 1];
                }
            }
        }

      return value[m][n];
  }
}
