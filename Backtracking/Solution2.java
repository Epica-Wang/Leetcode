
import java.util.*;

public class Solution2 {
    public List<List<List<Integer>>> test()
    {
      List<List<List<Integer>>> threeD  = new ArrayList<List<List<Integer>>>();
      List<List<Integer>> twoD = new ArrayList<>();
      List<Integer> oneD = new ArrayList<>();
      oneD.add(1);
      oneD.add(2);

      twoD.add(oneD);

      List<List<Integer>> twoD_Copy = new ArrayList<>(twoD);
      //boolean isTwoDReference2 = (twoD_Copy.equals(twoD)); //true
      //twoD_Copy.add(new ArrayList<Integer>(oneD));
      threeD.add(twoD_Copy);
      boolean isOneDReference = (threeD.get(0).get(0) == oneD); //true
      boolean isOneDReference2= (twoD_Copy.get(0) == oneD); //true
      boolean isTwoDReference = (threeD.get(0) == twoD); // false
      boolean isTwoDReference2 = (twoD_Copy == twoD); //false
      System.out.println(isOneDReference);
      System.out.println(isOneDReference2);
      System.out.println(isTwoDReference);
      System.out.println(isTwoDReference2);
      //change twoD
      //twoD.remove(twoD.size() - 1);
      //twoD.get(0).remove(0);
      return threeD;
    }

    public static void main(String[] args){
      Solution2 solu = new Solution2();
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
