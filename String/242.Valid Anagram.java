Given two strings s and t , write a function to determine if t is an anagram of s.

Example 1:

Input: s = "anagram", t = "nagaram"
Output: true
Example 2:

Input: s = "rat", t = "car"
Output: false
Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?

//如果是unicode的话  就用hashmap存吧
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;          //Return false if both Strings are of different lengths.
        int freq[] = new int[26];                           //Frequency array to keep track of character count in strings.
        for(int i=0; i < s.length(); i++){
            freq[s.charAt(i)-'a']++;                        //Add to frequence array for String s.
        }
        for(int i=0; i < t.length(); i++){
            freq[t.charAt(i)-'a']--;                        //Remove from frequency array for String t.
            if(freq[t.charAt(i)-'a']<0) return false;       //Optimization to return false when string t has excess characters.
        }
        for(int i=0; i<26; i++){
            if(freq[i] != 0) return false;                  //Verify that character counts of both String s and t are equal.
        }
        return true;
    }
}
