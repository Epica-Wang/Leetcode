Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5);
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.

/*
相比min stack多了要popMax（）的操作。用doubly linked list。
popmax会显示出doubly的好处：在max element出栈后，需要更新在它之后栈里元素的max。通过prev指针可方便修改
*/
/*
建node的写法：
建一个node class储存：
 0. val 自己的value
 1. next node （实现正常stack的功能）
 2. prev node
 3. max value （记录该node及已经入栈的node中的最大值）

整个linkedlist维护dummyHead和dummyTail，dummyHead.next表示栈顶。dummyTail.prev表示栈底元素

push(x):
 1. 新建一个node：node.next =head.next；head.next.prev = node; head.next = node;
 2. node.max = Max(head.next.max,自己的value）

pop():
 1. 删除head.next就行 head.next.next成为新的head.next。 其他啥都不用干

popMax():
 1. 获取要删除的max value=head.max
 2. 从head开始遍历 删除val = max的结点
 4. 用prev指针往head扫 更新node的max = max(node.prev.max, 自己的value)

top(): return head.value
peekMax():return head.max
//////////////////////
注意点：
1. dummyHead和dummyTail的初始化。
  head.next = tail; tail.prev = head;
  push第一个x的时候，x的max= max（x，head.next.max)
  所以tail的max要初始化成Integer.MIN_VALUE;

*/
class MaxStack {
  private class Node{
    int val;
    int max;
    Node prev;
    Node next;
    public Node(int a, int b, Node prev, Node next){
        this.val = a;
        this.max = b;
        this.prev = prev;
        this.next = next;
    }
  }
  private Node head;
  private Node tail;
  /** initialize your data structure here. */
  public MaxStack(){
      head = new Node(Integer.MIN_VALUE,Integer.MIN_VALUE,null,null);
      tail = new Node(Integer.MIN_VALUE,Integer.MIN_VALUE,null,null);
      head.next = tail;
      tail.prev = head;
  }

  public void push(int x) {
    Node newNode = new Node(x, Math.max(x, head.next.max), head, head.next);
    Node oldTop = head.next;
    oldTop.prev = newNode;
    head.next = newNode;
  }

  public int pop() {
    Node topNode = head.next;
    head.next = topNode.next;
    topNode.next.prev = head;
    return topNode.val;
  }

  public int top() {
    return head.next.val;
  }

  public int peekMax() {
      return head.next.max;
  }

  public int popMax() {
    int maxVal = peekMax();
    Node node = head.next;
    while(node!=null && node.val!=maxVal){
      node = node.next;
    }
    //after while. node.val = maxVal是我们要找的那个
    Node prevNode = node.prev;
    Node nextNode = node.next;
    prevNode.next = nextNode;
    nextNode.prev = prevNode;
    while(prevNode!=head){
      prevNode.max = Math.max(prevNode.val,prevNode.next.max);
      prevNode = prevNode.prev;
    }
    return maxVal;
  }
}
