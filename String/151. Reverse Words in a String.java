Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Update (2015-02-12):
For C programmers: Try to solve it in-place in O(1) space.
/*
tricky test case: need to remove all leading blank space after reversed Wrods
input str:"   " output should be:""
但input:" 1"   output:"1 "   keep blank space   所以不能用split(" ")去处理！！
*/
public class Solution {
    public String reverseWords(String s) {
      String[] reverseWords = s.split(" ");
      int len = reverseWords.length;
      if(len==0) return "";
      if(len==1) return reverseWords[0];
      //这个地方tricky  for这么写的话len=1的时候因为只交换一半  i<0  不会进入for循环  所以在前面得
      // check一下len==1的情况
      for(int i=0;i<0.5*len;i++){
        //switch arr[i] with arr[length-1-i]
        String temp = new String(reverseWords[i]);
        reverseWords[i] = reverseWords[len-1-i];
        reverseWords[len-1-i] = temp;
      }
      StringBuilder str = new StringBuilder();
      for(int i=0;i<len-1;i++){
        str.append(reverseWords[i]);
        str.append(" ");
      }
      str.append(reverseWords[len-1]); //在前面记得check len是不是>1！！

      return str.toString();
    }
}
