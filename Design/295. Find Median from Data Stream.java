Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3)
findMedian() -> 2
/*
maxHeap: smaller half of data stream. 顶是smaller half中最大的. size保持在len/2
minHeap: larger half of data stream. 顶是larger half中最小的.  size保持在len - len/2
  所以minHeap的size要么=maxHeap+1 要么=maxHeap

find操作：通过比较俩heap的size判断. 注意在这个func里需要考虑edge cases 如heap是空的之类的

add操作：1.比较俩heap顶的值判断加入哪个heap   2.add完然后balance一下二者size的关系

*/
class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianFinder() {
      minHeap = new PriorityQueue<>();
      maxHeap = new PriorityQueue<>(1,new Comparator<Integer>(){
        @Override
        public int compare(Integer n1, Integer n2){
          return n2-n1;
        }
      });

    }

    public void addNum(int num) {
      //处理初始情况  第一个num加在minHeap里  到后面再来第二个元素的时候就顺畅进行后面的操作
      if(minHeap.size()==0){
        minHeap.add(num);
        return;
      }
      if(num<=minHeap.peek()){
        maxHeap.add(num);
        if(maxHeap.size()>minHeap.size()){
          minHeap.add(maxHeap.poll());
        }
      }else{
        minHeap.add(num);
        if(minHeap.size()>maxHeap.size()+1){
          maxHeap.add(minHeap.poll());
        }
      }
    }

    public double findMedian() {
      if(maxHeap.size()==0) return minHeap.size()==0? 0:(double)minHeap.peek();

      //下面这几行必须保证俩heap里都有东西
      if(minHeap.size()==maxHeap.size()){
        return (double)(minHeap.peek()+maxHeap.peek())/2;
      }else{ //size 奇数
        return (double)minHeap.peek();
      }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
