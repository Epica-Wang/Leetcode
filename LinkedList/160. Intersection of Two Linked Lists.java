Write a program to find the node at which the intersection of two singly linked lists begins.


For example, the following two linked lists:

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
begin to intersect at node c1.


Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.
/*
solution2: 不用先遍历A,B记录length。此解法A，B各被遍历一次
A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
记录headA=a1； headB=b1；
ptA从headA开始走，走完a1~c3. ptA move to headB，走完b1~b3
ptB从headB开始走，走完b1~c3. ptB move to headA，走完a1~a2

接下来ptA,ptB各走一步 就都到c1了。ptA==ptB 相遇了。
ptA ptB在c1之前不会相遇。

若A，B没有intersection：
A:          a1 → a2

B:     b1 → b2 → b3
最终ptA==ptB==null
*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA==null||headB==null) return null;
        ListNode ptA = headA;
        ListNode ptB = headB;
        while(ptA!=ptB){
          ptA = ptA==null? headB : ptA.next;
          ptB = ptB==null? headA : ptB.next;
        }
        return ptA;
    }
}

/*
solution1: 先遍历A,B 记录lengthA lengthB
哪个length长就多走（lengthA-lengthB）或lengthB-lengthA步
从两个list剩余length相同的地方开始 element by element的比较
A,B各被遍历了两次
*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode ptrA = headA;
        ListNode ptrB = headB;
        while(ptrA != null){
            ptrA = ptrA.next;
            lenA ++;
        }
        while(ptrB != null){
            ptrB = ptrB.next;
            lenB ++;
        }

        ptrA = headA;
        ptrB = headB;

        if(lenA > lenB){
            for(int i = 0; i < lenA - lenB; i++){
                ptrA = ptrA.next;
            }
        } else {
            for(int i = 0; i < lenB - lenA; i++){
                ptrB = ptrB.next;
            }
        }

        while(ptrA != ptrB){
            ptrA = ptrA.next;
            ptrB = ptrB.next;
        }

        return ptrA;
    }
}
