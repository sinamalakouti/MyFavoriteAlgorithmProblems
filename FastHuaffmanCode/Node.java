package course3_week3;

public class Node implements Comparable<Node> {
	public Double weight;
	public double maxNumberOfMerges  ;
	public double minNumberOfMerges  ;
	public Node(  Double weight) {
		this.weight = weight;
		this.maxNumberOfMerges = 0;
		this.minNumberOfMerges = 0;
	}
	public Node(   Double weight, double maxnumberOfMergesm , double minumberOfMerges) {
		this.weight = weight;
		this.maxNumberOfMerges = maxnumberOfMergesm;
		this.minNumberOfMerges = minumberOfMerges;
	}
	@Override
	public int compareTo(Node o) {
		// 
		return weight.compareTo(o.weight);
	} 

}
