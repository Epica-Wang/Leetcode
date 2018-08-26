//very similar with 26 remove duplicates from sorted array

public class Solution {
    public int removeElement(int[] nums, int val) {
        if(nums==null) return 0;
        int newLength=0;
        for(int index=0;index<nums.length;index++){
        	if(nums[index]!=val)
        		nums[newLength++] = nums[index];
        }
        return newLength;
    }
}