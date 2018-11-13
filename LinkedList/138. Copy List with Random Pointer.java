A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */

/*
solution2: iterate 3遍，但不需要hashmap额外空间
我们需要一个哈希表的原因是当我们访问一个结点时可能它的随机指针指向的结点还没有访问过，结点还没有创建，
所以我们需要线性的额外空间。想避免使用额外空间，我们只能通过利用链表原来的数据结构来存储结点。
基本思路是这样的，对链表进行三次扫描，第一次扫描对每个结点进行复制，然后把复制出来的新节点接在原结点的next，也就是让链表变成一个重复链表，就是新旧更替；
第二次扫描中我们把旧结点的随机指针赋给新节点的随机指针，因为新结点都跟在旧结点的下一个，所以赋值比较简单，就是node.next.random = node.random.next，其中node.next就是新结点，因为第一次扫描我们就是把新结点接在旧结点后面。
现在我们把结点的随机指针都接好了，最后一次扫描我们把链表拆成两个，第一个还原原链表，而第二个就是我们要求的复制链表。因为现在链表是旧新更替，只要把每隔两个结点分别相连，对链表进行分割即可。
*/

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
      if(head==null) return null;
      RandomListNode node = head;
      //1st iteration: copy old list
      while(node!=null){
        RandomListNode newNode = new RandomListNode(node.label);
        newNode.next = node.next;
        node.next = newNode;
        node = node.next.next;
      }
      // 2nd iteration: copy random pointer. reset node to head
      node = head;
      while(node!=null){
        if(node.random!=null){
          node.next.random = node.random.next; //node.random.next 才是新的node
        }
        node = node.next.next;
      }
      //3rd iteration: recover old list，seperate new list的同时把new list里面node的next指针设置正确
      node = head;
      RandomListNode newHead = node.next;
      RandomListNode newNode = newHead;
      while(node!=null && node.next.next!=null){
        //注意这儿访问了node.next.next。node！=null的时候 node.next肯定也！=null。但如果node和node.next是最后两个元素
        //node.next.next就是null了。
        //而下面又访问了newNode.next.next等于是node.next.next.next。当node.next.next是null的时候这边非法了
        //所以while里得多加个条件node.next.next!=null
        node.next = node.next.next;
        newNode.next= newNode.next.next;
        node = node.next;
        newNode = newNode.next;
      }
      //处理最后两个元素
      node.next = null;
      newNode.next = null;
      return newHead;
    }
}
/*
solution1：hashMap存两个list里node的对应关系
总共需要iterate 2遍：
1st：用old list的next指针，建hashmap以及 new list node和其next pointer
2nditerate HashMap,复制random pointer
*/

//简洁的写法：
public RandomListNode copyRandomList(RandomListNode head) {
  if (head == null) return null;

  Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

  // loop 1. copy all the nodes
  RandomListNode node = head;
  while (node != null) {
    map.put(node, new RandomListNode(node.label));
    node = node.next;
  }

  // loop 2. assign next and random pointers
  node = head;
  while (node != null) {
    map.get(node).next = map.get(node.next);
    map.get(node).random = map.get(node.random);
    node = node.next;
  }

  return map.get(head);
}
//第一遍的啰嗦写法
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
      if(head==null) return null;
      RandomListNode newHead = new RandomListNode(head.label);
      HashMap<RandomListNode, RandomListNode> map = new HashMap<>();
      map.put(head, newHead);
      RandomListNode oldNode = head;
      RandomListNode newNode = newHead;

      while(oldNode.nexts!=null){
        newNode.next = new RandomListNode(oldNode.next.label);
        map.put(oldNode.next, newNode.next);
        oldNode = oldNode.next;
        newNode = newNode.next;
      }

      for(Map.Entry<RandomListNode,RandomListNode> entry: map.entrySet()){
        RandomListNode oldRandom = entry.getKey().random;
        if(oldRandom!=null){
          RandomListNode newRandom = map.get(oldRandom);
          entry.getValue().random = newRandom;
        }
      }
      return newHead;
    }
}
