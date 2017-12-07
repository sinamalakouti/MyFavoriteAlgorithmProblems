package two_sat;


public class Clause {
    private final Literal first; // The two literals this clause is made of.
    private final Literal second;
    
    
	public Clause(Literal one, Literal two) {
        first = one;
        second = two;
    }

    

	public Literal getFirst() {
        return first;
    }

    public Literal getSecond() {
        return second ;
    }

    @Override
    public String toString() {
        return "(" + first + " or " + second + ")";
    }
}
