package course3_week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * 
 * Your task in this problem is to run the dynamic programming algorithm (and the reconstruction procedure) from lecture on this data set. 
 * The question is: of the vertices 1, 2, 3, 4, 17, 117, 517, and 997, which ones belong to the maximum-weight independent set?
 *  (By "vertex 1" we mean the first vertex of the graph---there is no vertex 0.) In the box below, enter a 8-bit string, where the ith bit should be 1 if the ith of these 8 vertices is in the maximum-weight independent set,
 *   and 0 otherwise. For example, if you think that the vertices 1, 4, 17, and 517 are in the maximum-weight independent set and the other four vertices are not, then you should enter the string 10011010 in the box below.
 * 
 * 
 * implementing independence set using dynamic programming ( my first application in dynamic programming)
 * 
 * 
 * answer for this question is  : 10100110

 * 
 * @author sina
 *
 */
public class IndependentSet {

	public static double[] table;

	public static void calculatinIndependentSet(double[] w) {

		table[0] = 0;
		table[1] = w[1];

		for (int i = 2; i < w.length; i++) {
			
			double result = Math.max(table[i - 1], w[i ] + table[i-2]);
			table[i] = result;

		}

	}

	public static ArrayList<Integer> reconstruction(int i, double[] w) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		while (i >= 1) {

			if (table[i - 1] >= table[i]){

				i--;
				
			} else {
				
				s.add(i);
				i -= 2;

			}

		}
		
		return s;

	}

	public static String cunstructingResultString(ArrayList<Integer> s) {

		String r = "";
		if (s.contains(1)) {
			r += "1";
		} else
			r += "0";

		if (s.contains(2)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(3)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(4)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(17)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(117)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(517)) {

			r += "1";
		} else
			r += "0";
		if (s.contains(997)) {

			r += "1";
		} else
			r += "0";
		return r;
	}

	public static void readFromFile(String pathname) throws FileNotFoundException {

		File file = new File(pathname);
		Scanner scan = new Scanner(file);
		int n = scan.nextInt();
		System.out.println(n);
		table = new double[n + 1];
		double[] w = new double[n+1];
		w[0]=0;
		int index = 1;
		while (scan.hasNext()) {
			w[index] = scan.nextDouble();
			index++;

		}

		calculatinIndependentSet(w);
		System.out.println(table[n]);
		System.out.println(cunstructingResultString(reconstruction(n, w)));

	} 


	
	public static void main(String[] args) {
		try {
			readFromFile("mwis.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
