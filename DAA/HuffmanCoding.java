/*
Problem Statement:
Write a Java program to implement Huffman Encoding
using the Greedy Algorithm.

Given a set of characters and their frequencies,
construct a Huffman Tree and generate unique
variable-length binary codes for each character.

Characters with higher frequencies should generally
receive shorter binary codes.

Example:
Characters = A B C D E F
Frequencies = 5 9 12 13 16 45

Output:
Generate Huffman binary codes for each character.
*/

package DAA;

import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    char ch;
    int freq;

    Node left, right;

    Node(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
        left = right = null;
    }
}

public class HuffmanCoding {

    static void printCodes(Node root, String code) {

        if (root == null)
            return;

        // Leaf node means character found
        if (root.left == null && root.right == null) {
            System.out.println(root.ch + " : " + code);
            return;
        }

        // Left = 0, Right = 1
        printCodes(root.left, code + "0");
        printCodes(root.right, code + "1");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of characters: ");
        int n = sc.nextInt();

        PriorityQueue<Node> pq =
                new PriorityQueue<>((a, b) -> a.freq - b.freq);

        // Input characters and frequencies
        for (int i = 0; i < n; i++) {

            System.out.print("Enter character: ");
            char ch = sc.next().charAt(0);

            System.out.print("Enter frequency: ");
            int freq = sc.nextInt();

            pq.add(new Node(ch, freq));
        }

        // Build Huffman Tree
        while (pq.size() > 1) {

            Node left = pq.poll();
            Node right = pq.poll();

            Node parent = new Node(
                    '-',
                    left.freq + right.freq
            );

            parent.left = left;
            parent.right = right;

            pq.add(parent);
        }

        // Print Huffman Codes
        Node root = pq.poll();

        System.out.println("\nHuffman Codes:");

        printCodes(root, "");
        
        sc.close();
    }
}