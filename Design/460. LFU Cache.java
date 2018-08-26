Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present.
When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item.
For the purpose of this problem, when there is a tie (i.e.,
two or more keys that have the same frequency),
the least recently used key would be evicted.

/*
my solution:
对每个frequency建一个doubly linked list，新加入的node加到tail。
每个(key,value)pair用Node存，
两个hashmap：
  1. HashMap<Integer, Node> pairs: 保存element的key，Node是在对应frequency list里的位置
  2. HashMap<Integer, DDLList> freqList: 保存frequency和该frequency list的头

还需要一个min_freq变量：记录当前最小的frequency是什么，方便找到对应的freqList删元素。

get(key):
->若不存在， return -1
->若存在， [frequency+1，查看是否要修改min_freq]

put(key, value):
->若不存在，先检查若size==capacity,[将min_freq的头元素remove掉, 并在pairs里remove对应元素]
  再在pairs map里增加元素，加入freqList=1的list里，新min_freq设成1
->若存在，[frequency+1,查看是否要修改min_freq]， 更新pair

重复操作frequency+1，值得写个function。查看是否要修改min_freq的操作也只会被frequency+1触发，写在一起好了
addFreq(int key):
只对已经存在的key进行addFreq操作
->从旧freqList里删除，加入新freqList。若旧freqList为空了，并且旧的freq为min_freq，更新min_freq为新freq_list的freq，从freqList里删除旧freqList
->修改pairs里对应元素的freq++

remove():
在put操作insert一个新node时，size已经是capacity了
->从min_freqList里删除最开头的。
  如果min_freqList除了head tail之外没了，从freqList map里删除min_freq
->从pairs map里删除该节点


*/
class LFUCache {
    //// node
    private class Node{
      int key;
      int value;
      int freq;
      Node next;
      Node prev;
      public Node(int key, int value){
        this.key = key;
        this.value = value;
        this.freq = 1;
        next = prev = null;
      }
    }
    /// doubly linked list
    private class DDLList{
      Node head;
      Node tail;
      public DDLList(){
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
      }
    }

    private int capacity;
    private int min_freq;
    private HashMap<Integer, Node> pairs; // store key and value pairs
    private HashMap<Integer,DDLList> freqList; //each frequency has a ddlinked list to store node
    public LFUCache(int capacity) {
      this.capacity = capacity;
      pairs = new HashMap<>();
      freqList =  new HashMap<>();
    }

    public int get(int key) {
      if(pairs.containsKey(key)){
        addFreq(key);
        return pairs.get(key).value;
      }
      return -1;
    }

    public void put(int key, int value) {
      if(pairs.containsKey(key)){
        addFreq(key);
        pairs.get(key).value = value;
      }else{ //insert new
        if(capacity==0) return;
        if(pairs.size()==capacity){
          remove();
        }
        Node newNode = new Node(key, value);
        if(freqList.containsKey(1)){
          DDLList freqOne = freqList.get(1);
          newNode.prev = freqOne.tail.prev;
          freqOne.tail.prev.next = newNode;
          newNode.next = freqOne.tail;
          freqOne.tail.prev = newNode;
        }else{
          DDLList freqOne = new DDLList();
          freqList.put(1,freqOne);
          freqOne.head.next = newNode;
          freqOne.tail.prev = newNode;
          newNode.prev = freqOne.head;
          newNode.next = freqOne.tail;
        }
        min_freq = 1;
        pairs.put(key,newNode);
      }
    }

    public void addFreq(int key){
      int oldFreq = pairs.get(key).freq;
      int newFreq = oldFreq+1;
      pairs.get(key).freq = newFreq;
      //delete from oldFreq
      Node curNode = pairs.get(key);
      curNode.next.prev = curNode.prev;
      curNode.prev.next = curNode.next;
      //check if oldFreq is empty
      DDLList oldFreqList = freqList.get(oldFreq);
      if(min_freq==oldFreq){
        if(oldFreqList.head.next==oldFreqList.tail){
          freqList.remove(oldFreq);
          min_freq = newFreq;
        }
      }
      //insert to newFreq
      if(!freqList.containsKey(newFreq)){
        freqList.put(newFreq, new DDLList());
      }
      Node newFreqTail = freqList.get(newFreq).tail;
      curNode.prev = newFreqTail.prev;
      curNode.next = newFreqTail;
      newFreqTail.prev.next = curNode;
      curNode.freq+=1;
    }

    public void remove(){
      DDLList minFreqList = freqList.get(min_freq);
      Node firstNode = minFreqList.head.next;
      minFreqList.head.next = firstNode.next;
      firstNode.next.prev = minFreqList.head;
      pairs.remove(firstNode.key);
      // check if minFreqList has no node after delete
      if(minFreqList.head.next == minFreqList.tail){
        freqList.remove(min_freq);
      }
    }
}
