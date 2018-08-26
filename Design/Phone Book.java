电话薄phone book (一个人名对应多个电话号码)
  1. output the phone book by alphabetic order
  2.输入人名：0）auto-complete, 1）查找对应电话号码，2）返回和这个人是neighbor的另外4个人的phone number(alphabetic order)
  3.输入电话号码找名字
  4.如果不考虑memory，在disk上做，怎么设计这个数据结构[电话本在disk上应该就是简单的database吧，一列是人名，另一列是电话，因为一个人可以有多个电话，所以同一个人名可以有多行。然后primary key 应该是人名和电话的组合]
Sol:
数据结构：Tire or treeMap (or hashMap ——不涉及alphabetic order的情况下)
——对于Trie返回neighbor:
	查找下一个：直接在当前节点的children里找下一个，如果没有则返回parent节点，从下一个child开始遍历。
	查找上一个：返回parent节点，从上一个child开始逆序遍历。
	*每个节点可以存它所有可能到达的节点array，可以直接输出下一个（array的内容or parent array的内容来加速： 但array只在顺序添加人名的情况下为sort的）
——对于treemap完成auto-compelete, 找到第一个大于等于输入的节点，输出从当前节点开始，保证最后一个字母不变情况下的正序遍历的结果。

/*my solution*/
Trie Tree存电话号码，叶子结点存人名.
TreeMap 存人名和电话号码的关系。key是name， value是Trie tree里这个人的电话号码的叶子结点。（如果一个人有多个电话号码，则value是list of Trie node)
  1.output the phone book by alphabetic order:遍历treeMap，每个人去trie里找电话打印
  2.输入人名：
    0）auto-complete：找到第一个大于等于输入name的节点，输出从当前节点开始，保证最后一个字母不变情况下的正序遍历的结果。
    例子：如要auto-complete "han":假设电话本里大于"han" 有"hank","hanks","hans","ham"...输出到"ham"之前就应该停止。
    1）查找对应电话号码在TreeMap里找到name对应的trieNode，在Trie里向上一路找到root构建电话号码
    2）返回和这个人是neighbor的另外4个人的phone number：找到前两个比name小，后两个比name大的key，分别查询电话号码
    3）输入电话号码找名字：直接在Trie里查询

//TrieNode
class TrieNode{
  public int digit;
  public TrieNode[] children; //size 10 array. store 0~9
  public String name; //非叶子结点的name==null

  public TireNode parent; //存一下parent，方便从人名找电话号码时回溯
}

//Trie
class Trie{
  private TrieNode root;

  public TrieNode getRoot(){return root;}
  public void insert(String name, String phoneNumber){}
  public String searchByName(TrieNode node){} //return phonenumber. giving name node, search from bottom to up to get phonenumber
  public String searchByNumber(String phoneNumber){} //return name

}

class PhoneBook{
  Trie phoneBook;
  TreeMap<String, TrieNode> namePhoneMap;

  public void autoComplete(String name, namePhoneMap){}
  public String searchByName(String name){ TrieNode node = namePhoneMap.get(name); return phoneBook.searchByName(node)};
  public String searchByNumber(String phoneNumber){return phoneBook.searchByNumber(phoneNumber)};

  public List<String> findNeightbor(String name){}

}
