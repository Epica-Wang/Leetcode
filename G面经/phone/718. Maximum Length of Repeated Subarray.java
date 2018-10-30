/*
回忆longest common subsequences
subseq不要求连续，而subarray要求连续

dp[i][j]: 使用A中前i个，并且ending with A[i]， B中前j个，并且ending with B[j]，能得到的longest common subarray长度
*/

class Solution {
  public int findLength (int[] A, int[] B) {
  		int lenA = A.length;
      int lenB = B.length;
      int[][] dp = new int[lenA + 1][lenB + 1];

      // initialize dp array
      for (int i = 0; i <= lenA; i++ ) {
      	dp[i][0] = 0;
      }
      for (int j=0; j <= lenB; j++) {
      	dp[0][j] = 0;
      }
      int maxLen = 0;
      for(int i = 1; i <= lenA; i++) {
      	for (int j = 1; j <= lenB; j++) {
      		if (A[i-1] == B[j-1])
      			dp[i][j] = 1 + dp[i-1][j-1];
      		else
      			dp[i][j] = 0;
      		maxLen = Math.max ( dp[i][j], maxLen);
      	}
      }
      return maxLen;
  	}
}
