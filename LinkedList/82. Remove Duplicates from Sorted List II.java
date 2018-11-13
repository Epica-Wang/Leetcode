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
/*
11.12重写思路清晰版
*/
/*
dummy->1->2->3->3->4->4->5
at beginning, prev set to dummy.
each time we need to compare two nodes: cur and cur.next
while (cur!= null && cur.next !=null)
-> 1. if cur.val != cur.next.val, then we know cur is safe. no need to delete cur.
    set prev = cur; cur = cur.next

-> 2. if cur == cur.next:
    moving forward to find the first node that != cur.val or we will reach the end
    prev.next = that first node
    cur = that first node
After while:
if cur!= null && cur.next == null, then cur is the last element, and it's unique. need to do nothing
if cur == null need to do nothing
*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        dummy.next = head;
        ListNode cur = head;

        while(cur != null && cur.next != null){
            if(cur.val != cur.next.val) {
                prev = cur;
                cur = cur.next;
            }else {
                cur = cur.next;
              while(cur.next != null && cur.val == cur.next.val) {
                  cur = cur.next;
              }
            // if cur.next == null, then cur is the last element
              if(cur.next == null) {
                  prev.next = null;
                  break;
              }else{
                  prev.next = cur.next;
                  cur = cur.next;
              }
            }
        }
        return dummy.next;
    }
}

//old version
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
