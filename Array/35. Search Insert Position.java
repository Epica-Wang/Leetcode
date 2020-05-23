public class Solution {
    public int searchInsert(int[] nums, int target) {
 		for(int index=0;index<nums.length;index++){
 			if(nums[index]==target)
 				return index;
 			else if(target<nums[index]) return index;
 			else if(index==nums.length-1) return nums.length;
 			else if(target>nums[index+1]) continue;
 			else return (index+1);
 		}
    }
}

//solution2: binary search N*log(N). See binary search folder
