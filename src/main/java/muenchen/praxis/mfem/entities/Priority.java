package muenchen.praxis.mfem.entities;

public enum Priority {
	A,B,C;

	public String toString() {
		switch(this) {
			case A: return "A";
			case B: return "B";
			case C: return "C";
			default: throw new IllegalArgumentException();
		}
	}
}
