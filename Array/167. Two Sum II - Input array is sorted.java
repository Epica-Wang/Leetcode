public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        //if(numbers==null || numbers.length==1 || numbers[numbers.length-1]+numbers[numbers.length-2]<target)
        	//return null;

        int index1=0;
        int index2=numbers.length-1;
        while(true){
        	if(numbers[index1]+numbers[index2]>target)
        		index2--;
        	else if (numbers[index1]+numbers[index2]<target)
        		index1++;
        	else:
        		break
        }
        int[] answer= {index1,index2};
        return answer;


    }
}