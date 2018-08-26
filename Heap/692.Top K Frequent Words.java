Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

/*
1. hashmap store frequency of words
2. use heap to maintain top k. 
implement by PriorityQueue. need to define a new comparator
*/
class Solution {
    public List<String> topKFrequent(String[] words, int k){
      if(words.length<1) return new LinkedList<String>();
      HashMap<String, Integer> freq = new HashMap<>();
      for(String word: words){
        freq.put(word,freq.getOrDefault(word, 0)+1);
      }
      Comparator<Map.Entry<String, Integer>> comparator = new NewComparator();
      PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(k, comparator);
      for(Map.Entry<String, Integer> entry: freq.entrySet()){
        pq.add(entry);
        if(pq.size()>k){
          pq.remove();
        }
      }

      List<String> res = new LinkedList<>();
      //PriorityQueue 遍历时的大坑：用iterator()/for each loop 不能保证traverse by priority queue的order！！
      // the ordering assurance only applies to offer, take, poll, peek, and possibly some other methods.
      // for(Map.Entry<String,Integer> entry:pq){
      //   // larger one add to front!!!
      //   res.add(0,entry.getKey());
      // }
      while(!pq.isEmpty()){
        res.add(0,pq.poll().getKey());
      }
      return res;
    }
}

class NewComparator implements Comparator<Map.Entry<String,Integer>>{
  @Override
  public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2){
    if(e1.getValue()!=e2.getValue()){
      return e1.getValue() - e2.getValue();
    }
    else return -1* e1.getKey().compareTo(e2.getKey());  // use String's compareTo method.
    //this problem requires lower alphabetical comes first, i.e.larger
  }
}

//用lambda 写comparator
PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                 (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

/// 写法2： 用anonymous class 写priority queue 的comparator
class Solution {
    public List<String> topKFrequent(String[] words, int k){
      if(words.length<1) return new LinkedList<String>();
      HashMap<String, Integer> freq = new HashMap<>();
      for(String word: words){
        freq.put(word,freq.getOrDefault(word, 0)+1);
      }
      PriorityQueue<Map.Entry<String,Integer>> pq = new PriorityQueue<>(k,new Comparator<Map.Entry<String,Integer>>(){
        @Override
        public int compare(Map.Entry<String,Integer> e1, Map.Entry<String,Integer> e2){
          if(e1.getValue()!=e2.getValue()){
            return e1.getValue() - e2.getValue();
          }
          else return -1* e1.getKey().compareTo(e2.getKey());
      });
      for(Map.Entry<String, Integer> entry: freq.entrySet()){
        pq.add(entry);
        if(pq.size()>k){
          pq.remove();
        }
      }

      List<String> res = new LinkedList<>();
      while(!pq.isEmpty()){
        res.add(0,pq.poll().getKey());
      }
      return res;
    }
}
