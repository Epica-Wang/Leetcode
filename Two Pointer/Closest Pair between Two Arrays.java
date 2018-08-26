Given two arrays of integers, compute the pair of values (one value in each array) with the smallest (non-negative) difference. Return the difference.

Examples :

Input : A[] = {l, 3, 15, 11, 2}
        B[] = {23, 127, 235, 19, 8}
Output : 3
That is, the pair (11, 8)

Input : A[] = {l0, 5, 40}
        B[] = {50, 90, 80}
Output : 10
That is, the pair (40, 50)

//先sort，再two pointer
public int findSmallestDifference(int A[], int B[]){
      // Sort both arrays
      // using sort function
      Arrays.sort(A);
      Arrays.sort(B);

      int a = 0, b = 0;

      // Initialize result as max value
      int minDiff = Integer.MAX_VALUE;

      // Scan Both Arrays upto
      // sizeof of the Arrays
      while (a < A.length && b < B.length)
      {
          if (Math.abs(A[a] - B[b]) < minDiff)
              minDiff = Math.abs(A[a] - B[b]);

          // Move Smaller Value
          if (A[a] < B[b])
              a++;
          else
              b++;
      }
      // return final sma result
      return result;
}
