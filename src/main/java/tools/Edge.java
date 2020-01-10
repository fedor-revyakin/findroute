package tools;

//ВНИМАНИЕ!!!!
//При чтении ребер из файла создавать ДВА РАЗНЫХ ОБЪЕКТА РЕБРА ИЗ КАЖДОЙ СТРОКИ ДАННЫХ
public class Edge {

	private final int v;

	private final int w;

	private final double weight;

	private final int floorNumberEdgeEnd;

	public static Edge createEdge(int v, int w, double weight, int floorNumberEdgeEnd) {

		return new Edge(v, w, weight, floorNumberEdgeEnd);
	}

	private Edge(int v, int w, double weight, int floorNumberEdgeEnd) {

		this.v = v;

		this.w = w;

		this.weight = weight;

		this.floorNumberEdgeEnd = floorNumberEdgeEnd;
	}

	public int from() {

		return v;
	}

	public int to() {

		return w;
	}

	public double weight() {

		return weight;
	}

	public int floorNumber() {

		return floorNumberEdgeEnd;
	}

	@Override
	public String toString() {

		String s = Integer.toString(v) + "  " + Integer.toString(w);

		return s;
	}
}