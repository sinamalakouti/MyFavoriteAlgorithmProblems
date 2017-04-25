import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class SCC {
	
 	public static ArrayList<ArrayList<Integer>> adjencyList = new  ArrayList<ArrayList<Integer>>();
	
 	public static void makingAdjList(){
 		
 		File file = new File("/Users/sina/Desktop/coursera.rtf");
 		try {
			Scanner scan = new Scanner(file);
			
			while( scan.hasNext()){
				
				String n = scan.nextLine();
				if( adjencyList.size() >= n.length()){
					// this node is already exist in the arrayList
					
					adjencyList.get(n.length()-1).add(scan.nextInt()-1);
					
				}else {
				
					adjencyList.add(new ArrayList<Integer>());
					adjencyList.get(n.length() -1).add(scan.nextInt()-1);
					
				}
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
 		
 	}
 	
 	
 	public static void main(String[] args) {
		makingAdjList();
		System.out.println(adjencyList);
	}
	
	

}
