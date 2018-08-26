Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Example 1:

Input: 1->2->3->3->4->4->5
Output: 1->2->5
Example 2:

Input: 1->1->1->2->3
Output: 2->3
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
      if(head==null) return head;
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode prev = dummy;
      ListNode cur = head;
      while(cur!=null && cur.next!=null){
        if(cur.val!=cur.next.val){
          prev = cur;
          cur = cur.next;
        }else{ //记得把check null写在前面 然后再写后面的条件
          //写成while cur.val==cur.next.val && cur.next!=null) 在一路删到结尾的时候会报null pointer exception 因为CUR.NEXT为null  要先check next
          while(cur.next!=null && cur.val==cur.next.val ){
            cur = cur.next;
          }
          prev.next = cur.next;
          cur = cur.next;
        }
      }
      return dummy.next;
    }
}
