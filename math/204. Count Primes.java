Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

// version 3
// small trick  in inner loop j start from 2, sacrifice some time but will terminate inner loop before get integer overflow
class Solution {
    public int countPrimes(int n) {
      if(n<=2) return 0;
      int count = 0;
      boolean[] isPrime = new boolean[n];

      /*
      If a number i is not prime, than it must can be product of some i' and i''
      smaller than i. And then isPrime[i] will change to false in second for loop.
      So in first for loop,
      if we find some i s.t isPrime[i] still true, it means elements smaller than i
      can not generate i.
      Then i must be a prime.

      */
      for(int i=2; i < n; i++){
        if(!isPrime[i]){ // i is not a prime
          continue;
        }else{
          count++; // isPrime[i] = true. i is prime
        }
        for(int j = 2; i * j < n; j++){
          isPrime[i*j] = false;
        }
      }
      return count;
    }
}

//version 2 save time.
// but still fail. for n = 49979 test case,
// when i=49978, if inner loop j start from i, we got i*j=49978 * 49978 overflow max integer

class Solution {
    public int countPrimes(int n) {
      if(n<=2) return 0;
      int count = 0;
      boolean[] isPrime = new boolean[n];

      /*
      If a number i is not prime, than it must can be product of some i' and i''
      smaller than i. And then isPrime[i] will change to false in second for loop.
      So in first for loop,
      if we find some i s.t isPrime[i] still true, it means elements smaller than i
      can not generate i.
      Then i must be a prime.

      */
      for(int i=2; i < n; i++){
        if(!isPrime[i]){ // i is not a prime
          continue;
        }else{
          count++; // isPrime[i] = true. i is prime
        }
        for(int j = i; i * j < n; j++){
          isPrime[i*j] = false;
        }
      }
      return count;
    }
}

//this version got time limited error
class Solution {
    public int countPrimes(int n) {
      if(n<=2) return 0;

      int count = n-2;  //exclude 1, and n itself since it's asking less than n
      boolean[] isPrime = new boolean[n];  // denote 0 to n-1
      Arrays.fill(isPrime, true);
      //can generate all non-prime number without repetition
      /* wrong answer!! will do extra count-- for different i,j that has same product
      eg: both get count-- for 2*6 and 3*4
      for(int i = 2; i < n;i++){
        for(int j = i;i * j < n;j++){
          count--;
        }
      }
      */
      for(int i=2;i < n; i++){
        for(int j=i; i * j < n; j++){
          if(isPrime[i * j]){
            isPrime[i * j] = false;
            count--;
          }
        }
      }
      return count;
    }
}
