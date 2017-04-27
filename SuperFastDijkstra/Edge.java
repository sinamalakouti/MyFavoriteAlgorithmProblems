package week2;

public class Edge implements Comparable<Edge> {
	private int source ; 
	private int dest;
	private int weight;
	
	public Edge (int source, int dest , int weight){
		this.source = source;
		this.dest= dest;
		this.weight = weight;	
	}
	
	public int getSource (){
		return source;
	}
	
	public int getDest(){
		return dest;
		
	}
	public int getWeight(){
		return weight;
	}
	public void setSource(int src){
		this.source = src;
	}
	public void setDest(int dest){
		this.dest = dest;
	}
	public void setWeight(int weight){
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
			return weight - o.getWeight();
	}
}
