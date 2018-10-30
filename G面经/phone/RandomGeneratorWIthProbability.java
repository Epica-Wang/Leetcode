/* useful disscussion in stackoverflow
https://stackoverflow.com/questions/20327958/random-number-with-probabilities
*/
Generate random integers from within [1;3] with the following probabilities:

P(1) = 0.2
P(2) = 0.3
P(3) = 0.5

// approach 1: generate a random integer within [0;100] and do the following:
If it is within [0;20] --> I got my random number 1.
If it is within [21;50] --> I got my random number 2.
If it is within [51;100] --> I got my random number 3.

Random rand = new Random();
int p = rand.next(100);
if (p<20) return 1;
else if p(<50) return 2;
else return 3;
// approach 2:
get rid of the fractions by multiplying with a constant multiplier,
and then build an array with the size of this multiplier.
Multiplying by 10 you get:

P(1) = 2
P(2) = 3
P(3) = 5
Then you create an array with the inverse values -- '1' goes into elements 1 and 2, '2' into 3 to 6, and so on:

P = (1,1, 2,2,2, 3,3,3,3,3);

引出问题：how to find the smallest multiplier to make all fractions whole number?
如： 0.2, 0.3, 0.5
如果我们分别知道让0.2, 0.3, 0.5 成为whole number的multiplier为m1, m2, m3
=> then the lowest common multiple of m1, m2 and m3 is the result.

则问题转化成如何求smallest multiplier to make a fraction a whole number?
假设fraction is a/b, 求出greatest common divisor （gcd）of a and break;
则 a/b可以写成 n1 * gcd / n2 * gcd = n1 / n2. 那a/b 乘以 n2就可以是个整数了 = n1.
