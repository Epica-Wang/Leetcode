/*
Given a roman numeral, convert it to an integer.

Input is guaranteed to be within the range from 1 to 3999.
Symbol	I	 V	 X	  L	   C	  D	    M
Value	 1  5	  10	 50	  100	 500	1,000

IV, IX,
*/

class Solution{
  public int romanToInt(String s) {
    HashMap<Character, Integer> hm = new HashMap<>();
    hm.put('I',1);
    hm.put('V',5);
    hm.put('X',10);
    hm.put('L',50);
    hm.put('C',100);
    hm.put('D',500);
    hm.put('M',1000);
    
    int sum = 0;
    for (int i=0;i<s.length()-1;i++){
      if(hm.get(s.charAt(i))<hm.get(s.charAt(i+1))){
        sum-=hm.get(s.charAt(i));
      }
      else{
        sum+=hm.get(s.charAt(i));
      }
    }
    sum+=hm.get(s.charAt(s.length()-1));
    return sum;
    }
}
