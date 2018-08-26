Given a string, sort it in decreasing order based on the frequency of characters.
Example 1:
Input:
"tree"
Output:
"eert"
Explanation:
'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.

Example 2:

Input:
"cccaaa"

Output:
"cccaaa"

Explanation:
Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
Note that "cacaca" is incorrect, as the same characters must be together.

Example 3:

Input:
"Aabb"

Output:
"bbAa"

Explanation:
"bbaA" is also a valid answer, but "Aabb" is incorrect.
Note that 'A' and 'a' are treated as two different characters.

/*Solution1: bucket sort   O(N)*/

public class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        List<Character> [] bucket = new List[s.length() + 1];
        for (char key : map.keySet()) {
            int frequency = map.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }
        StringBuilder sb = new StringBuilder();
        for (int pos = bucket.length - 1; pos >=0; pos--) {
            if (bucket[pos] != null) {
                for (char num : bucket[pos]) {
                    for (int i = 0; i < map.get(num); i++) {
                        sb.append(num);
                    }
                }
            }
        }
        return sb.toString();
    }
}

/////////////////////////////////////////////////////////////
/*Solution 2
/* hashmap存frequency，max heap按frequency存，override一个comparator 按freq和alphabet比较
时间：O(nlog(n))for delete&insert in heap */
class Solution {
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        if(s.length()<=1) return s;
        for(int i=0;i<s.length();i++){
          if(map.containsKey(s.charAt(i))){
            map.put(s.charAt(i), map.get(s.charAt(i))+1);
          }else{
            map.put(s.charAt(i), 1);
          }
        }

        //priority queue for max heap.
        PriorityQueue<Map.Entry<Character,Integer>> pq =  new PriorityQueue<>(52,new Comparator<Map.Entry<Character, Integer>>(){
          @Override
          public int compare(Map.Entry<Character,Integer> e1, Map.Entry<Character,Integer> e2){
            //注意这边return的东西
            return e1.getValue()==e2.getValue()? e1.getKey()-e2.getKey() :e2.getValue()-e1.getValue();
          }
        });

        for(Map.Entry<Character,Integer> entry:map.entrySet()){
          pq.add(entry);
        }
        StringBuilder res = new StringBuilder();
        while(pq.size()!=0){
          Map.Entry<Character,Integer> entry = pq.poll();
          int count = entry.getValue();
          while(count>0){
            res.append(Character.toString(entry.getKey()));
            count--;
          }
        }
        return res.toString();
    }
}
