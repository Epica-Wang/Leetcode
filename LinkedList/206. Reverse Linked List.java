/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
 1->2->3->4->null
for each node, we need to store its original previous and original next node
head 1最后再处理，因为它的prev是null
进while循环current从2开始
*/
class Solution {
    public ListNode reverseList(ListNode head) {
      // check if input list is []
      if(head == null){
        return head;
      }

      ListNode prev = head;
      ListNode current = prev.next;
      //for each node, we need to store its original previous and original next node
      while (current!=null){
        ListNode nextTemp = current.next;  //use nextTemp to store original next of current node
        current.next = prev;   //change the current.next to previous node
        prev = current;     // current node is done. This node become previous node of its next
        current = nextTemp;  // move forward
      }
      head.next = null;   // don't forget to change the next of head.
      return prev;
    }
}
