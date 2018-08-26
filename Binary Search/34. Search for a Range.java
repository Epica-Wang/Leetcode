Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]

class Solution {
    public int[] searchRange(int[] nums, int target) {
      if(nums.length<1) return new int[]{-1,-1};

      int left=0;
      int right = nums.length-1;
      int start=-1;
      int end = -1;
      //find staring
      while(left+1<right){
        int mid = left + (right-left)/2;
        if(nums[mid]==target) right=mid;
        if(nums[mid]<target) left=mid+1;
        if(nums[mid]>target) right=mid-1;
      }
      if(nums[left]==target){
        start = left;
      }else if(nums[right]==target){
        start = right;
      }

      //别忘了重置！
      left=0;
      right=nums.length-1;
      //find ending
      while(left+1<right){
        int mid = left+(right-left)/2;
        if(nums[mid]==target) left=mid;
        if(nums[mid]>target) right=mid-1;
        if(nums[mid]<target) left=mid+1;
      }
      if(nums[right]==target){
        end=right;
      }else if(nums[left]==target){
        end=left;
      }

      return new int[]{start,end};
    }
}
