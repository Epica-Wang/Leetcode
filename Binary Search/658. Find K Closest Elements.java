Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104


class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
      int left = 0;
      int right = arr.length - 1;
      int small = -1;
      int large = -1;
      ArrayList<Integer> resList = new ArrayList<>();
      // find largest ele smaller than or equal to x
      while (left + 1 < right) {
        int mid = left + (right - left)/2;
        if (arr[mid] > x) {
          right = mid;
        }else if (arr[mid] <= x) {
          left = mid;
        }
      }
      small = arr[right] <= x ? right : (arr[left] <= x ? left : -1);

      // find smallest ele larger than x
      left = 0;
      right = arr.length - 1;
      while (left + 1 < right) {
        int mid = left + (right - left)/2;
        if (arr[mid] > x) {
          right = mid;
        }else if (arr[mid] <=x) {
          left = mid;
        }
      }
      large = arr[left] > x ? left : (arr[right] > x ? right : -1);

      if (small == -1 ) {
        for (int i = 0 ; i < k; i++) {
          resList.add(arr[i]);
        }
      }else if (large == -1) {
        for (int i = arr.length - 1 ; i >= arr.length - 1 - k; i--) {
          resList.add(arr[i]);
        }
      }else {
        int count = 0;
        left = small;
        right = large;
        while (count < k) {
          if (small>= 0 && large <= arr.length) {
            if (Math.abs(x - arr[small]) <= Math.abs(x - arr[large])) {
              resList.add(arr[small]);
              small--;
            }else {
              resList.add(arr[large]);
              large++;
            }
          }else if (small >= 0) {
            resList.add(arr[small]);
            small--;
          }else {
            resList.add(arr[large]);
            large++;
          }
          count++;
        }
      }
      return resList;
    }
}
