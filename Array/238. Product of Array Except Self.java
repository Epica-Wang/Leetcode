Given an array nums of n integers where n > 1,
return an array output such that output[i]
is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity?
(The output array does not count as extra space for the purpose of space complexity analysis.)

/*
解决O(n)time: 如何利用之前计算好的结果？
For each index i in output array, it is product of all elements on its left * all elements on its right.
We can save the accumulated product result.
 [1,2,3,4]
0. intialize output array to be all 1
1. first traverse input array from left to right to construct output:
  -> each output[i], it is product of all elements on its left except itself.
  output[0] = 1
  for(i=1 to n-1)
    output[i] = output[i-1] * intput[i-1]. (output[i-1]= 0~i-2)

2. then traverse input array from right to left:
  -> now for each output[i], we should add product of all elements on its right to it
  -> initialize a variable accumulateRight = input[last].
  output[n-1] = output[n-1]
  for(i=n-2 to 0)
    output[i] *= accumulateRight
    accumulateRight *= input[i]  //update accumulateRight, then pass it to i-1
*/

class Solution {
    public int[] productExceptSelf(int[] nums) {
      int[] output = new int[nums.length];
      if(nums.length<1) return output;
      output[0] = 1;
      for(int i=1;i<nums.length;i++){
        output[i] = output[i-1] * nums[i-1];
      }

      int accumulateRight = nums[nums.length-1];
      for(int i=nums.length-2;i>=0;i--){
        output[i] *= accumulateRight;
        accumulateRight *= nums[i];
      }

      return output;
    }
}
