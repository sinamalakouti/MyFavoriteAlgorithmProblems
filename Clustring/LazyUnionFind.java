package course3_week2;

public class LazyUnionFind {
	
	int [] parent ; 
	int n; 
	int [] rank; 
	int countSets; 
	public LazyUnionFind(int n) {
		this.n = n;
		countSets = n;
		this.parent = new int [n];
		this.rank = new int [n];
		for ( int i =0  ; i < n ;  i++){
			parent [i] = i;
			rank[i] =0 ;
		}
	}


	   public int getN(){
	        return n;
	    }
	   public int  getDisjointSets (){
		   return countSets;
	   }
	   
	   // Return component identifier for component containing p
	    public int find(int p)
	    {
	        int root = p;
	        while(root != parent[root]){
	            root = parent[root];
	        }
	        while (p != root) {
	            int newp = parent[p];
	            parent[p] = root;
	            p = newp;
	        }
	        return root;
	    }
	    // Replace sets containing p and q with their union.
	    public void union(int p, int q) 
	    {
	        int i = find(p);
	        int j = find(q);
	        if (i == j){
	            return;
	        }
	        if ( rank[i]   >  rank[j]){
	        	parent[j] = i;
	        }else
	        	parent[i] = parent[j];
	        countSets--;
	    }
	


}
