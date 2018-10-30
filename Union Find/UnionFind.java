//讲解： https://www.youtube.com/watch?v=VJnUwsE4fWA
class UnionFindSet{
  //用数字index 0~n+1来表示点
  private int[] parents;
  private int[] ranks;
  private int count;
  public UnionFindSet(int n){ // constructor
    //at beginning every node is a cluster. its parent is itself
    count =n; //初始n个cluster
    parents =  new int[n+1];
    ranks = new int[n+1];;
    for(int i=0;i<parents.length;i++){
      parents[i] = i;
      ranks[i] = 1;
    }
  }

  /*在第一次find某个x的时候, 实现了path compression(i.e tree flat),
  之后再find这个x就是O（1）的时间了。
  只有第一次find x需要O(N).
  */
  public int find(x){  //返回x的根节点的
    //同时能做到，将x的所有祖先都指向这个cluster的根节点(tree flat)
    if (x!=parents[x]){
      parents[x] = find(parents[x]);
    }
    return parents[x];
    /* 或者用while的写法
    while(x!=parents[x]){
      parents[x] = parents[parents[x]];
      x = parents[x];
    }
    return x;
    */
  }

  public boolean union(x,y){
    int px = find(x);
    int py = find(y);
    //x &y 已经在一个cluster里面，return false 表示没有merge
    if(px==py) return false;

    //让rank低的merge到rank高的root上面去，这样能减少之后path compreesion需要compress的点的数目
    if(ranks[px]>ranks[py])
      parents[py] = px;
    else if(ranks[py]>ranks[px])
      parents[px] = py;
    else if(ranks[py]==ranks[px]){
      //随便选一个成为另一个的parent
      parents[py] = px;
      ranks[px]++;
    }
    count--; //成功union了一次
    return true;
  }

  public int count(){
    return count;
  }
}
