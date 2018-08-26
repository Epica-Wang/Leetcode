Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode prev = dummy;
      ListNode cur = head;
      while(cur!=null){
        if(cur.val==val){
          prev.next = cur.next;
          cur = cur.next;
        }else{
          prev = cur;
          cur = cur.next;
        }
      }
      return dummy.next;
    }
}

/*写法2 不需要prev 但得考虑连续删除的情况*/

class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(val-1);
        dummy.next = head;
        ListNode cur = dummy;
        while(cur.next!=null){
            if(cur.next.val==val){
                cur.next = cur.next.next;
            }else{  //直到cur.next不用删再改cur   防止需要连续删除
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
