package course3_week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * 
 * 
 * 
 * 
 *  The goal of this problem is to run Clustering algorithm on a graph and print the max spacing value 
 *  when number of disjoint clusters are 4 ( k = 4)
 * 	Text file explanation : 
 *  	[number_of_nodes]
 *  	[edge 1 node 1] [edge 1 node 2] [edge 1 cost]
 *  	[edge 2 node 1] [edge 2 node 2] [edge 2 cost]
 *  	...
 *  
 *  Problem definition: 
 *  	There is one edge (i,j) for each choice of 1≤i<j≤n, where n is the number of nodes.
 * 	    For example, the third line of the file is "1 3 5250", indicating that the distance between nodes 1 and 3 (equivalently, the cost of the edge (1,3)) is 5250. 
 * 		You can assume that distances are positive, but you should NOT assume that they are distinct
 *  	Your task in this problem is to run the clustering algorithm from lecture on this data set,
 *      where the target number k of clusters is set to 4. What is the maximum spacing of a 4-clustering?
 * 
 * 
 * 
 * 		Answer for this question  : 106
 * 
 * 
 * @author sina
 *
 */
public class Clustering {
	public static ArrayList<Edge> edges = new ArrayList<Edge>();
	public static int k = 4;
	public static int n;
	public static int[] parents;
	public static LazyUnionFind uf;

	public static void readFromFile(File file) {
		Scanner scan;
		try {
			scan = new Scanner(file);
			n = scan.nextInt();
			parents = new int[n];
			for (int i = 0; i < n; i++)
				parents[i] = -1;

			while (scan.hasNext()) {
				int i = scan.nextInt() - 1;
				int j = scan.nextInt() - 1;
				int cost = scan.nextInt();
				Edge e = new Edge(i, j, cost);
				edges.add(e);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void Clustring() {

		for (Edge e : edges) {

			uf.union(e.getI(), e.getJ());
			if (uf.countSets == k)
				break;

		}
		int max = Integer.MAX_VALUE;
		for (Edge e : edges) {
			if (uf.find(e.getI()) != uf.find(e.getJ()))
				max = Math.min(max, e.getCost());
		}

		System.out.println("Max-Spacing K-Clustering => " + max);

	}

	public static void main(String[] args) {
		File file = new File("clustering1.txt");
		readFromFile(file);
		Collections.sort(edges);
		uf = new LazyUnionFind(n);
		Clustring();
	}
}
