/*
hint1: How can we reuse a previously computer palindrome to compute a larger palindrome?
hint2: If “aba” is a palindrome, is “xabax” and palindrome? Similarly is “xabay” a palindrome?
hint3: Complexity based hint:
If we use brute-force and check whether for every start and end position
a substring is a palindrome we have O(n^2) start - end pairs and O(n) palindromic checks.
Can we reduce the time for palindromic checks to O(1) by reusing some previous computation.
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
