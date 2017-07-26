package course3_week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/*
 *  The goal of this problem is to run clustring algorithm on 
 * 
 * 
 * 
 * 
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
