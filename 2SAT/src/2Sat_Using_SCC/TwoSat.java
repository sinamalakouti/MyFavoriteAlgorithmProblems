
/*
 *In this assignment you will implement one or more algorithms for the 2SAT problem. 
 * Here are 6 different 2SAT instances: #1 #2 #3 #4 #5 #6.
 * 
 * The file format is as follows. In each instance, the number of variables and the
 * number of clauses is the same, and this number is specified on the first line of 
 * the file. Each subsequent line specifies a clause via its two literals, with a 
 * number denoting the variable and a "-" sign denoting logical "not". For example,
 * the second line of the first data file is "-16808 75250", which indicates the clause
 * ¬x16808∨x75250.
 * 
 * Your task is to determine which of the 6 instances are satisfiable, and which are
 * unsatisfiable. In the box below, enter a 6-bit string, where the ith bit should be
 * 1 if the ith instance is satisfiable, and 0 otherwise. For example, if you think that
 * the first 3 instances are satisfiable and the last 3 are not, then you should enter 
 * the string 111000 in the box below.
 * 
 * DISCUSSION: This assignment is deliberately open-ended, and you can implement 
 * whichever 2SAT algorithm you want. For example, 2SAT reduces to computing the
 * strongly connected components of a suitable graph (with two vertices per variable
 * and two directed edges per clause, you should think through the details). This might
 * be an especially attractive option for those of you who coded up an SCC algorithm
 * for Part I of this course. Alternatively, you can use Papadimitriou's randomized 
 * local search algorithm. (The algorithm from lecture might be too slow, so you might
 * want to make one or more simple modifications to it to ensure that it runs in a 
 * reasonable amount of time.) A third approach is via backtracking. In lecture we 
 * mentioned this approach only in passing; see the DPV book, for example, for more 
 * details.
 *                   ANSWER : 101100
 */


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
