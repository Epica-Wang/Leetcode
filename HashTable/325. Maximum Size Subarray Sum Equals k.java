Given an array nums and a target value k, find the maximum length of a subarray that sums to k. IF there isn t one, return 0 instead.

Note:
The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.

Example 1:

Input: nums = [1, -1, 5, -2, 3], k = 3
Output: 4
Explanation: The subarray [1, -1, 5, -2] sums to 3 and is the longest.
Example 2:

Input: nums = [-2, -1, 2, 1], k = 1
Output: 2
Explanation: The subarray [-1, 2] sums to 1 and is the longest.
Follow Up:
Can you do it in O(n) time?

/* O(n) solution:
这array里有负数，所以two pointer不能解决，因为没有单调性 不会说指针往右扫就能sum变大.
做法：prefix sum array + two sum，利用前缀和数组实现快速区间和查询，同时 two sum 的方法快速定位 index.
hashmap的存从开始到index i的sum，key是sum，value是i
如果key(sum)有重复的，则保留index小的i   这样subarray长一些
*/

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
      if(nums.length<1) return 0;
      int maxLength = 0;
      HashMap<Integer, Integer> prefixhp =  new HashMap<>();
      int sum =0;
      for(int i=0;i<nums.length;i++){
        sum+= nums[i];
        if(prefixhp.containsKey(sum)){
          //do nothing
          //添加这组if else 防止掉坑如[1,0,-1] k=-1   maxlength=2
          // 在index 0，1的位置sum都=1； 所以index 1的时候不更新value
        }else{
          prefixhp.put(sum, i);
        }
        if(sum==k){
          maxLength = i+1;
        }else if(prefixhp.containsKey(sum-k)){
          int temp = i - prefixhp.get(sum-k);
          maxLength = Math.max(maxLength, temp);
        }
      }
      return maxLength;
    }
}
