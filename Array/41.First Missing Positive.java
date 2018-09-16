Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.

/*
思路：通过元素交换，使得index x上的元素为x+1.
Index从小到大处理，处理完之后从头遍历index找到第一个index i不能满足其上元素为i+1的i,
则i+1为最小的missing positive.
index from 0 to len-1:
当处理index i时
1.若nums[i] = i+1: continue. 元素i+1已经在index i上
2.若nums[i] <=0 : continue. 对于该元素我们无能为力，不能将它换到它应该在的index上因为是负的。但可能后面的元素会被换到这个位置上
3.若nums[i] > length: continue. 仍然对于该元素无能为力，不能将它换到它应该在的index上，因为超过了最大的index。但可能后面的元素会被换到这个位置上
4.otherwise nums[i]!=i+1 && 0 < nums[i] <= length:
  设nums[i] = j, 此时查看：
    如果 j该在的index j-1上已经是正确的数（即j）了，continue，
    不进行交换（否则有可能死循环 考虑元素可能有duplicate的情况）；
    否则我们将nums[j-1] 和nums[i] 上的元素交换。
    这时nums[i]上是新元素了， 从新执行step 1 2 3 4 直到continue。
    （j被放到index j-1去了，later on处理index j-1会发现该index已经valid）

5. after step 1,2,3,4 能调整的元素都已经被调整完了。 从头遍历，发现第一个index x上不为x+1的 return x+1
*/
class Solution {
    public int firstMissingPositive(int[] nums) {
      int length = nums.length;

      for(int i=0; i < length; i++){
        while(nums[i]!=i+1){
          if(nums[i] <= 0) break;
          if(nums[i] > length) break;
          //otherwise nums[i]!=i+1 && 0 < nums[i] <= length
          int element = nums[i];
          if(nums[element-1] == element){
            break;
          }else{
            nums[i] = nums[element-1];
            nums[element-1] = element;
          }
        }
      }

      for(int j=0; j < length; j++){
        if(nums[j] != j+1) return j+1;
      }
      return length+1;
    }
}
