package course2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;

import java.util.Scanner;

import java.util.Stack;

/**
 * 
 *
 * 
 * The file contains the edges of a directed graph. Vertices are labeled as positive integers from 1 to 875714. Every
 * row indicates an edge, the vertex label in first column is the tail and the vertex label in second column is the head
 * (recall the graph is directed, and the edges are directed from the first column vertex to the second column vertex).
 * So for example, the 11th row looks liks : "2 47646". This just means that the vertex with label 2 has an outgoing
 * edge to the vertex with label 47646
 * 
 * Your task is to code up the algorithm from the video lectures for computing strongly connected components (SCCs), and
 * to run this algorithm on the given graph.
 * 
 * Output Format: You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes,
 * separated by commas (avoid any spaces). So if your algorithm computes the sizes of the five largest SCCs to be 500,
 * 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". If your algorithm finds less than 5 SCCs,
 * then write 0 for the remaining terms. Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100,
 * then your answer should be "400,300,100,0,0".
 * 
 * 	5 biggest components are  :
 * 
 * 	   434821, 968, 459, 313, 211

 * 
 * 
 * @author sina
 *
 */

public class Scc {

	public static int size_Counter = 0;
	public static ArrayList<Integer> size = new ArrayList<Integer>();
	public static Graph1 graph = new Graph1();
	public static int t = 0;
	public static int s = 0;
	public static int max_node = 0;

	public static void makingAdjList() {

		File file = new File("SCC_Problem.txt");
		try {
			Scanner scan = new Scanner(file);

			while (scan.hasNext()) {
				int key = scan.nextInt();
				max_node = Math.max(key, max_node);
				// System.out.println("max is" + max_node);
				int val = scan.nextInt();
				// System.out.println(key);
				graph.addNode(key, val);

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Function that returns reverse (or transpose) of this graph
	public static Graph1 getTranspose() {

		Graph1 g = new Graph1();
		// Recur for all the vertices adjacent to this vertex

		for (int i = 1; i <= graph.nodes.size(); i++) {
			if (!g.nodes.containsKey(i))
				g.nodes.put(i, new ArrayList<Integer>());
			ArrayList<Integer> temp = graph.nodes.get(i);
			for (int j = 0; j < temp.size(); j++) {
				g.addNode(temp.get(j), i);
			}

		}
		g.fn = new Integer[graph.nodes.size()];
		return g;
	}

	public static void settingFinishingTime(int v, boolean visited[], Stack stack) {

	}

	public static void printScc() {
		boolean visited[] = new boolean[graph.nodes.size()];
		Graph1 gRev = getTranspose();

		for (int v = max_node; v >= 1; v--) {
			Stack<Integer> l = new Stack<Integer>();
			if (visited[v - 1] == false) {
				l = DFSUtil(v - 1, visited, gRev, l);
				while (!l.isEmpty()) {
					t++;
					gRev.fn[l.pop()] = t;

				}

			}

		}

		max_node = t;

		graph.fn = gRev.fn;
		HashMap<Integer, ArrayList<Integer>> temp = new HashMap<Integer, ArrayList<Integer>>();
		visited = new boolean[graph.nodes.size()];
		// System.out.println(graph);
		for (int i = 1; i < graph.nodes.size(); i++) {
			if (!graph.nodes.containsKey(i))
				graph.nodes.put(i, new ArrayList<Integer>());
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int j = 0; j < graph.nodes.get(i).size(); j++) {
				arr.add(graph.fn[graph.nodes.get(i).get(j) - 1]);
			}
			temp.put(graph.fn[i - 1], arr);

		}
		graph.nodes = temp;
		for (int v = max_node; v >= 1; v--) {
			int counter = 0;
			Stack<Integer> s = new Stack<Integer>();
			if (visited[v - 1] == false) {
				s = dfs(v - 1, visited, graph, s);

				counter = s.size();

				size.add(counter);
			}

		}
		Collections.sort(size);
		for (int i = size.size() - 1; i > size.size() - 6; i--)
			System.out.println(size.get(i));

	}

	public static Stack DFSUtil(int v, boolean visited[], Graph1 g, Stack t) {
		Stack<Integer> s = new Stack<Integer>();
		s.push(v);
		while (!s.isEmpty()) {
			v = s.pop();

			if (!visited[v]) {
				visited[v] = true;
				t.push(v);

				for (int i = 0; i < g.nodes.get(v + 1).size(); i++) {
					if (!visited[g.nodes.get(v + 1).get(i) - 1]) {
						s.push(g.nodes.get(v + 1).get(i) - 1);
					}
				}
			}
		}

		return t;
	}

	public static Stack dfs(int v, boolean visited[], Graph1 g, Stack t) {

		Stack<Integer> s = new Stack<Integer>();
		s.push(v);
		while (!s.isEmpty()) {
			v = s.pop();
			if (!visited[v]) {
				visited[v] = true;
				t.push(v);

				if (!g.nodes.containsKey(v + 1))
					g.nodes.put(v + 1, new ArrayList<Integer>());
				for (int i = 0; i < g.nodes.get(v + 1).size(); i++) {

					if (!visited[g.nodes.get(v + 1).get(i) - 1])
						s.push(g.nodes.get(v + 1).get(i) - 1);
				}
			}

		}
		// System.out.println("t is \t" + t);
		return t;
	}

	public static void main(String[] args) {
		new Scc();
		makingAdjList();
		graph.fn = new Integer[graph.nodes.size()];

		printScc();
	}
}

class Graph1 {

	public HashMap<Integer, ArrayList<Integer>> nodes = new HashMap<Integer, ArrayList<Integer>>();
	public Integer[] fn;

	public void addNode(int key, int value) {

		int counter = nodes.size() + 1;
		while (counter < key) {
			nodes.put(counter, new ArrayList<Integer>());
			counter++;
		}

		if (nodes.containsKey(key)) {
			ArrayList<Integer> temp = nodes.get(key);
			temp.add(value);
			nodes.put(key, temp);
		} else {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(value);
			nodes.put(key, temp);
		}

	}


}
