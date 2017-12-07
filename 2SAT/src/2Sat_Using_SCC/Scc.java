package two_sat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Scc {

	public static <T> Map<Literal, Integer> StronglyConnectedComponent(Graph g) {

		Map<Literal, Integer> result = new HashMap<Literal, Integer>();

		// calculate finishing time
		Stack<Literal> l = visitOrder(getReverseGraph(g));

		int sccCounter = 0;
		while (!l.isEmpty()) {

			Literal start = (Literal) l.pop();

			if (result.containsKey(start))
				continue;

			findSccLiterals(start, g, result, sccCounter);

			++sccCounter;
		}

		return result;

	}

	private static void findSccLiterals(Literal node, Graph g, Map<Literal, Integer> result, int label) {

		if (result.containsKey(node))
			return;

		result.put(node, label);

		for (Literal endNode : g.edgesFrom(node)) {
			findSccLiterals(endNode, g, result, label);
		}
	}

	private static Stack<Literal> visitOrder(Graph g) {

		Stack<Literal> result = new Stack<Literal>();

		Set<Literal> visited = new HashSet<Literal>();

		for (Literal node : g.nodes.keySet())
			dfsUtils(node, g, visited, result);

		return result;

	}

	private static void dfsUtils(Literal node, Graph g, Set<Literal> visited, Stack<Literal> result) {

		if (visited.contains(node))
			return;

		visited.add(node);

		for (Literal endpoint : g.edgesFrom(node))
			dfsUtils(endpoint, g, visited, result);

		result.push(node);

	}

	private static Graph getReverseGraph(Graph g) {
		Graph gRev = new Graph();

		// adding all nodes to the reversed graph
		for (Literal node : g.nodes.keySet())
			gRev.addNode(node);

		for (Literal source : g.nodes.keySet()) {
			for (Literal dist : g.edgesFrom(source)) {

				gRev.addEdge(dist, source);

			}

		}

		return gRev;

	}
}
