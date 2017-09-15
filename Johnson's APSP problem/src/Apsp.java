/**
 * 
 * 
 * In this assignment you will implement one or more algorithms for the all-pairs shortest-path problem. Here are data files describing three graphs:
 *
 *g1.txt
 *g2.txt
 *g3.txt
 *
 *
 *The first line indicates the number of vertices and edges, respectively. Each subsequent line describes an edge (the first two numbers are its tail and head, respectively) and its length (the third number). NOTE: some of the edge lengths are negative. NOTE: These graphs may or may not have negative-cost cycles.
 *
 *Your task is to compute the shortest shortest path. Precisely, you must first identify which, if any, of the three graphs have no negative cycles. For each such graph, you should compute all-pairs shortest paths and remember the smallest one (i.e., compute minu,v∈Vd(u,v), where d(u,v) denotes the shortest-path distance from u to v ).
 *
 *If each of the three graphs has a negative-cost cycle, then enter "NULL" in the box below. If exactly one graph has no negative-cost cycles, then enter the length of its shortest shortest path in the box below. If two or more of the graphs have no negative-cost cycles, then enter the smallest of the lengths of their shortest shortest paths in the box below.
 *
 *
 * 
 * 
 *
 **********  g1.txt : negative cycle  
 **********  g2.txt : negative cycle 
 **********  g3.txt : -19
 **********  Final Answer : -19
 * 
 * 
 * @author SINA
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import Edge;
import Vertex;

public class Apsp {
	// ArrayList<Vertex> graph = new ArrayList<Vertex>();
	public static PriorityQueue<Edge> crossingEdges = new PriorityQueue<Edge>();
	public static Vertex[] graph;
	public static ArrayList<Edge> edges = new ArrayList<Edge>();

	public static void readFromFile() {

		String pathname = new String("g3.txt");
		File file = new File(pathname);
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		graph = new Vertex[scan.nextInt() + 1];

		while (scan.hasNext()) {

			int tail = scan.nextInt();
			int head = scan.nextInt();
			int length = scan.nextInt();

			Vertex headV;
			Vertex tailV;

			if (graph[head] == null) {
				headV = new Vertex(head);
			} else
				headV = graph[head];

			if (graph[tail] == null)
				tailV = new Vertex(tail);
			else
				tailV = graph[tail];
			graph[head] = headV;
			graph[tail] = tailV;

			Edge e = new Edge(tailV, headV, length);
			tailV.getAdjEdges().add(e);
			edges.add(e);

		}

	}

	public static void johnsons() {

		// step1 :
		// first add a new vertex S to the graph
		Vertex s = new Vertex(0);
		graph[0] = s;
		// set a edge from s to any other vertexes with value 0
		for (int i = 1; i < graph.length; i++) {
			Edge e = new Edge(s, graph[i]);
			s.getAdjEdges().add(e);
			edges.add(e);
		}
		// step2 :

		// running bellmen ford algorithm on vertex 0
		bellmenFord(s, graph.length, edges.size());

		// setting all vertex's P-value

		for (int i = 0; i < s.getAdjEdges().size(); i++) {

			s.getAdjEdges().get(i).getHead().setpValue(s.getAdjEdges().get(i).getLength());

		}

		// re weighting all the edges c'(e) = c(e) + p(u) - p(v)

		for (int v = 1; v < graph.length; v++) {

			for (int e = 0; e < graph[v].getAdjEdges().size(); e++) {
				graph[v].getAdjEdges().get(e).setLength(graph[v].getAdjEdges().get(e).getLength() + graph[v].getpValue()
						- graph[v].getAdjEdges().get(e).getHead().getpValue());
			}
		}
		// running Dijkstra on all n nodes to get all pair shortest path ( n *
		// Dijkstra )

		int[][] table = new int[graph.length][graph.length];

		for (int i = 1; i < graph.length; i++) {

			Vertex[] graphCopy = makingCopyOfEverything(graph);

			int[] shortestPath = new int[graphCopy.length];

			shortestPath = Dijkstra(graphCopy[i], graphCopy);

			for (int v = 0; v < graph.length; v++) {
				graphCopy[v].setVisited(false);
			}
			table[i] = shortestPath;

		}

		// re weighting all edges again

		for (int i = 1; i < table.length; i++) {
			for (int j = 1; j < table.length; j++) {
				if (table[i][j] != Integer.MAX_VALUE)
					table[i][j] = table[i][j] - graph[i].getpValue() + graph[j].getpValue();

			}
		}

		// finding shortest value among all shortest paths

		int best = Integer.MAX_VALUE;
		int head = 0;
		int tail = 0;
		for (int i = 1; i < table.length; i++)
			for (int j = 1; j < table.length; j++) {

				if (best > table[i][j]) {
					best = table[i][j];
					head = j;
					tail = i;
				}

			}

		// result of the question
		System.out.println("best is \t" + best);

	}
	// making copie of a graph and all its properties

	public static Vertex[] makingCopyOfEverything(Vertex g[]) {
		Vertex[] temp = new Vertex[g.length];

		for (int i = 0; i < g.length; i++) {
			Vertex v = new Vertex(g[i].getName());
			v.setpValue(g[i].getpValue());
			v.setVisited(false);
			ArrayList<Edge> tempedges = new ArrayList<Edge>();
			for (int j = 0; j < g[i].getAdjEdges().size(); j++) {
				g[i].getAdjEdges().get(j).getTail().setVisited(false);
				g[i].getAdjEdges().get(j).getHead().setVisited(false);
				Edge e = new Edge(g[i].getAdjEdges().get(j).getTail(), g[i].getAdjEdges().get(j).getHead());
				e.setLength(g[i].getAdjEdges().get(j).getLength());
				tempedges.add(e);
			}
			v.setAdjEdges(tempedges);
			temp[i] = v;

		}
		return temp;
	}

	// bellmen ford algorithm
	public static void bellmenFord(Vertex s, int V, int E) {

		int[] dist = new int[V];
		for (int i = 0; i < V; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[s.getName()] = 0;

		for (int i = 1; i < V; i++) {
			for (int j = 0; j < E; j++) {

				Vertex tail = edges.get(j).getTail();
				Vertex head = edges.get(j).getHead();
				int weight = edges.get(j).getLength();

				if (dist[tail.getName()] != Integer.MAX_VALUE && dist[tail.getName()] + weight < dist[head.getName()]) {

					dist[head.getName()] = dist[tail.getName()] + weight;

				}

			}
		}

		// detecting negative cycle

		for (int j = 0; j < E; ++j) {
			int u = edges.get(j).getTail().getName();
			int v = edges.get(j).getHead().getName();
			int weight = edges.get(j).getLength();
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {

				System.out.println("Graph contains negative weight cycle");
				System.exit(0);
			}
		}
		// setting graph's edges values
		for (int i = 0; i < s.getAdjEdges().size(); i++) {
			s.getAdjEdges().get(i).setLength(dist[s.getAdjEdges().get(i).getHead().getName()]);

		}

	}

	// Dijkstra algorithm
	public static int[] Dijkstra(Vertex s, Vertex[] g) {

		s = g[s.getName()];
		s.setVisited(true);

		int[] shortestPaths = new int[g.length];

		for (int i = 0; i < shortestPaths.length; i++)
			shortestPaths[i] = Integer.MAX_VALUE;

		crossingEdges = new PriorityQueue<Edge>();
		for (int i = 0; i < s.getAdjEdges().size(); i++) {
			if (s.getAdjEdges().get(i).getLength() < 0) {
				System.err.println("Negative Edge is not allowed!!");
				System.exit(0);
			}
			crossingEdges.add(s.getAdjEdges().get(i));

		}

		int i = 1;
		while (i < g.length && crossingEdges.size() != 0) {

			Edge temp = crossingEdges.poll();
			while (temp.getHead().isVisited() == true) {

				temp = crossingEdges.poll();
				if (temp == null)
					break;
			}

			if (temp == null) {
				System.err.println("Not Good !!");
				break;
			}

			temp.getHead().setVisited(true);
			shortestPaths[temp.getHead().getName()] = temp.getLength();
			for (Edge edge : g[temp.getHead().getName()].getAdjEdges()) {
				if (edge.getHead().isVisited() == false) {
					edge.setLength(edge.getLength() + temp.getLength());
					crossingEdges.add(edge);
				}

			}

			i++;
		}

		return shortestPaths;
	}

	public static void main(String[] args) {
		readFromFile();
		johnsons();
	}
}