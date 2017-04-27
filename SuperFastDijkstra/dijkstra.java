package week2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


/**
 * 
 * Input format:
 * The file contains an adjacency list representation of an undirected weighted 
 * graph with 200 vertices labeled 1 to 200. Each row consists of the node 
 * tuples that are adjacent to that particular vertex along with the length of 
 * that edge. For example, the 6th row has 6 as the first entry indicating that 
 * this row corresponds to the vertex labeled 6. The next entry of this row 
 * "141,8200" indicates that there is an edge between vertex 6 and vertex 141 
 * that has length 8200. The rest of the pairs of this row indicate the other 
 * vertices adjacent to vertex 6 and the lengths of the corresponding edges.
 * 
 * Task:
 * Run Dijkstra's shortest-path algorithm on this graph, using 1 (the first 
 * vertex) as the source vertex, and to compute the shortest-path distances 
 * between 1 and every other vertex of the graph. If there is no path between a 
 * vertex v and vertex 1, we'll define the shortest-path distance between 1 and 
 * v to be 1000000.
 * 
 * Output format:
 * Report the shortest-path distances to the following ten vertices, in order: 
 * 7,37,59,82,99,115,133,165,188,197. You should encode the distances as a 
 * comma-separated string of integers. So if you find that all ten of these 
 * vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 
 * distance away, then your answer should be 1000,1000,1000,1000,1000,2000,1000,
 * 1000,1000,1000. 
 * 
 * 
 * 
 * answer for this question is : 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
 * 
 * 
 * 
 * using minheap ( priority queue)  and the time Complexity is therefore O ( mLog(n) )
 * only keep the nodes that are not visited yet in the priority queue
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author sina
 * 
 *
 */


public class dijkstra {
	
	private  Graph graph = new Graph();
	private ArrayList<Node> visited; 
	private PriorityQueue<Edge> crossingEdges = new PriorityQueue<Edge>();  // edges with one node explored, another unexplored 

	
	public void readingGraphFromFile(){
		File file = new File("Dijkstra_Data.txt");
		try {
			Scanner scan = new Scanner(file);
				// reading line by line
			while( scan.hasNextLine()){
				
				
				
				String line = scan.nextLine();
				
					// parsing the line 
//				System.out.println(line);
				String [] alldetails = line.split("[,\\s+]");
				int number = Integer.parseInt(alldetails[0]);
				Node node = new Node(number);
				for ( int i =1 ; i< alldetails.length ; i++){
					Edge edge = new Edge(number, Integer.parseInt(alldetails[i]), Integer.parseInt(alldetails[i+1]));
					i++;
					node.adjEdges.add(edge);
					
				}
				graph.nodes.add(node);	
			}
			
			visited = new ArrayList<Node>(graph.nodes.size());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/*
	 * 
	 *  
	 * dijkstra algorith which returns shortest path to all other nodes form s and gets s as source node
	 *  
	 * 
	 *  	
	 */
	public int [] dijkstraAlgorithm (Node s){
		
		int [ ] shortestPaths = new int [ this.graph.nodes.size()];
		
		for (int i =0 ; i < shortestPaths.length ; i++)
			shortestPaths[i] = Integer.MAX_VALUE;
		for (int i =0 ; i< s.adjEdges.size() ; i++)
			crossingEdges.add(s.adjEdges.get(i));
		// counter for the main loop of the algorithm which should we iterate n - 1 where n is the number of the nodes in the graph
		int i =0;
		visited.add(0,s);
		
//		if ( s.adjEdges == null || s.adjEdges.size() == 0)
			 
			// The main loop of the algorithm O(n)
		while (i < graph.nodes.size()){
			
			Edge tempEdge = crossingEdges.poll();
//			while (visited.contains(tempEdge)){
//				tempEdge = crossingEdges.poll();
			while (visited.contains( graph.nodes.get(tempEdge.getDest() -1)    )) {
				tempEdge = crossingEdges.poll();
				if (tempEdge== null) {
					break;
				}
//				
			}
			
			if (tempEdge == null){
					// there is no more edges to visit so the algorithm is over and we should break the loop
				for (int j = 0; j < graph.nodes.size(); j++) {
					if ( ! visited.contains(graph.nodes.get(i))  )
						shortestPaths[j] = 1000000;
				}
				
				break;
			}
			
			visited.add(graph.nodes.get(tempEdge.getDest() - 1));
			
			shortestPaths[tempEdge.getDest()- 1] =tempEdge.getWeight();
			for (Edge edge : graph.nodes.get(tempEdge.getDest() - 1).adjEdges ) {
				
				if( !visited.contains(edge.getDest())){
					
					edge.setWeight(edge.getWeight() + tempEdge.getWeight());
					crossingEdges.add(edge );
				}
				
			}
			i++;
		}
		
		
		
		
		return shortestPaths;
	}
	
	
	public static void main(String[] args) {
		dijkstra d = new dijkstra();
		d.readingGraphFromFile();
		int []result = d.dijkstraAlgorithm(d.graph.nodes.get(0));
		for ( int i =0 ; i< result.length ; i ++ )
			if (i==6 || i==36 || i == 58 || i == 81 || i == 98 || i == 114 || i ==132 || i == 164 || i == 187 || i == 196)
			System.out.print(result[i]+",");
	}
	
	
//	

}
