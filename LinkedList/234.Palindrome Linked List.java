/*This can be solved by reversing the 2nd half and compare the two halves. Let’s start with an example [1, 1, 2, 1].

In the beginning, set two pointers fast and slow starting at the head.

1 -> 1 -> 2 -> 1 -> null
sf
(1) Move: fast pointer goes to the end, and slow goes to the middle.

1 -> 1 -> 2 -> 1 -> null
          s          f
(2) Reverse: the right half is reversed, and slow pointer becomes the 2nd head.

1 -> 1    null <- 2 <- 1
h                      s
(3) Compare: run the two pointers head and slow together and compare.

1 -> 1    null <- 2 <- 1
     h            s
*/
public boolean isPalindrome(ListNode head) {
    ListNode fast = head, slow = head;
    while (fast != null && fast.next != null) {
        fast = fast.next.next;
        slow = slow.next;
    }
    if (fast != null) { // odd nodes: let right half smaller
        slow = slow.next;
    }
    slow = reverse(slow);
    fast = head;

    while (slow != null) {
        if (fast.val != slow.val) {
            return false;
        }
        fast = fast.next;
        slow = slow.next;
    }
    return true;
}

public ListNode reverse(ListNode head) {
    ListNode prev = null;
    while (head != null) {
        ListNode next = head.next;
        head.next = prev;
        prev = head;
        head = next;
    }
    return prev;
}



/*6.28重写练习：reverse second half

快慢指针 当快走到尾时  慢指针走到中间
若list长度为奇：fast指向最后一个element，slow指向最中间的element  slow.next是要reverse的second half的头
若list长度为偶：fast指向null，slow就是要reverse的second half的头
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
      ListNode fast = head;
      ListNode slow = head;
      while(fast!=null && fast.next!=null){
        fast = fast.next.next;
        slow = slow.next;
      }
      ListNode secondHalfHead=null;
      if(fast==null){ //偶
         secondHalfHead = reverse(slow);
      }else{
        secondHalfHead = reverse(slow.next);
      }
      while(secondHalfHead!=null){
        if(head.val!=secondHalfHead.val){
          return false;
        }
        head = head.next;
        secondHalfHead = secondHalfHead.next;
      }
      return true;
    }

    public ListNode reverse(ListNode head){
      if(head==null) return head;
      ListNode prev =head;
      ListNode cur = head.next;
      while(cur!=null){
        ListNode temp = cur.next;
        cur.next =  prev;
        prev = cur;
        cur = temp;
      }
      head.next = null;
      return prev;
    }
}
