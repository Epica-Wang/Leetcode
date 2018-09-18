/*
dp[i][j]代表A前i个字符和B前j个字符的最长公共子序列的长度。
由算法导论可知：
if A.charAt(i) == B.charAt(j):
-> then A.charAt[i](i.e. B.charAt[j]) must in one of their lcs.
-> rest of their lcs are in lcs of A[1~i-1] & B[1~j-1]. i.e. dp[i-1][j-1]
-> so dp[i][j] = dp[i-1][j-1] + 1

else if A.charAt(i) != B.charAt(j):
-> then lcs of A[1~i] & B[1~j] must be the lcs of A[1~i-1] & B[1~j] or A[1~i] & B[1~j-1].
-> i.e. dp[i][j] = max(dp[i-1][j], dp[i][j-1])

*/

public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
      int lenA = A.length();
      int lenB = B.length();
      int[][] dp = new int[lenA+1][lenB+1];

      int lcs = 0;

      for(int i = 1; i < lenA + 1; i++){
        for(int j = 1; j < lenB + 1; j++){
          if(A.charAt(i-1) == B.charAt(j-1)){
            // dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i][j-1]);
            dp[i][j] = dp[i-1][j-1] + 1; // dp[i-1][j-1] + 1 must >= dp[i][j-1s]
          }else{
            dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
          }
          lcs = Math.max(lcs, dp[i][j]);
        }
      }

      return lcs;
    }
}
