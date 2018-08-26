class Solution {
    public double myPow(double x, int n) {
      if(n==0) return 1;
      if(n>0){
        return n%2==0 ? myPow(x*x,n/2): x*myPow(x*x, n/2);
      }
      /*  x=1.00000  n=-2147483648  overflow. Avoid 1/something. 1/0
      else
        return 1/myPow(x,-n);
      */
      /* x = 1.00000 n=-2147483648 overflow!! max integer is 2147483647!!!
      overflow when convert -2147483648 to 2147483648
      else
      return myPow(1/x,-n);
      */
      else
        return 1/x *myPow(1/x,-(n+1));
    }
}

//my Solution
/*
public double myPow(double x, int n){
      if (n>=0){
          if (n ==0) return 1;
          if (n == 1) return x;
          double result = x;
          int current_pow = 1;
          while(2*current_pow <=n){
              result *=result;
              current_pow *=2;
          }
          int pow_remain = n-current_pow;
          if (pow_remain ==0)
              return result;
          else
              return result*myPow(x,pow_remain);
      }
      else
          return 1/myPow(x, -n);
  }
when x=0.00001,n=2147483647 time limited.
convert while(...)into recursive .
This solution has time complexity 2*log(N/2).
*/
