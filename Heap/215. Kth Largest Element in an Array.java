// Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
//
// For example,
// Given [3,2,1,5,6,4] and k = 2, return 5.
//
// Note:
// You may assume k is always valid, 1 ≤ k ≤ array's length.

// Solution1: use min heap with size k to store top k largest.
// then kth largest at top of min heap.
// kth largest means there is n-(k-1) elements smaller than it.

//O(N lg K) running time + O(K) memory
class Solution{
  public int findKthLargest(int[] nums, int k){
    Queue<Integer> pq = new PriorityQueue<>();
    for(int number:nums){
      pq.offer(number);
      if(pq.size()>k){
        pq.poll();  // poll n-(k-1) times
      }
    }
    return pq.poll();
  }
}

// Solution2: use max heap. store top n-(k-1) smallest. then kth largest is at the top of max heap.
// kth largest means there are k-1 elements larger than it.
class Solution {
    public int findKthLargest(int[] nums, int k) {
      Queue<Integer> pq = new PriorityQueue<>(nums.length, (a,b)->b-a);
      for(int numbers:nums){
        pq.offer(numbers);
        if(pq.size()>nums.length-(k-1)){
          pq.poll(); // poll k-1 times
        }
      }
      return pq.poll();
    }
}


// Solution 3: use max heap.Total n elements, kth largest means there are k-1 elements larger than it.
// construct a max heap. Then poll k times.
class Solution {
    public int findKthLargest(int[] nums, int k) {
      Queue<Integer> pq = new PriorityQueue<>(nums.length, (a,b)->b-a);
      for(int numbers:nums){
        pq.offer(numbers);
      }
      int count =0;
      //int result = 0;
      while(count<k-1){
        pq.poll();
        count++;
      }
      return pq.poll();
    }
}
