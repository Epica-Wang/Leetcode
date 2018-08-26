A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5
Explanation: Your function can return either index number 1 where the peak element is 2,
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.
/*
相邻数字绝不重复
数组两端有 -Inf 做 padding
这就意味着在这样的给定条件下，区间 [0, n - 1] 一定存在一个 peak. 我们需要做的，就是利用 binary search 逐渐缩小这个有效区间的大小。

对于nums[mid]:
->如果nums[mid-1]< nums[mid] > nums[mid+1], 则nums[mid]就是peak
->如果nums[mid-1], nums[mid], nums[mid+1]单调递增：nums[mid+1]到nums[right]一定有peak.
  若nums[mid+1]到nums[right]单调递增，则right是peak；若单调递减，则mid+1是peak；
  若中间出现了拐点（即先增后减） 则也一定有peak
->如果nums[mid-1], nums[mid], nums[mid+1]单调递减：nums[left]到nums[mid-1]一定有peak.
  若nums[left]到nums[mid-1]单调递增，则mid-1是peak；若单调递减，则left是peak；
  若中间出现拐点，一定有peak
*/
class Solution {
    public int findPeakElement(int[] nums) {
      if(nums.length==1) return 0;
      int left=0;
      int right=nums.length-1;
      while(left+1<right){
        int mid=left+(right-left)/2;
        //在while里，left和right中间至少有一个element，所以mid-1不会小于left，mid+1不会大于right
        if(nums[mid]>nums[mid-1]&&nums[mid]>nums[mid+1]){
          return mid;
        }
        if(nums[mid]>nums[mid-1]&&nums[mid+1]>nums[mid]){ //单调递增
          left=mid+1;
        }else{  //单调递减
          right=mid-1;
        }
      }

      //return语句可涵盖：1.跳出while后left和right指向同一个元素 2.nums的length=2 根本没进while循环
      return nums[left]>nums[right]? left:right;
    }
}
