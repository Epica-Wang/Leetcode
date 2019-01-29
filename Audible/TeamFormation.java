
/*
2. teamFormation.  给一个int[] scores， int team， int m，
挑选队员的方法是从array的前m个和后m个中间挑出最大的，
如果array总长度小于m了就直接返回所有里面最大的，挑出team个数字，返回他们的加和。
方法是自己写了comparable或者comparator，然后用priorityqueue做。注意控制arrayindex

input: List<Integer> score, int team, int m
output: Long score

https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=441214&highlight=audible

 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class TeamFormation {

    public static long form(int[] input, int team, int m) {
        long res = 0;
        if (2 * m >= input.length) {
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> (b - a));
            for (int i = 0; i < input.length; i++) {
                priorityQueue.add(input[i]);
            }
            for (int i = 0; i < team; i++) {
                res = res + priorityQueue.poll();
            }
            return res;
        }
        PriorityQueue<Integer> p1 = new PriorityQueue<>((a, b) -> (b - a));
        PriorityQueue<Integer> p2 = new PriorityQueue<>((a, b) -> (b - a));
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            p1.add(input[i]);
            p2.add(input[input.length - 1 - i]);
        }
        for (int i = m; i < input.length - m; i++) {
            deque.add(input[i]);
        }
        for (int i = 0; i < team; i++) {
            if (!p1.isEmpty() && !p2.isEmpty()) {
                if (p1.peek() >= p2.peek()) {
                    res += p1.poll();
                    if (!deque.isEmpty()) {
                        p1.add(deque.pollFirst());
                    }
                } else {
                    res += p2.poll();
                    if (!deque.isEmpty()) {
                        p2.add(deque.pollLast());
                    }
                }
            } else if (!p1.isEmpty()) {
                res += p1.poll();
            } else {
                res += p2.poll();
            }
        }
        return res;
    }

    public static void main(String[] args) {
//        System.out.println(TeamFormation.form(new int[]{1, 2, 3, 4}, 4, 2));
//
//        System.out.println(TeamFormation.form(new int[]{1, 2, 3, 4}, 3, 2));
//
//        System.out.println(TeamFormation.form(new int[]{1, 2, 3, 4}, 3, 3));

//        System.out.println(TeamFormation.form(new int[]{6, 4, 6, 8, 8, 0, 8, 9, 1, 3, 10}, 5, 3));  // 41
//        System.out.println(TeamFormation.form(new int[]{6, 4, 6, 8, 8, 0, 8, 9, 1, 3, 10}, 6, 3));  // 49
//        System.out.println(TeamFormation.form(new int[]{6, 4, 6, 8, 8, 0, 8, 9, 1, 3, 10}, 6, 6));  // 49
//        System.out.println(TeamFormation.form(new int[]{6, 4, 6, 8, 8, 0, 8, 9, 1, 3, 10}, 8, 6));  // 59
        System.out.println(TeamFormation.form(new int[]{8, 18, 5, 15, 18, 11, 15, 9, 7}, 5, 1));

    }
}
