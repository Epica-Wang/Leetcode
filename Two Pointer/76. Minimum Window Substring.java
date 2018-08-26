/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

For example,
S = "ADOBECODEBANC"
T = "ABC"
Minimum window is "BANC".

Note:
If there is no such window in S that covers all characters in T, return the empty string "".

If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
*/

class Solution {
    public String minWindow(String s, String t) {
      HashMap<Character,Integer> map = new HashMap();
      for(char c : s.toCharArray())
          map.put(c,0);
      for(char c : t.toCharArray())
      {
          if(map.containsKey(c))
              map.put(c,map.get(c)+1);
          else
              return "";
      }
      int start =0, end=0, minStart=0,minLen = Integer.MAX_VALUE, counter = t.length();
      while(end<s.length()){
        char c1 = s.charAt(end);
        if(map.get(c1)>0)
          counter--;
        map.put(c1, map.get(c1)-1);

        end++;

        while(counter==0){
          if(minLen> end-start){
            minLen = end-start;
            minStart = start;
          }

          // 保证跳出这个while循环的时候， start被更新到在s里 下一个t中存在的字符的位置
          char c2 = s.charAt(start);
          map.put(c2, map.get(c2)+1);
          if(map.get(c2)>0)
            counter++;

          start++;
        }
      }
      return minLen==Integer.MAX_VALUE? "" :s.substring(minStart, minStart+minLen);
    }
}
