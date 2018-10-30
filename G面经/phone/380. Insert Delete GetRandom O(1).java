Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

/*
Insert O(1) & Delete O(1) -> HashTable
getRandom O(1) -> Array

1. HashMap to store (val -> IndexInArray)
2. resultArray: store actual val

Insert(val):
- if hm contains val return false;
- if hm don't contain val
  add val to end of resultArray
  insert a new entry to hm
  return true

Delete(val):
- if hm don't contain val return false;
- if hm contain val:
  get index of val from hm
  - if index is last index of resultArray => delete last element in resultArray, delete that entry in hm
  - if index is not the last one => swap last element to this position, change its IndexInArray in hm, delete val entry in hm, delete last element in resultArray

getRandom():
  generate a index in range [0, resultArray.size()-1]
  return resultArray[index]
*/
class RandomizedSet {
    private HashMap<Integer, Integer> hm;
    private ArrayList<Integer> resultArray;
    /** Initialize your data structure here. */
    public RandomizedSet() {
      hm = new HashMap<>();
      resultArray = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
      if (hm.containsKey(val)) return false;

      resultArray.add(val);
      hm.put(val, resultArray.size()-1);
      return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
      if (!hm.containsKey(val)) return false;

      int valIndex = hm.get(val);
      if (valIndex == resultArray.size()-1) {
        resultArray.remove(valIndex);
        hm.remove(val);
      }else { // need to swap with last element
        int lastElement = resultArray.get(resultArray.size()-1);
        hm.put(lastElement, valIndex);
        resultArray.set(valIndex, lastElement);
        
        resultArray.remove(resultArray.size()-1);
        hm.remove(val);
      }
      return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
      Random rand = new Random();
      int index = rand.nextInt(resultArray.size());
      return resultArray.get(index);
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
