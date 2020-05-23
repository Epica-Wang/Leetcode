Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// if allow duplicates
/* 花花酱 https://www.youtube.com/watch?v=mRTgft9sBhA
1. HashMap to store (val -> (Index1InArray, index2InArray) )
2. resultArray: store (val, indexInValueSetOfHashMap)

eg:
HashMap:
  1-> (0, 2)
  3-> (1, 3)
resultArray:
  (1, 0[在hm的1的valueset里index是第一个]) (3, 0[在hm的3的valueset里index是第一个]), (1, 1[在hm的1的valueset里index是第2个], (3, 1[在hm的3的valueset里index是第2个]))

delete(1):
- get last one of 1's indexInArray from hm: 2
- 2 is not the lastElement of resultArray, swap with lastElement:
  - lastElement  = 3, its index in valueset of 3 is 1.
  swap的时候 resultArray里（3，2）这个pair不用变，但需要这个2去hashMap里找到 3 在resultArray里的index，which is 3.
  - change this index in hashMap to 1's indexInArray: 即hashMap 3 -> (1, 2)
  - also change resultArray to: (1, 0) (3, 0), (3, 1), (3, 1)
  - delete lastElement in resultArray: (1, 0) (3, 0), (3, 1)
  - delete a 1 in hashMap: 1 -> (0)

*/
class RandomizedCollection {
    private HashMap<Integer, ArrayList<Integer>> hm;
    private ArrayList<ArrayList<Integer>> resultArray;   //each element: [val, index in LinkedList of hm'key]
    /** Initialize your data structure here. */
    public RandomizedCollection() {
      hm = new HashMap<>();
      resultArray = new ArrayList<>();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
      if (hm.containsKey(val)) {
        int duplicatesCount = hm.get(val).size();
        ArrayList<Integer> newElement = new ArrayList<>(Arrays.asList(val, duplicatesCount));
        resultArray.add(newElement);
        // add new index for this val in hashmap
        hm.get(val).add(resultArray.size()-1);
        return false;
      }else{
        ArrayList<Integer> newElement = new ArrayList<>(Arrays.asList(val, 0));
        resultArray.add(newElement);
        // create a new LinkedList
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(resultArray.size()-1);
        hm.put(val, newList);
        return true;
      }
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
      if (!hm.containsKey(val)) return false;
      else{
        int valIndexAtresultArray = hm.get(val).get(hm.get(val).size()-1);

        // if this val is last element in resultArray, remove directly
        if(valIndexAtresultArray == resultArray.size()-1) {
          resultArray.remove(resultArray.size()-1);
        }else{ // swap this val with lastElement in resultArray
          ArrayList<Integer> lastElement = resultArray.get(resultArray.size()-1);
          int lastElementIndex = lastElement.get(1); // its index in hashmap key lastElement

          resultArray.set(valIndexAtresultArray, lastElement);

          // edit hashmap: change lastElement's new index
          hm.get(lastElement.get(0)).set(lastElementIndex, valIndexAtresultArray);

          resultArray.remove(resultArray.size()-1);
        }

        // deal with hashmap
        // delete this entry in hashmap if no duplicates
        if (hm.get(val).size() == 1) {
          hm.remove(val);
        }else{
          hm.get(val).remove(hm.get(val).size()-1);
        }

        return true;
      }
    }

    /** Get a random element from the collection. */
    public int getRandom() {
      Random rand = new Random();
      int randomIndex = rand.nextInt(resultArray.size());
      return resultArray.get(randomIndex).get(0);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
