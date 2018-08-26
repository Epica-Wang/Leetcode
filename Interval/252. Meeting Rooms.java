Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
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
按 start 排序后，数组有了单调性，上面的判断条件就简化成了 A.end > B.start 则一定重叠.
*/
/*Solution1:自定义comparator*/
public class Solution {
    private class IntervalComparator implements Comparator<Interval>{
        public int compare(Interval a, Interval b){
            return a.start - b.start;
        }
    }

    public boolean canAttendMeetings(Interval[] intervals) {
        if(intervals == null || intervals.length <= 1) return true;

        Arrays.sort(intervals, new IntervalComparator());

        for(int i = 1; i < intervals.length; i++){
            if(intervals[i].start < intervals[i - 1].end) return false;

        }

        return true;
    }
}
/*solution2:
巧妙的想法
给start和end各建一个array  然后分别sort
如果begin[i]<stop[i-1] 则一定false
因为begin的index到了i 若可以参加 则一定前i-1个meeting都比它早结束
前i-1个里面最大的结束时间是stop[i-1] 
*/
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
                int len=intervals.length;
        if(len==0){
            return true;
        }
        int[]begin=new int[len];
        int[]stop=new int[len];
        for(int i=0;i<len;i++){
            begin[i]=intervals[i].start;
            stop[i]=intervals[i].end;
        }
        Arrays.sort(begin);
        Arrays.sort(stop);
        int endT=0;
        for(int i=1;i<len;i++){
            if(begin[i]<stop[i-1]){
                return false;
            }
        }
        return true;
    }
}

/*solution3:利用treeMap sort key的性质 把start time作为key
建好map之后iterate一遍*/
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
      if(intervals.length<=1) return true;
      TreeMap<Integer, Integer> tmap = new TreeMap<>();
      for(Interval interval:intervals){
        if(tmap.containsKey(interval.start)){
          return false;
        }else{
          tmap.put(interval.start,interval.end);
        }
      }
      int lastStart = -1
      int lastEnd = -1;
      for(Map.Entry<Integer, Integer> entry:tmap.entrySet()){
        if(lastEnd>entry.getKey()||){
          return false;
        }else{
          lastEnd = entry.getValue();
        }
      }
      return true;
    }
}
