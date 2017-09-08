package muenchen.praxis.mfem.entities;

public enum Priority {
	A(1.0),
	B(0.5),
	C(0.25);

	private double value;

	Priority(double value) {
		this.value=value;
	}

	public double getValue() {
		return value;
	}

	public String toString() {
		switch(this) {
			case A: return "A";
			case B: return "B";
			case C: return "C";
			default: throw new IllegalArgumentException();
		}
	}
}
