package course3_week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * PROBLEM DEFINITION : In this programming problem and the next you'll code up
 * the greedy algorithm from the lectures on Huffman coding. This file describes
 * an instance of the problem. It has the following format: [number_of_symbols]
 * [weight of symbol #1] [weight of symbol #2] For example, the third line of
 * the file is "6852892," indicating that the weight of the second symbol of the
 * alphabet is 6852892. (We're using weights instead of frequencies, like in the
 * "A More Complex Example" video.) ... Your task in this problem is to run the
 * Huffman coding algorithm from lecture on this data set. What is the maximum
 * length of a codeword in the resulting Huffman code?
 * 
 * I implement Huffman code using min heap
 * 
 * 
 * the answer for the maximum length code word is : 19 the answer for the
 * minimum length code word is : 9
 * 
 * @author sina
 *
 */

public class HauffmanCode {

	public static PriorityQueue<Node> symboles = new PriorityQueue<Node>();

	public static void hauffmanAlgorithm() {

		while (symboles.size() > 1) {

			// finding the 2 lightest nodes and removing them from the heap
			Node firstNode = symboles.poll();
			Node secondNode = symboles.poll();
			// merging 2 lightest nodes 
			Double newWeight = firstNode.weight + secondNode.weight;
			Double newMaxNmberOfMerges = Math.max(firstNode.maxNumberOfMerges, secondNode.maxNumberOfMerges) + 1;
			Double newMinNumberOfmerges = Math.min(firstNode.minNumberOfMerges, secondNode.minNumberOfMerges) + 1;

			// adding merged node to the heap 
			symboles.add(new Node(newWeight, newMaxNmberOfMerges, newMinNumberOfmerges));

		}

	}

	public static double findingMaxLengthCodeWord() {

		return symboles.peek().maxNumberOfMerges;
	}

	public static double findingMinLenghtCodeWord() {
		return symboles.peek().minNumberOfMerges;
	}

	public static void inputFromFile(String pathname) throws FileNotFoundException {
		File file = new File(pathname);
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		scan.nextLine();
		while (scan.hasNext()) {
			symboles.add(new Node(scan.nextDouble()));
		}

	}

	public static void main(String[] args) {
		try {
			inputFromFile("huffman.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		hauffmanAlgorithm();
		System.out.println(findingMaxLengthCodeWord());
		System.out.println(findingMinLenghtCodeWord());
	}

}
