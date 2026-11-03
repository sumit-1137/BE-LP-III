//Practical 4

/*
Problem Statement:
Write a Java program to implement Quick Sort using
both Deterministic and Randomized pivot selection.

In Deterministic Quick Sort, the last element is selected
as the pivot.

In Randomized Quick Sort, the pivot is selected randomly.

Both methods divide the array into smaller partitions
and sort them recursively.

Example:
Input: 45 20 10 60 30 80 50

Output: 10 20 30 45 50 60 80
*/

package DAA;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    // Deterministic Quick Sort
    static int partition(int arr[], int low, int high) {

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    static void quickSort(int arr[], int low, int high) {

        if (low < high) {

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Randomized Quick Sort
    static int randomizedPartition(int arr[], int low, int high) {

        Random rand = new Random();

        int randomIndex = low + rand.nextInt(high - low + 1);

        int temp = arr[randomIndex];
        arr[randomIndex] = arr[high];
        arr[high] = temp;

        return partition(arr, low, high);
    }

    static void randomizedQuickSort(int arr[], int low, int high) {

        if (low < high) {

            int pi = randomizedPartition(arr, low, high);

            randomizedQuickSort(arr, low, pi - 1);
            randomizedQuickSort(arr, pi + 1, high);
        }
    }

    public static void main(String[] args) {

        int arr1[] = { 45, 20, 10, 60, 30, 80, 50 };
        int arr2[] = Arrays.copyOf(arr1, arr1.length);

        System.out.println("Original Array: " + Arrays.toString(arr1));

        quickSort(arr1, 0, arr1.length - 1);

        System.out.println("Deterministic Quick Sort: "
                + Arrays.toString(arr1));

        randomizedQuickSort(arr2, 0, arr2.length - 1);

        System.out.println("Randomized Quick Sort: "
                + Arrays.toString(arr2));
    }
}