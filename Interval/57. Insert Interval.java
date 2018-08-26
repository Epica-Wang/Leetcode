Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

 /*
intervals已经按start升序排好，并且不overlap
one-pass扫描intervals   扫描时发现需要跟newinterval merge的区间一边扫一边update merge完区间的范围就行
从头开始扫 两种情况不需要merge：
1. i.end < new.start: newInterval前面的不需要merge
2. i.start > new.end：newInterval后面的不需要merge

扫到第一种情况完全添加之后 此时i.end>=new.start了  如果i.start不大于new.end  就应该merge
 */
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
      List<Interval> res = new ArrayList<>();
      int index = 0;

      //newInterval前面的 直接加
      while(index<intervals.size() && intervals.get(index).end<newInterval.start){
        res.add(intervals.get(index));
        index++;
      }

      while(index<intervals.size() && intervals.get(index).start<=newInterval.end){
        newInterval.start = Math.min(intervals.get(index).start, newInterval.start);
        newInterval.end = Math.max(intervals.get(index).end, newInterval.end);
        index++;
      }

      res.add(newInterval);

      //newInterval后面的 直接加
      while(index<intervals.size()){
        res.add(intervals.get(index));
        index++;
      }
      return res;
    }
}
