package course3_week2;

class Edge implements Comparable<Edge>
{
    private int i;
    private int j;
    private int cost;
		
    public Edge(int i, int j, int cost)
    {
	this.i = i;
	this.j = j;
	this.cost = cost;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge edge) 
    {
        int result;
        
        if(this.getCost() >= edge.getCost()){
            result = 1;
        }
        else{
            result = -1;
        }
        
        return result;
    }
}