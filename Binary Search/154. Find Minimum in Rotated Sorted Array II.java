Example 1:

Input: [1,3,5]
Output: 1
Example 2:

Input: [2,2,2,0,1]
Output: 0

/*进阶的153. array里可能有duplicates
加上有重复元素的条件之后，这题立刻就变成 Hard 难度了。原因在于出现了新情况，即我们不知道最小值到底在 mid 的左边还是右边，比如【3,[3],1,3】，中间的 3 和两端值都相等，无法正确地直接砍掉一半。
我们依然可以靠 A[mid] 与 A[right] 的大小关系来二分搜索；
A[mid] > A[right] 时，mid 在左半边，最小值一定在 mid 右侧；
A[mid] < A[right] 时，mid 在右半边，最小值一定在 mid 左侧；
A[mid] == A[right] 时，无法判断，把 right 向左挪一格:
  这种情况下：如果A[mid]和A[right]都在左边或者右边的sorted array里，A[right]肯定不是最小值，right--
            如果A[mid]在左边的sorted array, A[right]在右边：因为左边的sorted array整体>=右边的sorted array
            那么现在左边的一个element A[mid]居然等于右边的一个element A[right]。说明A[right]肯定是右边sorted array中的最大值，right--
*/
class Solution {
    public int findMin(int[] nums) {
      if(nums.length==1) return nums[0];
      int left=0;
      int right=nums.length-1;
      while(left+1<right){
        int mid=left+(right-left)/2;
        if(nums[mid]>nums[right]){
          left=mid+1;
        }else if(nums[mid]<nums[right]){
          right=mid;
        }else{ //nums[mid]==nums[right]
          right--;
        }
      }
      //return语句可涵盖：1.跳出while后left和right指向同一个元素 2.nums的length=2 根本没进while循环
      return Math.min(nums[left],nums[right]);
    }
}
