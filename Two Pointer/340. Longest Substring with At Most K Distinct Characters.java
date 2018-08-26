/*利口159基础上把2改成k就行了
当right move到第k+1个新char时，left往右走，同时hashmap里的count--
left一直向右移到某个key，它的index + 它hashmap现有的count = right index的时候停止。
即当前left char repeat了count次数，然后就接上了right char。
//也就是说，left一直向右移 直到某个key的count减为0了  则当前的unique char变成k个
*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
      int length = s.length();
      if(k<=0) return 0;
      if(length<=k) return length;
      int left=0;
      int right=1;
      int maxLen = 0;
      HashMap<Character,Integer> map = new HashMap<>();
      map.put(s.charAt(left),1);

      while(right<length){
        if(map.containsKey(s.charAt(right))){
          map.put(s.charAt(right), map.get(s.charAt(right))+1);
        }else if(map.size()<k){
          map.put(s.charAt(right),1);
        }else{ //出现第三个unique char
          maxLen= Math.max(maxLen, right-left);
         // 新char加入map
          map.put(s.charAt(right),1);
          //该移动left了
          while(map.size()==k+1){
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
