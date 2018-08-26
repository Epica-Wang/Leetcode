// https://www.hackerrank.com/contests/infinitum-jun14/challenges/possible-path/problem

import java.io.*;
import java.util.*;

public class Solution {
    public static int gcd(int a,int b){
        while(a!=0 && b!=0) {
         int c = b;
         b = a%b;
         a = c;
        }
        return a+b; // either one is 0, so return the non-zero value
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        for(int i=0;i<t;i++){
            int a = scan.nextInt();
            int b = scan.nextInt();
            int x = scan.nextInt();
            int y = scan.nextInt();
            if(gcd(a,b)==gcd(x,y)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }
}
