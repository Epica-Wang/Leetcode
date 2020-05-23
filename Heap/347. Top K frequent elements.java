Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2].

Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
      if(nums.length<1) return new LinkedList<>();
      Map<Integer, Integer> hp = new HashMap<>();
      for(int number: nums){
        hp.put(number, hp.getOrDefault(number,0)+1);
      }

      // min heap
      Queue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer>>(){
        @Override
        public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2){
          if(entry1.getValue()!= entry2.getValue()) return entry1.getValue()-entry2.getValue();
          else return entry1.getKey()-entry2.getKey();
        }
      });

      for(Map.Entry<Integer, Integer> entry: hp.entrySet()){
        pq.add(entry);
        if(pq.size()>k){
          pq.poll();
        }
      }
      List<Integer> res = new LinkedList<>();
      while(!pq.isEmpty()){
        // min heap. so need to add to front
        res.add(0, pq.poll().getKey());
      }
      return res;
    }
/* maxheap写法：前面pq的comparator改一下，heap里加n个，poll k次*/
}

/**
max heap 写法2：
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
      HashMap<Integer, Integer> map = new HashMap<>();
      for(int i=0;i<nums.length;i++){
        map.put(nums[i],map.getOrDefault(nums[i],0)+1);
      }
      List<Integer> res =  new LinkedList<>();
      PriorityQueue<Map.Entry<Integer,Integer>> maxHeap = new PriorityQueue<>(nums.length, new Comparator<Map.Entry<Integer,Integer>>(){
        @Override
        public int compare(Map.Entry<Integer,Integer> e1, Map.Entry<Integer,Integer> e2){
          return e2.getValue()-e1.getValue();
        }
      });
      for(Map.Entry<Integer, Integer> entry:map.entrySet()){
        maxHeap.add(entry);
        if (maxHeap.size() > map.entrySet().size() - k) {
            res.add(maxHeap.poll().getKey());
        }
      }
      return res;
    }
}
