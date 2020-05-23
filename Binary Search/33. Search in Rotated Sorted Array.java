class Solution {
    public int search(int[] nums, int target) {
        if(nums.length ==0) return -1;
        /*find pivot,i.e to find index of min value. Use binary search
        two subarray [0,index of min-1] and [index of min, n-1] are sorted.
        Every ele in second sub is less than first sub.
        */
        int left = 0;
        int right = nums.length - 1;
        while(left<right){
          int mid = left + (right-left)/2;
          if(nums[mid]>nums[right])
            left = mid+1;
          else
            right = mid;
        }
        // left=mid=index of min
        // now binary seach to find target
        int index_min = left;
        //in which subarray should we search target
        if(index_min == 0||target<nums[nums.length-1]){
          left = index_min;
          right = nums.length-1;
        }
        else{
          left = 0;
          right = index_min-1;
        }
        // binary search now
        while(left<right){
          int mid = left + (right-left)/2;
          if(nums[mid]==target)
            return mid;
          else if(nums[mid]<target)
            left = mid+1;
          else
            right = mid;
        }
        //remeber to check at last: when left = right
        if (nums[left]==target)
          return left;
        else
          return -1;
    }
}
