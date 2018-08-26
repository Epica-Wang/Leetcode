
class Solution{
  public int reverse(int x){
    long result = 0;
    while(x!=0){
      result = result*10 + x%10;
      x = x/10;
    }
    return (result>Integer.MAX_VALUE || result<Integer.MIN_VALUE)? 0:(int)result;
  }
}


/*
class Solution {
    public int reverse(int x) {
      if(x==Integer.MIN_VALUE)
        return 0;
      else if(x>0)
        return reversePositive(x);
      else
        return -1*reversePositive(-x);
    }
    public int reversePositive(int x){
      int reminder = 0;
      int modFactor = 10;
      StringBuilder str = new StringBuilder();
      while(reminder!=x){
        reminder = x % modFactor;
        if(reminder==x){
          str.append(Integer.toString(reminder*10/modFactor));
          break;
        }
        else{
            str.append(Integer.toString(reminder*10/modFactor));
            x = x-reminder;
            modFactor *=10;
        }
      }
      String ansSTR = str.toString();
      int answer = Integer.parseInt(ansSTR);
      return answer;
    }
}
*/
