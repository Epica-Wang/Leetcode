/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

//recursive 写法
/**
1->2->3->4->5->NULL
  1->2->3->4 <- 5
    1->2->3 <- 4 <- 5
      1->2 <- 3 <- 4 <- 5
        1 <- 2 <- 3 <- 4 <- 5
*/
class Solution {
  public ListNode reverseList(ListNode head) {
      if (head == null || head.next == null) return head;
      // recursive call的时候每层return的newHead都是original list中的最后一个node，一直不变
      ListNode newHead = reverseList(head.next);
      // head.next 为已经reverse好的list的最后一个node
      // 此时change current node(head)和已经reverse好的list的最后一个node的关系
      head.next.next = head;
      // head.next别忘了设成null, 如果是还有元素 之后recursive的stack里会改next的
      head.next = null;
      return newHead;
  }
}

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

// 11.12重写练习 dummy head
class Solution {
    public ListNode reverseList(ListNode head) {
      if(head==null) return null;
      ListNode dummy = new ListNode(0);
      ListNode prev =  dummy;
      ListNode cur = head;
      while(cur!=null){
        ListNode nextTemp = cur.next;
        cur.next = prev;
        prev = cur;
        cur = nextTemp;
      }
      head.next = null;
      //dummy = null; 这么写了只是让dummy指向了null, 但head.next 还是指向之前那个new ListNode(0)

      return prev;
    }
}
