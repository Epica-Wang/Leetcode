Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.

Example 1:

Input: 123
Output: "One Hundred Twenty Three"
Example 2:

Input: 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:

Input: 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
Example 4:

Input: 1234567891
Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"

/*
from right to left, each time we process 3 digits. write a function called threeDigits(num)
eg: 1 234 567 891
  891-> threeDigits(891)
  567-> threeDigits(567) thousand
  234-> threeDigits(234) million
  1 -> threeDigits(1) billion
处理的同时，用count记录处理过了几个3位，就知道下一个处理完的后面是跟thousand，or million or billion。。。
最大的是2^31-1 20多个billion。 用一个map1存一下
  count=0-> empty str (不够三位) ;
  count=1-> hundred；
  count =2 -> thousand ;
  count =3 -> million..
append all these result together.

同时还需要一个map2存数字和英文的对应 0~19: zero~ninteen
另一个map3存几十：2~9 ：twenty to ninty

function threeDigits(num): num可以是3位及以下的数。return string。
  1.if num ==0： 即为0，或00，或000  直接return “”
  2. if num/10 ==0: 只有1位 return map2里查找结果
  3. if num/100==0: 只有两位 如果第一位是1，在map2里查找是十几，否则map3里查第一位，map2里查第二位
  4. else ： 三位都不为0， 先搞第一位是几百，然后处理后面两位，方法同上面123. num= num-几百，再调用一下threeDigits(num)

注意点：
0. 若初始num=0  直接return zero，因为跟其他地方有0的处理情况不一样。
1. 啥时加空格：统一都在前面加，最后的result remove leading space
2. count>=1的时候，要判断刚处理完的三位digits是不是空的。 如果是空 则不在results里加hundred/thousand/million。。
  例子：1，000，000->直接是1 million
*/

class Solution {
    private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] THOUSANDS = {"", " Thousand", " Million", " Billion"};
    public String numberToWords(int num) {
      if(num==0) return "Zero";
      StringBuilder result = new StringBuilder();
      int count=0;

      while(num!=0){
        int tempNum = num%1000;
        String res = threeDigits(tempNum);
        if(res==""){
          //do nothing
          //test case :1,000,000: 1 million
        }else{
          result.insert(0, res + THOUSANDS[count]);
        }
        count++;
        num = num/1000;
      }

      return result.substring(0,1).equals(" ")?result.substring(1):result.toString();
    }

    public String threeDigits(int num){
      if(num==0){
        return "";
      }else if(num/10==0){  //只有一位
        return " "+LESS_THAN_20[num];
      }else if(num/100==0){ //只有两位
        if(num/10==1){
          return " "+LESS_THAN_20[num];
        }else{
          String temp1 = LESS_THAN_20[num%10];
          String temp2 = TENS[num/10];
          if(temp1=="") return " "+temp2; //注意这里要分开写  不然temp1为空时会多一个空格
          return " "+temp2+ " " +temp1;
        }
      }else{
        int hundred = num/100;
        String remain = threeDigits(num-hundred*100);
        if(remain=="") return " "+LESS_THAN_20[hundred]+" Hundred";   //注意这里
        return " "+LESS_THAN_20[hundred]+" Hundred"+ remain;
      }
    }
}
