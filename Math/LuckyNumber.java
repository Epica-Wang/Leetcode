/* https://www.hackerrank.com/challenges/lucky-numbers/problem/  */
/* A number is called lucky if the sum of its digits, as well as the sum of the squares of its digits is a prime number.
How many numbers between A and B(both inclusive) are lucky? */
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class LuckyNumber {
    public static String[] luckyNumber(String[] nums){
      String[] result = new String[nums.length-1];
      for(int i=1;i<nums.length;i++){
        String[] range = nums[i].split(" ");
        long digitLenMin = range[0].length();
        long digitLenMax = range[1].length();
        long digitSumMin = 1;
        long digitSumMinSq = 1;
        long digitSumMax = 9*digitLenMax;
        long digitSumMaxSq = 9*9*digitLenMax;
        long countInRange = 0;
        HashSet<Long> primeInRange = primeInRange(digitSumMin,digitSumMaxSq);
        for(long n=Long.parseLong(range[0]);n<=Long.parseLong(range[1]);n++){
          long[] digits = digitOperation(n);
          long digitSum = digits[0];
          long digitSqSum = digits[1];
          if(primeInRange.contains((long)digitSum)&&primeInRange.contains((long)digitSqSum)){
            countInRange+=1;
            //System.out.format("The number is %d, digit sum is %d, sum sq is %d %n", n, digitSum, digitSqSum);
          }
        }
        result[i-1] = String.valueOf(countInRange);
      }
      for(int i=0;i<result.length;i++){
        System.out.println(result[i]);
      }
      return result;
    }

    public static HashSet<Long> primeInRange(long min, long max){
      HashSet<Long> primeInRange = new HashSet<>();
      for(long i=min;i<=max;i++){
        boolean isPrime = true;
        if(i<2) continue;
        for(long j=2;j<i;j++){
          if(i%j==0){
            isPrime = false;
            break;
          }
        }
        if(isPrime==true){
          primeInRange.add(i);
        }
      }
      return primeInRange;
    }

    public static long[] digitOperation(long n){
      long digitSum = 0;
      long digitSqSum = 0;
      while(n!=0){
        long remaining = n%10;
        digitSum+=remaining;
        digitSqSum+=remaining*remaining;
        n = n/10;
      }
      return new long[]{digitSum, digitSqSum};
    }

    public static void main(String args[] ) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        String[] nums = new String[]{"1","1 1000000000000"};
        String[] result = luckyNumber(nums);
        for(int i=0;i<result.length;i++){
            System.out.println(result[i]);
        }
    }

}
