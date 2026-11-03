//Practical 3

/*
Problem Statement:
Write a Java program to solve the 0/1 Knapsack problem
using Dynamic Programming.

Given a set of items with their weights and profits,
select items to maximize the total profit without
exceeding the given knapsack capacity.

Each item can either be selected completely (1)
or not selected (0). Fractional selection is not allowed.

Example:
Weights = {10, 20, 30}
Profits = {60, 100, 120}
Capacity = 50

Output: Maximum Profit = 220
*/

package DAA;

import java.util.Scanner;

public class Knapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weight = new int[n];
        int[] profit = new int[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter weight of item " + (i + 1) + ": ");
            weight[i] = sc.nextInt();

            System.out.print("Enter profit of item " + (i + 1) + ": ");
            profit[i] = sc.nextInt();
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        int[][] dp = new int[n + 1][capacity + 1];

        // Dynamic Programming
        for (int i = 1; i <= n; i++) {

            for (int w = 1; w <= capacity; w++) {

                if (weight[i - 1] <= w) {

                    dp[i][w] = Math.max(
                        profit[i - 1] + dp[i - 1][w - weight[i - 1]],
                        dp[i - 1][w]
                    );

                } else {

                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println("Maximum Profit = " + dp[n][capacity]);

        sc.close();
    }
}
