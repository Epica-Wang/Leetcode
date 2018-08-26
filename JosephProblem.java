/*
n个人排成一圈，这n个人的序号分别是0 ~ n-1，
给定一个数m,从头开始报数，每报到m-1时，这个人出列，
然后下个人从0开始报数，直到最后一个人留下，求最后一个人的序号。
arraylist解法：https://blog.csdn.net/u011514810/article/details/55667136

*/
import java.util.*;

/*解法recursive: 时间O(N) 空间O(1)
假设f(n)是初始有n人时，最后留下的人的编号
则有f(n) = [f(n-1)+m] mod n
以n=5，m=3为例：
  一开始有5个人：
  0 1 2 3 4
  第一轮结束后2号死亡 圈子里剩下： 因为下一个从3开始 把3放在前面
  3 4 0 1
  如果我们有办法知道初始为4人时的答案，即圈子为：
  0 1 2 3
  那我们可以把n=4的初始圈子的所有数x变成 (x+m) mod 5 即：
  3 4 0 1
  恰好为n=5时第一轮结束后的状态， 如果知道n=4的答案 就能知道n=5的答案
当求n=z时的圈子第一轮结束后的圈子状态 可由n=z-1时的初始圈子做变换x-> (x+m) mod z 得到
则我们可从n=1的答案 推出n=2 直到n=z
*/
public class JosephProblem{
  public int josephRecursive(int n, int m){
    if(n==1) return 0;
    return (josephRecursive(n-1,m) + m)%n;
  }
  public static void main(String[] args){
    JosephProblem test = new JosephProblem();
    int last = test.josephRecursive(7,3);
    System.out.println(last);
  }
}

/*
解法：用array。时间：O(M*N) 空间O(N) 每杀一个人要O(M)，一共杀n人
*/
public class JosephProblem{
  public int[] joseph(int n, int m){
    int[] persons =  new int[n];
    int[] results = new int[n]; //存处决顺序
    int count = 0;//杀了几个
    int step = 0;
    while(count<m){
      for(int i=0;i<persons.length;i++){
        if(persons[i]==0){
          step++;
        }
        if(step==m){ //kill this person
          persons[i]=-1;
          results[count]=i;
          count++;
          step=0;
        }
      }
    }
    return results;
  }
}
/*
解法Circular linkedlist: 首尾相连
https://www.geeksforgeeks.org/josephus-circle-using-circular-linked-list/
/*
解法： arraylist
*/

public class JosephProblem{
  public int joseph(int totalNum, int countNum){
    if(totalNum<=0||countNum<=0) return -1;
    //if(totalNum==1) return
    List<Integer> alist = new ArrayList<>();
    for(int i=0;i<totalNum;i++){
      alist.add(i);
    }
    int index=0;
    while(alist.size()>1){
      index = (index+countNum-1)% alist.size();
      System.out.format("now is %d %n", alist.get(index));
      alist.remove(index);
    }
    return alist.get(0);
  }

  public static void main(String[] args){
    JosephProblem test = new JosephProblem();
    int last = test.joseph(14,2);
    System.out.println(last);
  }
}
