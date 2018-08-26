class Solution {
    public int lengthOfLongestSubstring(String s) {
      if (s.length()<=1)
        return s.length();
      int maxLen = 0;
      int head = 0;
      int tail = 1;

      while(tail<=s.length()-1){
        //current longest str. Not include char at tail
        String tempStr = s.substring(head,tail);
        // char at tail already appears in tempStr
        if(tempStr.indexOf(s.charAt(tail))!=-1){
          int oriIndex = head+tempStr.indexOf(s.charAt(tail));  //get index of repeated char in original str
          head = oriIndex+1;
          int tempLen = (tail-1) - head +1;
          if(tempLen>=maxLen){
            maxLen = tempLen;
          }
        }else{ // char at tail doesnt appear in tempStr before
          tail++;
        }
      }

      int tempLen = (tail-1) - head +1;
      if(tempLen>=maxLen){
        maxLen = tempLen;
      }
      return maxLen;
    }
}
