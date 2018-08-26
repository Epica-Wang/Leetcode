/*
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

/* only care about non-zero elements. Don't need to do swap with 0.
Traverse whole array to put non-zero to nums[0],nums[1],...nums[k]. then fill
rest of array with 0;
*/
class Solution {
    public void moveZeroes(int[] nums) {
      int nonZero = 0;
      for(int i=0;i<nums.length;i++){
        if(nums[i]!=0){
          nums[nonZero]=nums[i];
          nonZero++;
        }
      }
      // nonZero = index of last non-zero element
      if(nonZero<nums.length-1){
        for(int j=nonZero+1;j<nums.length;j++)
          nums[j]=0;
      }
    }
}
