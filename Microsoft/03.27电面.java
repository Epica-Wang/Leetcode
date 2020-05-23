// You have an array of integers.
// The array can be changed with the Set method.
// At any time, a snapshot of the array can be taken, and a snapshotId is returned.
// There is a GetSnapshot method, which will return the array's contents at that given snapshot.

// Implement the below methods:

// Get(int index) : int
// Set(int index, int value) : void
// TakeSnapshot() : snapshotId
// GetSnapshot(snapshotId) : Array<int>

// Example
/*
    Initial Array: [ 0, 0, 0 ]
    Set(0,1) => [ 1, 0, 0 ]
    TakeSnapshot() => ID1
    TakeSnapshot() => ID2
    Set(1,2) => [ 1, 2, 0 ]
    TakeSnapshot() => ID3
    Set(2, 3) => [ 1, 2, 3 ]
    ...
    GetSnapshot(ID1) => [ 1, 0, 0 ]
    GetSnapshot(ID3) => [ 1, 2, 0 ]
*/

int arraySize = 10;
int[] originalArray = new int[arraySize];
int snapshotId = 0;
List<int[]> currentListOfChanges = new LinkedList<>();
// snapshotId => listOfChanges
HashMap<Integer, List<int[]>> snapshotListOfChangesMap = new HashMap<>();
// Get(int index) : int
public int get(int index) {
  // 1. construct the newest array
  // apply all the changes in the snapshotListOfChangesMap to originalArray
  return applyChanges(snapshotId)[index];
}

private int[] applyChanges(int endingSnapshot) {
  int[] newArray = new int[]{originalArray};
  for (int snapshot = 1 ; snapshot <= endingSnapshot; snapshot++) {
    List<int[]> listOfChanges = snapshotListOfChangesMap.get(snapshot);
    for (int[] change : listOfChanges) {
      newArray[change[0]] = change[1];
    }
  }
  return newArray;
}
// Set(int index, int value) : void
public void set(int index, int value) {
  currentListOfChanges.add(new int[]{index, value});
}

/**
 * list of Change: every time we call set(index, value):
 * add [index, value] to the listOfChange
	*/
// TakeSnapshot() : snapshotId
// set() * 5
// list of Change : 5
// takeSnapshot() => idI
// set() * 3
// list of Change : 5 + 3

// hashMap(snapshotId, new changes made since last snapshotId)

// getSnapShot
// retrive listChange from hashMap
//
public int takeSnapShot() {
  snapshotId++;
  snapshotListOfChangesMap.put(snapshotId, currentListOfChanges);
  // reset the currentListOfChanges to be empty
  currentListOfChanges = new LinkedList<>();
  return snapshotId;
}

// GetSnapshot(snapshotId) : Array<int>
public int[] getSnapShot(int snapshotId) {
  // apply all the changes before the snapshotId passedIn
  return applyChanges(snapshotId);
}
