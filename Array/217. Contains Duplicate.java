public class Solution {
	//use HashSet
	public  boolean containsDuplicate(int[] nums) {
		 Set<Integer> set = new HashSet<Integer>();
		 for(int i : nums)
			 if(!set.add(i))// if there is same
				 return true; 
		 return false;
	 }

	// use HashMap  slow than set
    public boolean containsDuplicate(int[] nums) {
       Map<Integer,Integer> map= new HashMap<>();
       for(int i:nums){
		map.put(i,map.getOrDefault(i,0)+1);
       if(map.get(i)>=2)
       		return true;
       }
       /*
       for(Map.Entry<Integer,Integer> entry:map.entrySet()){
       		if(entry.getValue()>=2)
       			return true;
       }*/
       return false; 
    }
}