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
/*
这次每个元素除了 size 之外也具有 value，就变成了更典型的 01 背包问题。
问题的结构依然具有 01 背包的性质，选一条 path 使得最终总价值最大，path 总数为 2^n. 同时对于同一个总的空间占用 totalSize 或者 totalValue，我们并不在乎它是怎么构造出来的，只要不重复选元素就行。
因此我们 dp 结构一致，而采用 int[][] 来记录
dp[i][j] 包里有 j 的空间，可以取前 i 个元素时，所能获得的最大收益。
*/
public class Solution {
  /**  https://mnmunknown.gitbooks.io/algorithm-notes/content/82ff0c_bei_bao_wen_ti.html
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
     public int backPackII(int m, int[] A, int V[]) {
         // write your code here
         // dp[i][j] within first i elements with space j, maximum profit gain
         int[][] dp = new int[A.length + 1][m + 1];

         for(int i = 1; i <= A.length; i++){
             for(int j = 1; j <= m; j++){
                 if(j - A[i - 1] >= 0){
                     dp[i][j] = Math.max(dp[i - 1][j] , dp[i - 1][j - A[i - 1]] + V[i - 1]);
                 } else {
                     dp[i][j] = dp[i - 1][j];
                 }
             }
         }

         return dp[A.length][m];
     }
}
