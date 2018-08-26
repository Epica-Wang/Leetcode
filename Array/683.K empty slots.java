class Solution {
    public int kEmptySlots(int[] flowers, int k) {
      TreeSet<Integer> treeSet = new TreeSet<>();
      for(int i=0;i<flowers.length;i++){
        int currentPosition = flowers[i];
        Integer next = treeSet.higher(currentPosition);
        Integer pre = treeSet.lower(currentPosition);

        if(next!=null && next - currentPosition == k+1){
          return i+1;
        }
        if(pre!=null && currentPosition-pre == k+1){
          return i+1;
        }
        treeSet.add(currentPosition);
      }
    }
}
