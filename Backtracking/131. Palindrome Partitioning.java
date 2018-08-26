/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

[
  ["aa","b"],
  ["a","a","b"]
]
*/
class Solution {
    public List<List<String>> partition(String s) {
      List<List<String>> result = new ArrayList<>();
      helper(result, new ArrayList<>(), s, 0);
      return result;
    }
    public void helper(List<List<String>> result, ArrayList<String> tempList, String s, int start){
      if(start==s.length()){
        result.add(new ArrayList<String>(tempList));
      }
      for(int i=start;i<s.length();i++){
        if(isPalindrome(s.substring(start,i+1))){
          tempList.add(s.substring(start,i+1));
          helper(result, tempList, s, i+1);
          tempList.remove(tempList.size()-1);
        }
      }
    }

    public boolean isPalindrome(String s){
      int left = 0;
      int right = s.length()-1;
      while(left<right){
        if(s.charAt(left)!=s.charAt(right)) return false;
        left++;
        right--;
      }
      return true;
    }
}
