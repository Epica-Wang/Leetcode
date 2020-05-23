Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example 1:

Input: [[0, 30],[5, 10],[15, 20]]
Output: 2
Example 2:

Input: [[7,10],[2,4]]
Output: 1

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

/*soluiton1 扫描线
将这三个区间在x轴上画出来，并用一条垂直于x轴的线作为扫描线从左至右扫描，会很容易得出答案，即与扫描线焦点的最大值即为所求。但是在程序中我们怎样表示这种思想呢？
-对所有点进行标记，区分起始点和终止点
-对所有点进行排序
-依次遍历每个点，遇到起始点+1，遇到终止点-1，并更新记录最大值
*/
public class Solution {
    private class Point{
        int time;
        boolean isStart;
        public Point(int time, boolean isStart){
            this.time = time;
            this.isStart = isStart;
        }
    }

    private class PointComparator implements Comparator<Point>{
        public int compare(Point a, Point b){
            if(a.time == b.time) return a.isStart ? 1 : -1 ;
            else return a.time - b.time;
        }
    }

    public int minMeetingRooms(Interval[] intervals) {
        if(intervals == null || intervals.length == 0) return 0;

        Point[] arr = new Point[intervals.length * 2];

        for(int i = 0; i < intervals.length; i++){
            arr[i * 2] = new Point(intervals[i].start, true);
            arr[i * 2 + 1] = new Point(intervals[i].end, false);
        }

        Arrays.sort(arr, new PointComparator());

        int maxOverLap = 0;
        int curCount = 0;
        for(Point pt : arr){
            if(pt.isStart) curCount ++;
            else curCount --;
            maxOverLap = Math.max(maxOverLap, curCount);
        }

        return maxOverLap;
    }
}
(FB) follow-up: 如何返回重叠最多的那个区间？
当前 maxOverlap 创造新高的时候，存下 start 的时间戳，因为这是所有重合区间中 start 时间最晚的一个；
继续扫描，看到新的 end 的时候，存下这个 end 的时间戳，因为它是重合区间里 end 时间最早的一个；
二者之间，就是具体的 max overlap 区间。

//solution2 思路同上扫描线 只是将start和end time单独存成array，从而避免了建Point class
 public class Solution {
     public int minMeetingRooms(Interval[] intervals) {
         int[] starts = new int[intervals.length];
         int[] ends = new int[intervals.length];
         for(int i=0; i<intervals.length; i++) {
             starts[i] = intervals[i].start;
             ends[i] = intervals[i].end;
         }
         Arrays.sort(starts);
         Arrays.sort(ends);
         int rooms = 0;
         int endsItr = 0;
         for(int i=0; i<starts.length; i++) {
             if(starts[i]<ends[endsItr])
                 rooms++;
             else
                 endsItr++;
         }
         return rooms;
     }
 }

//solution3:heap +greedy
class Solution {
    public int minMeetingRooms(Interval[] intervals) {
      if(intervals.length<=1) return intervals.length;

      Arrays.sort(intervals, new Comparator<Interval>(){
        @Override
        public int compare(Interval i1, Interval i2){
          return i1.start - i2.start;
        }
      });
      PriorityQueue<Interval> minHeap = new PriorityQueue<>(intervals.length, new Comparator<Interval>(){
        @Override
        public int compare(Interval i1, Interval i2){
          return i1.end - i2.end;
        }
      });

      minHeap.add(intervals[0]);
      for(int i=1;i<intervals.length;i++){
        Interval temp = minHeap.peek();
        if(temp.end<=intervals[i].start){
          temp = minHeap.poll();
          temp.end = intervals[i].end;
          minHeap.add(temp);
        }else{
          minHeap.add(intervals[i]);
        }
      }
      return minHeap.size();
    }
}
