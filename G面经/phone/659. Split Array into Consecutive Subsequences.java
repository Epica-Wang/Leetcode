You are given an integer array sorted in ascending order (may contain duplicates), you need to split them into several subsequences, where each subsequences consist of at least 3 consecutive integers. Return whether you can make such a split.

Example 1:
Input: [1,2,3,3,4,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3
3, 4, 5
Example 2:
Input: [1,2,3,3,4,4,5,5]
Output: True
Explanation:
You can split them into two consecutive subsequences :
1, 2, 3, 4, 5
3, 4, 5
Example 3:
Input: [1,2,3,4,4,5]
Output: False
Note:
The length of the input is in range of [1, 10000]
//////////////////////////////////////////////////
Lets take [1, 2, 3, 3, 4, 5, 7] as an example

[1, 2, 3, 3, 4, 5, 7]
>> [1]
>> [1,2] #2
>> [1,2,3]
>> [1,2,3] [3]  #1
>> [1,2,3] [3,4]
>> [3,4,5] #3
>> [7] #4
After observation, there are 3 cases

Case 1 : num == pq.peek().endNumber, we offer a new interval ( endNumber : num, length : 1 ) to pq => #1
Case 2 : num == pq.peek().endNumber + 1, we append it to pq.peek() interval => #2
Case 3 : num > p1.peek().endNumber + 1,
we keep abandoning intervals (if the length of the interval to abandon is smaller than 3, return false) until we could reduce to case 2 => #3
if we successfully remove all intervals but cannot reduce to case 2, we offer a new interval ( tail : num, length : 1 ) to pq => #4
In the priority queue, all intervals are sorted by endNumber increasingly, if there is a tie, we sort them by length increasingly.

Essence
Lets call a sequence with two ends as an interval. Whenever we do with interval-related problem, we always encapsulate useful information into a class Interval and use a PriorityQueue to compare intervals - or else, we may get us into a mess for sequence comparison.
class Interval {
    int endNumber;
    int length;
    public Interval(int endNumber, int length) {
        this.endNumber = endNumber;
        this.length = length;
    }
}

public boolean isPossible(int[] nums) {

        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> b.endNumber == a.endNumber ? a.length - b.length : a.endNumber - b.endNumber);

        for (int num : nums) {
            if (pq.isEmpty() || pq.peek().endNumber == num) {
                pq.add(new Interval(num, 1));
            } else {
                while (!pq.isEmpty() && pq.peek().endNumber + 1 < num) {
                    if (pq.poll().length < 3) {
                        return false;
                    }
                }
                if (pq.isEmpty()) {
                    pq.add(new Interval(num, 1));
                } else {
                    Interval interval = pq.poll();
                    interval.endNumber = num;
                    interval.length++;
                    pq.add(interval);
                }
            }
        }
        while (!pq.isEmpty()) {
            if (pq.poll().length < 3)
                return false;
        }

        return true;
    }
