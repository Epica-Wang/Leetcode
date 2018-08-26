Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 class Solution {
     public ListNode mergeKLists(ListNode[] lists) {
       //以防case: []
       if(lists.length<1) return null;
       PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, new Comparator<ListNode>(){
         @Override
         public int compare(ListNode node1, ListNode node2){
           return node1.val - node2.val;
         }
       });

       //intialize PriorityQueue
       for(int i=0;i<lists.length;i++){
         if(lists[i]!=null){  //以防case:[ [], [] ]
           pq.offer(lists[i]);
         }
       }
       ListNode dummy = new ListNode(0);
       ListNode former = dummy;
       // maintain PriorityQueue
       while(!pq.isEmpty()){
         ListNode current = pq.poll();
         former.next = current;
         former = current;

         if(current.next!=null){
           pq.offer(current.next);
         }else{  //current.next==null 说明该list访问到最后一个了  continue pq的size变成k-1了
           continue;
         }
       }
       former.next=null;
       return dummy.next;
     }
 }
