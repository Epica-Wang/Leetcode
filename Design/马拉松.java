一个track上有很多runners（runner已经给的属性有id和name），还有很多check points(已给属性为distance：距离终点的距离，和id)，
check points可以检测到哪个runner跑过它. [需要往这两个对象里加什么样的properties]
1. 实时更新top k的选手
2. Update（runnerId，cpId）每次跑到某个cp的时候 call这个函数。
3. 用这个系统生成一个dashboard显示runner现在的名次/实时更新排名表。
Sol:
#####问题就是如果有40个人，都在两个cp之间，而且在冲刺的话，他们的顺序会发生变化。在进入下一个cp之前我没办法知道到底谁在前谁在后#####
basic: 每个cp记录跑过运动员个数，若该个数 <= 10则返回当前运动员runnerIdx & 该cp的distance & 个数
sol1. 对于topk, 可以用hashMap & maxheap构成indexMaxHeap，存储（runnerId, dist）接受到条目后，
      或decrease_key，或删除最大(top)&插入(仅在严格<时操作) {类似股票} 0(nlogk) n : k*num of check points
sol2. 每个CP维护一个list，将当前pass的人从之前删除，添加至当前list，cp记录当前pass了多少人，用以对runner设置名次，
      若名次在前10，通知leadboard更新离终点最近cp，对每个cp取list, 直到第一个当前设定超过10的cp
sol3. 相当于LRU, 维护一个list, 每个cp存当前刚pass过的runner所对应位置，每个cp检测到runner后，将该runner删除，
      并加入改cp指向的位置下一个 ，更新cp指向位置（可以将list改为size为k, 仅记录号小于k的cp参与，添加删除同前）

/*
my Solution: doubly linked list +  hashMap
1.Runner node保存id，name和lastCheckPoint记录最近经过的cp id

2.对每个checkPoint:维护一个list 和hashmap（map里存runner在list里的node，查找时间为O（1）），每当有新的runner经过该cp（update fn)，插入runnerID到list和map。并在上一个cp list中将该runner删除。
  最好是doubly linked list, O（1）时间删除。

3. 另建一个linked list，每个node代表一个checkpoint，将checkpoint连起来

4. getTop(k)时, 根据3中建立的cp list依次将每个checkpoint中的runnerID顺序输出,直到输出达到k个  O（k）

5. dashboard()，依次将每个checkpoint中的runnerID顺序输出 全部输完

6. update(runnerID, cpID): 分三步 1.将runner加入到该cpID的list和map中 2. 将runner从他lastCheckPoint的list和map中删除  3. 更新runner的lastCheckPoint为cpID

*/
//做两个假设方便写代码：1. runnerID按顺序从0到n递增  2. checkpoint id从0到m递增  越大离终点越近
class Runner{
  int id;
  String name;
  int lastCheckPoint;
}

class CheckPoint{
  class Node{
    int runnerID;
    Node prev;
    Node next;
    public Node(int runnerID){//construct}
  }

  int id;
  int distance;
  Node head; //list头 最先加入的
  Node tail; // list尾 recently added
  HashMap<Integer,Node> map; //store runnerID and it's node in list

  public checkPoint(int id, int distance){//construct}
  public void insertRunner(int runnerID){
    Node newRunner = new Node(runnerID, tail.prev, tail);
    map.put(runnerID, newRunner);
    head.next.prev = newRunner;
  }
  public void deleteRunner(int runnerID){
    Node removeRunner = map.get(runnerID);
    removeRunner.prev.next = removeRunner.next;
    removeRunner.next.prev = removeRunner.prev;
    map.remove(removeRunner);
  }
}

class MarathonBoard{
  private ArrayList<CheckPoint> checkPointList; //store by cpID order
  private ArrayList<Runner> runnerList; //store by runnerID order

  public void update(int runnerID, int cpID){
    //注意刚经过第一个cp的时候 没有oldcp
    CheckPoint curCP = checkPointList.get(cpID);
    Runner runner = runnerList.get(runnerID);
    CheckPoint oldCP = runner.lastCheckPoint;
    curCP.insertRunner(runnerID);
    if(cpID>0){
      oldCP.deleteRunner(runnerID);
    }
    runner.lastCheckPoint = cpID;
  }

  public void getTopK(int k){
    //从checkPointList的尾开始扫。离终点最近的checkpoint
    for(int i=checkPointList.size()-1;i>=0;i--){
      if(k>=0){
        CheckPoint curCP = checkPointList.get(i);
        if(curCP.map.size()==0) continue; //还没人跑到这个checkpoint
        Node head = curCP.head;
        while(head.next!=curCP.tail){
          System.out.println(head.next.id);
          head = head.next;
          k--;
        }
      }
    }
  }

  public void getRank(){//same as getTopK without k limitation}
}
