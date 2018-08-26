You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example:

Given n = 5

call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true

Then 4 is the first bad version.

/*
version array[true,....true, false, false,....false]
找出第一个false
类似利口34  找start边界
*/
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
      if(n==1) return n;
      int left=1;
      int right =n;
      while(left+1<right){
        int mid=left+(right-left)/2;
        if(isBadVersion(mid)){
          right=mid;;
        }else{
          left=mid+1;
        }
      }
      if(isBadVersion(left)){
        return left;
      }else{
        return right;
      }
    }
}
