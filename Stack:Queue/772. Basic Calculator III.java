Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

Some examples:

"1 + 1" = 2
" 6-4 / 2 " = 4
"2*(5+5*2)/3+(6/2+8)" = 21
"(2+6* 3+5- (3*14/7+2)*5)+3"=-12

/*
另一种思想：本题变成recursive版的227
碰到括号 用227的思路处理括号里的东西
*/
public int calculate(String s) {
        if (s == null) {
            return 0;
        }
        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }
        q.offer('+');
        return cal(q);
    }

    private int cal(Queue<Character> q) {
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        while (!q.isEmpty()) {
            char c = q.poll();
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';
            } else if (c == '(') {
                num = cal(q);
            } else {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
                if (c == ')') {
                    break;
                }
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }
/*  四道calculator的套路总结
https://leetcode.com/problems/basic-calculator-iii/discuss/113592/Development-of-a-generic-solution-for-the-series-of-the-calculator-problems
*/
Note: Do not use the eval built-in library function.
//Recursive solution: O(n^2) time, O(n) space
public int basicCalculatorIII(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            int num = c - '0';

            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                num = num * 10 + (s.charAt(++i) - '0');
            }

            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '(') {
            int j = i;

            for (int cnt = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') cnt++;
                if (s.charAt(i) == ')') cnt--;
                if (cnt == 0) break;
            }

            int num = basicCalculatorIII(s.substring(j + 1, i));

            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '*' || c == '/') {
            o2 = (c == '*' ? 1 : -1);

        } else if (c == '+' || c == '-') {
            l1 = l1 + o1 * l2;
            o1 = (c == '+' ? 1 : -1);

            l2 = 1; o2 = 1;
        }
    }

    return (l1 + o1 * l2);
}


//Iterative solution: O(n) time, O(n) space
public int basicCalculatorIII(String s) {
    int l1 = 0, o1 = 1;
    int l2 = 1, o2 = 1;

    Deque<Integer> stack = new ArrayDeque<>(); // stack to simulate recursion

    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (Character.isDigit(c)) {
            int num = c - '0';

            while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                num = num * 10 + (s.charAt(++i) - '0');
            }

            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '(') {
            // First preserve current calculation status
            stack.offerFirst(l1); stack.offerFirst(o1);
            stack.offerFirst(l2); stack.offerFirst(o2);

            // Then reset it for next calculation
            l1 = 0; o1 = 1;
            l2 = 1; o2 = 1;

        } else if (c == ')') {
            // First preserve the result of current calculation
            int num = l1 + o1 * l2;

            // Then restore previous calculation status
            o2 = stack.poll(); l2 = stack.poll();
            o1 = stack.poll(); l1 = stack.poll();

            // Previous calculation status is now in effect
            l2 = (o2 == 1 ? l2 * num : l2 / num);

        } else if (c == '*' || c == '/') {
            o2 = (c == '*' ? 1 : -1);

        } else if (c == '+' || c == '-') {
            l1 = l1 + o1 * l2;
            o1 = (c == '+' ? 1 : -1);

            l2 = 1; o2 = 1;
        }
    }

    return (l1 + o1 * l2);
}
