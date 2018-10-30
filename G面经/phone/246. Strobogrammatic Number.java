A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

Example 1:

Input:  "69"
Output: true
Example 2:

Input:  "88"
Output: true
Example 3:

Input:  "962"
Output: false
/*
rotate 180 degrees:
0 -> 0
1 -> 1
6 -> 9
8 -> 8
9 -> 6
convert original string use this map, then compare if two string equals.
notice 1.need to convert from end to start
2. 第一次写犯的错误  map存成了<String, String>, 然后get(num.charAt(index))的时候map里找不到char，应该存成<Character, Character>
*/
class Solution {
    public boolean isStrobogrammatic(String num) {
      if (num.contains("2") ||
          num.contains("3") ||
          num.contains("4") ||
          num.contains("5") ||
          num.contains("7")) return false;

      HashMap<Character, Character> rotate = new HashMap<>();
      rotate.put('0', '0');
      rotate.put('1', '1');
      rotate.put('6', '9');
      rotate.put('8', '8');
      rotate.put('9', '6');

      StringBuilder rotateNum = new StringBuilder();
      for(int index=num.length()-1; index >=0; index--){
        rotateNum.append(rotate.get(num.charAt(index)));
      }
      return rotateNum.toString().equals(num);
    }
}
