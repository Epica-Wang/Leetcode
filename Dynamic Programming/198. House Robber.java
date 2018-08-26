/* 要求 O(n) time and O(1) memory.
*/
/* 题解：https://zhengyang2015.gitbooks.io/lintcode/house_robber_392.html
val[i]表示抢第i幢房子时能取得的最大值。有两种情况，即抢第i幢房子，则其最大值为val[i-2]+A[i-1]，
或者不抢第i幢房子，则其最大值为val[i-1]，比较两者取大的即可。
状态函数：val[i]=max(val[i-1], val[i-2]+A[i-1])
但是题目要求用O(1)的space，所以需要用滚动数组优化，因为对于每个i，只和i－1以及i－2相关，因此用长度为2的数组即可，满足题意。
*/

class Solution {
    public int rob(int[] nums) {
      int[] val = new int[2];
      if(nums.length==1) return nums[0];
      if(nums.length==2) return Math.max(nums[0],nums[1]);
      val[0] = nums[0];
      val[1] = Math.max(nums[0], nums[1]);

      for(int i=2;i<nums.length;i++){
        val[i%2] = Math.max(nums[i] + val[i%2], val[(i-1)%2]);
      }
      return val[(nums.length-1) %2];
    }
}


/*
maxProfit[n]表示长度前n的array里最大利润，它的值只和前两个 subproblem 相关，即 maxProfit(n - 1) 和 maxProfit(n - 2).
显然maxProfit[n-1]>=maxProfit[n-2]
则讨论两种情况：
1.若maxProfit[n-1]>maxProfit[n-2]:说明nums[n-1]包含在maxProfit[n-1]中。
  则maxProfit[n] = max(maxProfit[n-1], maxProfit[n-2]+nums[n])

2.若maxProfit[n-1]==maxProfit[n-2]:说明nums[n-1]不包含在maxProfit[n-1]中。
  则maxProfit[n] = maxProfit[n-2]+nums[n]
*/
class Solution {
    public int rob(int[] nums) {
      int[] maxPro = new int[nums.length];
      if(nums.length==0) return 0;
      if(nums.length==1) return nums[0];
      maxPro[0] = nums[0];
      maxPro[1] = Math.max(nums[1], maxPro[0]);
      for(int i=2;i<nums.length;i++){
        if(maxPro[i-1]==maxPro[i-2]){
          maxPro[i] = nums[i] + maxPro[i-2];
        }else{
          maxPro[i] = Math.max(maxPro[i-1], maxPro[i-2]+nums[i]);
        }
      }
      return maxPro[nums.length-1];
    }
}
