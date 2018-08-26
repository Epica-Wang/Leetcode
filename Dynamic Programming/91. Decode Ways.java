A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: '12'
Output: 2
Explanation: It could be decoded as 'AB' (1 2) or 'L' (12).
Example 2:

Input: '226'
Output: 3
Explanation: It could be decoded as 'BZ' (2 26), 'VF' (22 6), or 'BBF' (2 2 6).
/*
freewheel的onsite题

dp[i]代表前i个元素有多少种decode方法。
特殊 case : 11,101,110, 501..
如果遇到有歧义的情况，原理和 climbing stairs 类似，
当前位置的合理解个数要考虑到前面两个子问题的合理解个数，即 dp[i] 要看 dp[i - 1] 和 dp[i - 2];
1. 前一位不是 0 ，并且能和当前 digit 组成合理字母，就 dp[i] = dp[i - 1] + dp[i - 2];
2. 跟前一位组不了，就不产生新路线，dp[i] = dp[i - 1];

重点在于 "0" 的处理。
开头直接遇到 0，返回 0；
任何位置连续遇到两个 0 ，无解，返回 0；
前一位数不能和当前 0 组成字母，无解，返回 0；
前一位数能和当前的 0 组成字母，dp[i] = dp[i - 2];

*/
class Solution{
  public int numDecodings(String s) {
          if(s == null || s.length() == 0) return 0;
          if(s.charAt(0) == '0') return 0; // Can't start with 0

          int[] dp = new int[s.length() + 1];
          dp[0] = 1; //dummy
          dp[1] = 1;

          for(int i = 1; i < s.length(); i++){
              int cur = s.charAt(i) - '0';
              int prev = s.charAt(i - 1) - '0';
              int num = prev * 10 + cur;

              if(cur == 0){
                  if(prev == 0) return 0; // Error - 00
                  else if(num <= 26) dp[i + 1] = dp[i - 1]; // we can't discard current 0
                  else return 0; // 40, 50, etc.
              } else {
                  if(prev != 0 && num <= 26) dp[i + 1] = dp[i] + dp[i - 1]; // "101" = 1 way
                  else dp[i + 1] = dp[i];
              }
          }

          return dp[s.length()];
      }
}
