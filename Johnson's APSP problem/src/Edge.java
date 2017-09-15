
public class Edge implements Comparable {
	private Vertex tail; 
	private Vertex head;
	private int length;
	
	
	public Edge(Vertex tail , Vertex head , int length) {
		this.length = length; 
		this.head =head;
		this.tail = tail;
		
	}
	public Edge(Vertex tail , Vertex head ) {
		this.length = 0; 
		this.head =head;
		this.tail = tail;
		
	}	public Vertex getTail() {
		return tail;
	}
	public void setTail(Vertex tail) {
		this.tail = tail;
	}
	public Vertex getHead() {
		return head;
	}
	public void setHead(Vertex head) {
		this.head = head;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	@Override
	public int compareTo(Object o) {
		
		if (  this.length > ((Edge) o).length)
			return 1;
		else if ( this.length == ((Edge) o).length)
			return 0;
		else return -1;
			
	
	}
	
	
	
	
}
