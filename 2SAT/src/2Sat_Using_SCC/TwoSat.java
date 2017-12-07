package two_sat;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TwoSat {

	public static boolean isSatisfiable(ArrayList<Clause> formula) {
		boolean isSatisfiable = true;
		// all variables in the input formula ( first v second )
		Set<Integer> variables = new HashSet<Integer>();
		// adding all variables in the formula if they are not exist

		for (Clause clause : formula) {

			variables.add(clause.getFirst().getIndex());
			variables.add(clause.getSecond().getIndex());
		}
		// construct a graph of variables and their negations

		Graph graph = new Graph();

		for (Integer variable : variables) {
			graph.addNode(new Literal(variable, true));
			graph.addNode(new Literal(variable, false));
		}

		/*
		 * 
		 * for each Clause ( A or B ) adding to edges : ( ~A -> B ) and ( ~B ->
		 * A)
		 * 
		 */

		for (Clause clause : formula) {
			graph.addEdge(clause.getFirst().negation(), clause.getSecond());
			graph.addEdge(clause.getSecond().negation(), clause.getFirst());
		}

		// computing StronglyConnectedComponent of the graph

		Map<Literal, Integer> varSets = Scc.StronglyConnectedComponent(graph);

		for (Integer var : variables) {

			if (varSets.get(new Literal(var, true)).equals(varSets.get(new Literal(var, false)))) {

				return false;
			}
		}

		return isSatisfiable;

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException {

		Scanner scan = new Scanner(new File("2sat3.txt"));
		scan.nextInt();
		ArrayList<Clause> formula = new ArrayList<Clause>();
		while (scan.hasNext()) {

			int first = scan.nextInt();
			int second = scan.nextInt();

			Clause caluse = new Clause(new Literal(Math.abs(first), first > 0),
					new Literal(Math.abs(second), second > 0));
			formula.add(caluse);
		}

		System.out.println(isSatisfiable(formula));

	}

}
