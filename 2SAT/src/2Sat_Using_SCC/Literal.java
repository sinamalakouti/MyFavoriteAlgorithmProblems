package two_sat;

public class Literal {
	private final Integer index; // The variable in question
	private final boolean mIsPositive; // Whether this is X (true) or ~X (false)

	public Literal(int value, boolean isPositive) {

		index = value;
		mIsPositive = isPositive;
	}

	public Literal negation() {
		return new Literal(getIndex(), !isPositive());
	}

	public Integer getIndex() {
		return index;
	}

	public boolean isPositive() {
		return mIsPositive;
	}

	@Override
	public int hashCode() {
		return (isPositive() ? 1 : 31) * getIndex().hashCode();
	}

	public String toString() {
		return (isPositive() ? "" : "~") + getIndex();
	}

	@Override
	public boolean equals(Object obj) {

		Literal realObj = (Literal) obj;

		return realObj.isPositive() == isPositive() && realObj.getIndex().equals(getIndex());
	}

}
