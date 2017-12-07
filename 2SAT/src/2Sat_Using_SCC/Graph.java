package two_sat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Graph {

	public HashMap<Literal, Set<Literal>> nodes = new HashMap<Literal, Set<Literal>>();
	public Integer[] finishing_time;

	public boolean addNode(Literal node) {

		if (nodes.containsKey(node))
			return false;

		nodes.put(node, new HashSet<Literal>());
		return true;
	}

	public void addEdge(Literal start, Literal dest) {

		if (!nodes.containsKey(start) || !nodes.containsKey(dest)) {

			throw new NoSuchElementException("ERROR");

		}

		nodes.get(start).add(dest);
	}

	public Set<Literal> edgesFrom(Literal node) {

		Set<Literal> list = nodes.get(node);
		return list;
	}

	public Iterator<Literal> iterator() {
		return nodes.keySet().iterator();
	}
}
