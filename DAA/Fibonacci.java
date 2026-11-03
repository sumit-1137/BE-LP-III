//Practical 1

/*
Problem Statement:
Write a Java program to generate the Fibonacci series
using both recursive and non-recursive (iterative) methods.

The Fibonacci series is a sequence in which each number
is the sum of the previous two numbers.

Example:
Input: Number of terms = 7
Output: 0 1 1 2 3 5 8
*/

package DAA;

import java.util.Scanner;

public class Fibonacci {

    // Recursive method
    static int recursive(int n) {

        if (n <= 1) {
            return n;
        }

        return recursive(n - 1) + recursive(n - 2);
    }

    // Non-recursive method
    static void iterative(int n) {

        int a = 0, b = 1, c;

        System.out.print("Non-Recursive: ");

        for (int i = 0; i < n; i++) {

            System.out.print(a + " ");

            c = a + b;
            a = b;
            b = c;
        }

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of terms: ");

        int n = sc.nextInt();

        iterative(n);

        System.out.print("Recursive: ");

        for (int i = 0; i < n; i++) {

            System.out.print(recursive(i) + " ");
        }

        sc.close();
    }
}
