

//brute force: time: 2^n
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
      return helper(0, S, 0, nums);
    }

    public int helper(int currentSum, int S, int index, int[] nums){
      if(index==nums.length){
        if(currentSum==S){
          return 1;
        }
        return 0;
      }
      return helper(currentSum+nums[index], S, index+1, nums) + helper(currentSum-nums[index],S,index+1,nums);
    }
}
/* 一个有关java reference大错特错的写法
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
      int ways = 0;
      helper(ways, 0, S, 0, nums);
      return ways;
    }

    public void helper(int ways, int currentSum, int S, int index, int[] nums){
      if(index==nums.length){
        if(currentSum==S){
          ways++;
          //java pass primitive by value. helper里面change ways但不影响原来ways的值  所以return ways的时候一直是0
          //除非把ways设成class的一个attribute：private int ways
          //修改的时候用this.ways改才对
        }
        return;
      }
      currentSum +=nums[index];
      helper(ways,currentSum,S,index+1,nums);
      currentSum-=2*nums[index]
      helper(ways,currentSum,S,index+1,nums);
    }
}
*/
