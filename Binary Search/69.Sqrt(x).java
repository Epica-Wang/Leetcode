class Solution {
    public int mySqrt(int x) {
      if(x==0) return 0;
      if(x == 1) return 1;
      int left = 1, right =x;
      int mid = left + (right-left)/2;
      while(left<=right){
        if(mid == x/mid)
            return mid;
        if(mid<x/mid){
          left = mid;
          if(mid+1 > x/(mid+1))
            return mid;
        }
        else
            right = mid;
        mid = left + (right-left)/2;
      }
      return mid;
  }
}
