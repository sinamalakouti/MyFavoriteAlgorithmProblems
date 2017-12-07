package course3_week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/*
 *  The goal of this problem is to run clustring algorithm on 
 * 
 * 	In this problem we calculate number of disjoint clusters 
 *	when the maximum spacing is more than 3 
 * 	
 *   
 *
 *  The file format is:
 *
 * [# of nodes] [# of bits for each node's label]
 *
 * [first bit of node 1] ... [last bit of node 1]
 *
 * [first bit of node 2] ... [last bit of node 2]
 *
 * ...
 *
 * For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1" denotes the 24 bits associated with node #2.
 *
 * The distance between two nodes u and v in this problem is defined as the Hamming distance--- the number of differing bits --- between the two nodes' labels. For example, the Hamming distance between the 24-bit label of node #2 above and the label "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ in the 3rd, 7th, and 21st bits).
 *
 * The question is: 
 * what is the largest value of k such that there is a k-clustering with spacing at least 3? That is, how many clusters are needed to ensure that no pair of nodes with all but 2 bits in common get split into different clusters?
 *
 *
 *  	answer : 6118
 * 
 * 
 */



public class Clustering2 {

	public static int numberOfNodes;
	public static int numberOfBits;
	public static String[] nodesArray;

	public static void readFromFile(File file) throws FileNotFoundException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(file);
		numberOfNodes = scan.nextInt();
		numberOfBits = scan.nextInt();
		nodesArray = new String[numberOfNodes];
		int index = 0;
		scan.nextLine();
		while (scan.hasNextLine()) {
			String str = scan.nextLine();
			str = str.replaceAll("\\s+", "");
			nodesArray[index] = str;

			index++;
		}
	}

	public static void main(String[] args) {
		File file = new File("clustering_big.txt");
		try {
			readFromFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		HashMap<String, Integer> nodes = new HashMap<String, Integer>();
		int counter = 0;
		int neg = 0;
		for (int i = 0; i < nodesArray.length; i++) {
			if (nodes.containsKey(nodesArray[i])) {
				counter = nodes.get(nodesArray[i]);
				neg++;
			}

			nodes.put(nodesArray[i], counter);
			counter = i - neg;
			counter++;
		}

		numberOfNodes = nodes.size();
		LazyUnionFind uf = new LazyUnionFind(numberOfNodes);
		for (String key : nodes.keySet()) {
			String[] closeNodes = getCloseNodes(key);

			for (int j = 0; j < closeNodes.length; j++) {
				String k = closeNodes[j];

				if (nodes.containsKey(k)) {
					uf.union(nodes.get(key), nodes.get(k));
				}
			}
		}
		System.out.println("Number of Clusters  :  " + uf.countSets);

	}

	private static String[] getCloseNodes(String node) {

		int[] nodeBinary = new int[numberOfBits];

		for (int i = 0; i < node.length(); i++) {

			nodeBinary[i] = Integer.parseInt(node.charAt(i) + "");
		}

		String[] output = new String[numberOfBits + (numberOfBits * (numberOfBits - 1)) / 2];

		// Make 1 and 2 bit different
		int count = 0;

		for (int i = 0; i < numberOfBits; i++) {
			for (int j = i; j < numberOfBits; j++) {
				int[] newNodeBinary = nodeBinary.clone();

				if (i != j) {
					newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
					newNodeBinary[j] = (nodeBinary[j] + 1) % 2;
				} else {
					newNodeBinary[i] = (nodeBinary[i] + 1) % 2;
				}

				// Convert to String and add to output
				String sb = "";
				for (int f = 0; f < numberOfBits; f++) {

					sb += newNodeBinary[f];

				}

				output[count] = sb;
				count++;
			}
		}

		return output;
	}

}
