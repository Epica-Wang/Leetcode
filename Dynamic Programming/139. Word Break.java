Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".

/*
dp[i]：从0到index i可否被segment
dp[i]=true的条件: j从index i往前遍历，直到substring[j,i] && dp[j-1].
return dp[s.length]
i从0到s.length-1
*/
// 01.23.2019重写练习
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
      boolean[] dp = new boolean[s.length() + 1];
      dp[0] = true;
      for(int i = 1;i <= s.length();i++){
        for(int j = i;j >= 1;j--){
          //chars from j to i (index from 1)
          if (wordDict.contains(s.subString(j-1, i)) && dp[j-1]) {
            dp[i] = true;
            break;
          }
        }
      }
      return dp[s.length()];
    }
}


class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
      boolean[] dp = new boolean[s.length()];

      for(int i=0;i<s.length();i++){
        for(int j=i;j>=0;j--){
          if(j==0){
            if(wordDict.contains(s.substring(0,i+1))){
              dp[i]=true;
              break;
            }
          }else if(wordDict.contains(s.substring(j,i+1)) && dp[j-1]){
            //因为这个if里要求j-1>=0  即j>=1.  所以j=0的情况得单独在上面的if里判断
            dp[i] = true;
            break;
          }
        }
      }
      return dp[s.length()-1];
    }
}
