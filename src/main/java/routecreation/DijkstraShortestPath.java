package routecreation;

import tools.Edge;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Queue;
import java.util.Iterator;
import tools.ISectorRepresentation;

class DijkstraShortestPath {

	private Edge[] edgeTo;
	private double[] distTo;
	private IndexMinPQ<Double> pq;
	private Stack<Edge> shortestPath = new Stack<Edge>();

	DijkstraShortestPath(ISectorRepresentation rep, int start, int finish) {

		edgeTo = new Edge[rep.V()];

		distTo = new double[rep.V()];

		pq = new IndexMinPQ<Double>(rep.V());

		for (int v = 0; v < rep.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[start] = 0.0;

		//pq.insert(start, 0.0);

		int ejected = start;

		while(ejected != finish) {

			relax(rep, ejected);

			ejected = pq.delMin();

		}

		for (Edge e = edgeTo[finish]; e != null; e = edgeTo[e.from()])
			shortestPath.push(e);


	}

	private void relax(ISectorRepresentation rep, int v) {

		for(Edge e : rep.adj(v)) {

			int w = e.to();

			if (distTo[w] > distTo[v] + e.weight()) {

				distTo[w] = distTo[v] + e.weight();

				edgeTo[w] = e;

				if (pq.contains(w))
					pq.changeKey(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}

	Iterator<Edge> iterator() { return shortestPath.iterator(); }

	/*public Queue<Edge> getPathAsQueueEdges() {

		Iterator<Edge> i = shortestPath.iterator();

		Queue<Edge> result = new Queue<Edge>();

		while(i.hasNext())
			result.enqueue(i.next());

		return result;
	}*/
}