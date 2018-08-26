class Solution {
    public int findDuplicate(int[] nums) {
      int left =1;
      int right = nums.length-1;

      while(left<right){
        int mid = (left+right)/2;
        int count = 0;
        for(int number::nums){
          if(number<=mid)
            count +=1;
        }
        if (count<=mid) //no duplicate in [left, mid]; duplicate in [mid+1,right]
          left = mid+1;
        else   //duplicate in [left,mid]
          right=mid;
      }
      return left;
    }
}
