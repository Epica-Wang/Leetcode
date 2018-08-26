Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:

Input: "eceba"
Output: 3
Explanation: t is "ece" which its length is 3.
Example 2:

Input: "ccaabbb"
Output: 5
Explanation: t is "aabbb" which its length is 5.

/*
与利口3相似，典型two pointer。在利口3基础上考虑发现第三个新char的时候left指针怎么移动
思路：right指针往右移动的过程中，肯定需要个hashmap来记录char的count
当right move到第三个新char时，left往右走，同时hashmap里的count--
left一直向右移到某个key，它的index + 它hashmap现有的count = right index的时候停止。
即当前left char repeat了count次数，然后就接上了right char。
//也就是说，left一直向右移 直到某个key的count减为0了

保持hashmap里key size<=2,当hashmap里key的count=0时remove that key，

考虑下面三个例子
1. eceba: right指针到b的时候，left移到第二个e (此时left index=2,count=1, right index=3)
2. ceeba: right到b时，left移到第一个e (left index=1, count=2, right=3)
3. eecba: right到b时，left移到c (left index=2,count=1,right=3)

edge case:

*/

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
      int length = s.length();
      if(length<=2) return length;
      int left=0;
      int right=1;
      int maxLen = 0;
      HashMap<Character,Integer> map = new HashMap<>();
      map.put(s.charAt(left),1);

      while(right<length){
        if(map.containsKey(s.charAt(right))){
          map.put(s.charAt(right), map.get(s.charAt(right))+1);
        }else if(map.size()==1){
          map.put(s.charAt(right),1);
        }else{ //出现第三个unique char
          maxLen= Math.max(maxLen, right-left);
          // 新char加入map
          map.put(s.charAt(right),1);
          //该移动left了
          while(map.size()==3){
            if(map.get(s.charAt(left))==1){
              map.remove(s.charAt(left)); //remove that key
            }else{
              map.put(s.charAt(left), map.get(s.charAt(left))-1); // count--1
            }
            left++;
          }

        }
        right++;
      }

      //break while时right=length， update maxlen
      maxLen = Math.max(maxLen,right-left);
      return maxLen;
    }
}
