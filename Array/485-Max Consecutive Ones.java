//my version

public class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max =0;
        int now =0;
        for(int index=0;index<nums.length;index++){
            if(nums[index]==1)
                now+=1;
            if(nums[index]==0){
                if(now>max)
                    max = now;
                now = 0;
            }
        }
        if(now>=max)
            return now;
        else
            return max;
    }
}