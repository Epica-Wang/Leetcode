/* Solution 2:
https://zhengyang2015.gitbooks.io/lintcode/lru_cache.html
https://lefttree.gitbooks.io/leetcode/dataStructure/LRUCache.html
https://mnmunknown.gitbooks.io/algorithm-notes/content/shu_ju_jie_gou_de_li_yong_ff0c_chu_li_steam_data.html
both operations in O(1) time complexity
思路: 先分析requirement
Access to a random key in O(1)->hashtable
Remove the last entry in LRU cache in O(1) -> List
Add/move an entry to the front of LRU cache in O(1)->list.
说白了就是手动在HashMap的基础上实现LinkedHashMap： “头 + 尾 dummy node 的双向链表” + “HashMap”.
·但linked list中查找指定item需要遍历，这里可以用一个hashMap来记录key与节点之间的对应。
·linked list最尾部的表示最新访问的
  get(key)
    把node放到尾部
    读取value
  set(key, value)
    如果key已经有了
    放到尾部
    更新value
    如果key还没有
    放到尾部
    如果 size > capacity, 讲头部的node pop掉
因为头部和尾部都不确定，所以就像linkedlist要有dummy node一样，要有一个head和tail
head一直不变，指向第一个node（表示least recently used） tail一直指向最后一个node，每次更新(表示most recently used)
*/
class LRUCache {
    private class Node{
      Node next;
      Node prev;
      int key;
      int value;
      public Node(int key, int value){
        this.key = key;
        this.value = value;
        next = prev = null;
      }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    // least recently used
    private Node head;
    // most recently used
    private Node tail;

    public LRUCache(int capacity) {
      this.capacity = capacity;
      this.map =  new HashMap<Integer, Node>();
      head = new Node(-1,-1);
      tail = new Node(-1,-1);
      head.next = tail;
      tail.prev = head;
    }

    public int get(int key) {
      if(map.containsKey(key)){
        Node current = map.get(key);
        moveToEnd(current);
        return current.value;
      }else{
        return -1;
      }
    }

    public void put(int key, int value) {
      if(map.containsKey(key)){
        Node current = map.get(key);
        moveToEnd(current);
        current.value = value;
      }else{

        if(map.size()==capacity){
          removeHead();
        }
        Node newNode = new Node(key,value);
        tail.prev.next = newNode;
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev = newNode;

        map.put(key, newNode);
      }
    }

    public void moveToEnd(Node node){
      Node prevNode = node.prev;
      Node nextNode = node.next;
      // disconnect node from LinkedList
      prevNode.next = nextNode;
      nextNode.prev = prevNode;
      // insert node at end
      tail.prev.next = node;
      node.prev = tail.prev;
      node.next = tail;
      tail.prev = node;

    }
    public void removeHead(){
      Node removeNode = head.next;
      Node nextNode = removeNode.next;
      head.next = nextNode;
      nextNode.prev = head;
      map.remove(removeNode.key);
    }
}



/* Solution 1: use a LinkedHashMap. every time get/put an existing element,
first remove that element, then put back to the tail of LinkedHashMap.
evict is achieved by remove first element in LinkedHashMap.
所有操作time：O(1)

*/
class LRUCache {
    private HashMap<Integer,Integer> lhp;
    private int capacity;
    public LRUCache(int capacity) {
      this.capacity = capacity;
      lhp = new LinkedHashMap<>(capacity);
    }
    // in get, only need to remove and reput. i.e. update recently used
    public int get(int key) {
      if(lhp.containsKey(key)){
        int value =lhp.get(key);
        lhp.remove(key);
        lhp.put(key,value);
        return value;
      }else{
        return -1;
      }
    }
    // in put, if key exists, remove and reput. if not exists and reach capacity, evict first element in LinkedHashMap
    public void put(int key, int value) {
      if(lhp.containsKey(key)){
        lhp.remove(key);
        lhp.put(key,value);
      }else{
        if(lhp.size()==this.capacity){
          int keyRemoved = getFirstItem(lhp);
          lhp.remove(keyRemoved);
          lhp.put(key,value);
        }else{
          lhp.put(key,value);
        }
      }
    }

    private int getFirstItem(HashMap<Integer,Integer> lhp){
      Map.Entry<Integer,Integer> entry = lhp.entrySet().iterator().next();
      return entry.getKey();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
