import java.util.*;

public class Solution {
    public int getPairsCount(int[] arr, int sum)
    {
        HashMap<Integer, Integer> hm = new HashMap<>();
 
        // Store counts of all elements in map hm
        for (int i=0; i<arr.length; i++){
             
            // initializing value to 0, if key not found
            if(!hm.containsKey(arr[i]))
                hm.put(arr[i],0);
                 
            hm.put(arr[i], hm.get(arr[i])+1);
        }
        int twice_count = 0;
 
        // iterate through each element and increment the
        // count (Notice that every pair is counted twice)
        for (int i=0; i<arr.length; i++)
        {
            if(hm.get(sum-arr[i]) != null)
                twice_count += hm.get(sum-arr[i]);
 
            // if (arr[i], arr[i]) pair satisfies the condition,
            // then we need to ensure that the count is
            // decreased by one such that the (arr[i], arr[i])
            // pair is not considered
            if (sum-arr[i] == arr[i])
                twice_count--;
        }
 
        // return the half of twice_count
        return twice_count/2;
    }

    public static void main(String[] args){
    	Solution solu = new Solution();
        int arr[] = new int[]{1, 5, 7, -1, 5} ;
        int result = solu.getPairsCount(arr,6);
        System.out.println(result);
    }
}

