/*
hint1: How can we reuse a previously computer palindrome to compute a larger palindrome?
hint2: If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
hint3: Complexity based hint:
If we use brute-force and check whether for every start and end position
a substring is a palindrome we have O(n^2) start - end pairs and O(n) palindromic checks.
Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.
*/

/*
dp解法：dp(i, j) represents whether s(i ... j) can form a palindromic substring,
dp(i, j) is true when s(i) equals to s(j)
and s(i+1 ... j-1) is a palindromic substring.
When we found a palindrome, check if it's the longest one. Time complexity O(n^2).
难点在于要求j > i 且dp(i, j) depends on dp(i+1, j-1) 如何填dp，不同于普通按行or按列填写

This yields a straight forward DP solution,
which we first initialize the one and two letters palindromes,
and work our way up finding all three letters palindromes, and so on...
Complexity Analysis
Time complexity : O(n^2)

Space complexity : O(n^2)
*/
public String longestPalindrome(String s) {
  int n = s.length();
  String res = null;

  boolean[][] dp = new boolean[n][n];

  for (int i = n - 1; i >= 0; i--) {
    for (int j = i; j < n; j++) {
      dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);

      if (dp[i][j] && (res == null || j - i + 1 > res.length())) {
        res = s.substring(i, j + 1);
      }
    }
  }

  return res;
}

/*
old version: 以每个element作为palindrome的中心，尝试向左右两边extend
时间： n种可能的中心点O(n) * extend最多O(n) = O(n^2)
*/
class Solution {
    private int maxLen=0;
    private int head = 0;
    private int tail = 0;
    public String longestPalindrome(String s) {
      int len = s.length();
      if (len<2){
        return s;
      }
      for (int i=0;i<len-1;i++){
        extendPalindrome(s,i,i);    //odd length.    "aba"
        extendPalindrome(s,i,i+1);   // even length  "abba"
      }
      return s.substring(head,tail+1);
    }

    public void extendPalindrome(String s,int i, int j){
      while(i>=0&&j<=s.length()-1){
        if(s.charAt(i)==s.charAt(j)){
          i--;
          j++;
        }
        else
          break;
        }
        int tempLen = (j-1)-(i+1)+1;
        if(tempLen>=maxLen){
          maxLen = tempLen;
          head = i+1;
          tail = j-1;
        }
    }
}
