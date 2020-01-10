package tools;

public class Vertex {

	private final double x;
	private final double y;

	public Vertex(double x, double y) {this.x = x; this.y = y; }

	public double x() {return x; }

	public double y() {return y; }

	public String toString() {

		return "x = " + x + " y = " + y;
	}
}