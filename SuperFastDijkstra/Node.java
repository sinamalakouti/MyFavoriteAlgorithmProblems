package week2;

import java.util.ArrayList;

public class Node {
	
	private int number;
	public  ArrayList<Edge> adjEdges = new ArrayList<Edge>();
	public Node (int number){
		this.number  = number;
		
	}
	public int getnumber ( ){
		return this.number;
	}
	
	public void setNumber(int number){
		this.number = number;
	}
	

}
