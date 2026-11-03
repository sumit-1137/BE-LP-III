//Practical 2

/*
Problem Statement:
Write a Java program to solve the Fractional Knapsack
problem using the Greedy Algorithm.

Given a set of items with their weights and profits,
select items to maximize the total profit without
exceeding the given knapsack capacity.

Items can be divided into fractions.

Example:
Weights = {10, 20, 30}
Profits = {60, 100, 120}
Capacity = 50

Output: Maximum Profit = 240
*/
package DAA;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight, profit;

    Item(int weight, int profit) {
        this.weight = weight;
        this.profit = profit;
    }
}

public class FractionalKnapsack {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter weight and profit of item " + (i + 1) + ": ");

            int weight = sc.nextInt();
            int profit = sc.nextInt();

            items[i] = new Item(weight, profit);
        }

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        // Sort items according to profit/weight ratio
        Arrays.sort(items, new Comparator<Item>() {

            public int compare(Item a, Item b) {

                double r1 = (double) a.profit / a.weight;
                double r2 = (double) b.profit / b.weight;

                return Double.compare(r2, r1);
            }
        });

        double totalProfit = 0;

        for (int i = 0; i < n; i++) {

            if (capacity == 0)
                break;

            if (items[i].weight <= capacity) {

                capacity -= items[i].weight;
                totalProfit += items[i].profit;

            } else {

                totalProfit += items[i].profit *
                        ((double) capacity / items[i].weight);

                capacity = 0;
            }
        }

        System.out.println("Maximum Profit = " + totalProfit);

        sc.close();
    }
}
