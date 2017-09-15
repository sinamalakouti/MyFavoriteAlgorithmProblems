
import java.util.ArrayList;

public class Vertex {

	private int name;
	private ArrayList<Edge> adjEdges = new ArrayList<Edge>() ;
	private boolean visited;
	private int pValue = 0;
	
	public Vertex(int name) {
		this.name = name;
		this.visited = false;
	}
	
	public int getName() {
		return name;
	}
	public void setName(int name) {
		this.name = name;
	}
	public ArrayList<Edge> getAdjEdges() {
		return adjEdges;
	}
	public void setAdjEdges(ArrayList<Edge> adjEdges) {
		this.adjEdges = adjEdges;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getpValue() {
		return pValue;
	}
	public void setpValue(int pValue) {
		this.pValue = pValue;
	}
	
//	public double getLength( int v){
//		System.out.println("here heree");
//		for  ( int i = 0 ; i < adjEdges.size() ; i ++){
//			if ( adjEdges.get(i).getHead().getName() == v)
//				return adjEdges.get(i).getLength();
//			
//		}
//		
//		return 0;
//	}

}
