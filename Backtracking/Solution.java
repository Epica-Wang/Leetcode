import java.util.*;

public class Solution {
    public List<List<List<Integer>>> test()
    {
      List<List<List<Integer>>> threeD  = new ArrayList<List<List<Integer>>>();
      List<List<Integer>> twoD = new ArrayList<>();
      List<Integer> oneD = new ArrayList<>();
      oneD.add(1);
      oneD.add(2);

      twoD.add(oneD);

      List<List<Integer>> twoD_Copy = new ArrayList<>(twoD);
      threeD.add(twoD_Copy);

      //change twoD
      twoD.remove(twoD.size()-1);
      return threeD;
    }

    public static void main(String[] args){
      Solution solu = new Solution();
      List<List<List<Integer>>> threeD = solu.test();
      for(List<List<Integer>> twoD:threeD){
        for(List<Integer> oneD: twoD){
          for(int num: oneD){
            System.out.println(num);
          }
        }
      }
    }
}
