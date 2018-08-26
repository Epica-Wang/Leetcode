 /*规则类似 candy crush 游戏
 给你一个 table, n*n 的,然后 3 种 color,要求每排每列不能有连续三个一样颜色的。随机生成
 table。
 */

public ArrayList<int[]> generateTable(int n){
  ArrayList<ArrayList<ArrayList<Integer>>> res = new ArrayList<ArrayList<ArrayList<Integer>>>();
  generate(0,0,new ArrayList<ArrayList<Integer>>(), res);
  return res;
}

public void generate(int n, int i, int j, ArrayList<ArrayList<Integer>> temp,ArrayList<ArrayList<ArrayList<Integer>>> res){
  if(temp.size()==n-1 && temp.get(temp.size-1).size()==n-1){
    res.add(new ArrayList(temp));
    return;
  }
  if(j==n||i==n){
    return;
  }
  // fill table[i][j]
  // 不太对
  for(int color=1;color<=3;color++){
    if(testColor(i,j,color,temp)){
      temp.get(i).set(j,color);
      generate(n,i,j+1,temp,res);
      generate(n,i+1,j,temp,res);
      temp.get(i).set(j,0);
      break;
    }else{
      continue;
    }
  }//for
  // generate(n,i,j+1,temp,res);
  // generate(n,i+1,j,temp,res);
  // temp.get(i).set(j,0);
}

public boolean testColor(int i, int j, ArrayList<ArrayList<Integer>> temp){

}
