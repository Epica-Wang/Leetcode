//very similar with 27 -remove element

public class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums==null) return 0;
        if(nums.length==1) return 1;
        int index=1;      	
        int newLength =1;
        while(index<nums.length){
        	if(nums[index]==nums[index-1])
        		index++;
        	else{  //at any time newLength must <= index
        		nums[newLength++]=nums[index];   //assign value then newLength++
        		index++;
        		//newLength++;
        	}
        }
        return newLength;
    }
}