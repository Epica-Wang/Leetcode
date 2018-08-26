public class Test {
	public static int lengthOfLongestSubstring(String s) {
        if (s.length() <=1)
        	return s.length();
        int startPosition = 0;
        int endPosition = 1;

        String longest_sub = Character.toString(s.charAt(startPosition));   
        String temp_sub = Character.toString(s.charAt(startPosition));   // store temp sub array
        while (endPosition <= s.length()-1){
	        //check if char(endPosition) appears in temp_sub
	        if (temp_sub.indexOf(s.charAt(endPosition))>=0) {
	        	if(temp_sub.length()>= longest_sub.length())
	        		longest_sub = temp_sub;
	        	startPosition = endPosition;
	        	endPosition +=1;
	        	temp_sub = Character.toString(s.charAt(startPosition));

	        }
	        else {
	        	temp_sub +=Character.toString(s.charAt(endPosition));
	        	endPosition+=1;
	        }


    }	
    	if (temp_sub.length()>= longest_sub.length())
    		longest_sub = temp_sub;
    	System.out.println(longest_sub);
        return longest_sub.length();


    }

   public static void main(String args[]) {
      String s = "dvdf";
      
      int length = lengthOfLongestSubstring(s);
      System.out.println(length);
   }
}