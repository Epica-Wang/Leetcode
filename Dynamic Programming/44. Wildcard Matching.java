Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like ? or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "*"
Output: true
Explanation: '*' matches any sequence.

Example 3:
Input:
s = "cb"
p = "?a"
Output: false
Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
Example 4:

Input:
s = "adceb"
p = "*a*b"
Output: true
Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
Example 5:

Input:
s = "acdcb"
p = "a*c?b"
Output: false

class Solution {
    public boolean isMatch(String s, String p) {
      int sLen = s.length();
      int pLen = p.length();
      if(pLen == 0) return sLen == 0;

      boolean[][] dp = new boolean[1+sLen][1+pLen];
      dp[0][0] = true;
      int startIndex = 0;
      while(startIndex<pLen && p.charAt(startIndex) == '*'){
        dp[0][startIndex+1] = true;
        startIndex++;
      }
      // if(p.charAt(0) == '*'){
      //   for(int i=0; i<1 + sLen; i++){
      //     dp[i][1] = true;  // * match any sequence
      //   }
      // }

      for(int i = 1;i < 1 + sLen; i++){
        for(int j = 1; j < 1 + pLen; j++){
          if(p.charAt(j-1) == '?'){
            dp[i][j] = dp[i-1][j-1];
          }else if(p.charAt(j-1) == '*'){
            dp[i][j] = dp[i][j-1] ||  dp[i-1][j];
          }else{ // char at j is an ordianry char
            if(p.charAt(j-1) == s.charAt(i-1)){
              dp[i][j] = dp[i-1][j-1];
            }
          }
        }
      }
      return dp[sLen][pLen];
    }
}
