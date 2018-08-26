Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:

Input: [3,4,5,1,2]
Output: 1
Example 2:

Input: [4,5,6,7,0,1,2]
Output: 0

/* 乞丐版的35题*/
class Solution {
    public int findMin(int[] nums) {
      if(nums.length==1) return nums[0];
      int left=0;
      int right=nums.length-1;
      while(left+1<right){
        int mid=left+(right-left)/2;
        if(nums[mid]>nums[right]){
          left=mid+1;
        }else{
          //只有nums[mid]<nums[right]. 因为没有duplicates  nums[mid]不可能=right
          right=mid;
        }
      }
      //return语句可涵盖：1.跳出while后left和right指向同一个元素 2.nums的length=2 根本没进while循环
      return Math.min(nums[left],nums[right]);
    }
}
