import java.util.*;
/*
Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute difference is k.

Example 1:
Input: [3, 1, 4, 1, 5], k = 2
Output: 2
Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
Although we have two 1s in the input, we should only return the number of unique pairs.
Example 2:
Input:[1, 2, 3, 4, 5], k = 1
Output: 4
Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
Example 3:
Input: [1, 3, 1, 5, 4], k = 0
Output: 1
Explanation: There is one 0-diff pair in the array, (1, 1).

*/

/*
坑：[1,3,1] k=0;  [4,3,2,2,2] k =2;   需要考虑有多个重复元素的情况  不能简单的去重完了再排序
*/


public class Solution {
	public int findPairs(int[] nums, int k) {
    	if (nums == null || nums.length == 0 || k < 0)   return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                } 
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }
        
        return count;
 
	}
    /* Wrong edition 1
    public int findPairs(int[] nums, int k) {
    	Arrays.sort(nums);
        int[] unique = Arrays.stream(nums).distinct().toArray();
        List<Integer> unique_list = new ArrayList<Integer>();
        for (int index = 0; index<unique.length;index++)
        	unique_list.add(unique[index]);
        //List<Integer> unique_list =  Arrays.asList(unique);
        int count = 0;
        for(Integer integer:unique_list){
        	if (unique_list.contains(integer-k))
        		count+=1;
        }
        return count;
    }
	*/
    public static void main(String[] args){
    	Solution solu = new Solution();
    	int[] nums = new int[]{3,1,4,1,5};
    	int count = solu.findPairs(nums,2);
    	System.out.println(count);
    	System.exit(0);
    }
}

