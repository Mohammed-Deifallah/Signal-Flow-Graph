package sfg;

public class Pair {
	private String s;
	private float gain;

	public Pair(String s, float g) {
		this.s = s;
		gain = g;
	}

	public Pair(float c, float g) {
		s = "" + c;
		gain = g;
	}

	public Pair() {
		s = "";
		gain = 1;
	}

	public String getS() {
		return s;
	}

	public float getGain() {
		return gain;
	}

	public void updateS(int n) {
		s += n;
	}

	public void updateGain(float g) {
		gain *= g;
	}
}
