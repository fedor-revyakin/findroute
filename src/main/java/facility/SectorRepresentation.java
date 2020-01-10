package facility;

import edu.princeton.cs.algs4.Bag;
import tools.*;

public class SectorRepresentation implements ISectorRepresentation, IRepresentationConfigure {

	private final int V;

	private int E;

	private Bag<Edge>[] adj;

	SectorRepresentation(int V) 
	{

	this.V = V;

	adj = (Bag<Edge>[]) new Bag[V];

	for(int v = 0; v<V; v++) 
		adj[v] = new Bag<Edge>();

	}

	@Override
	public int V() { return V; }

	@Override
	public Iterable<Edge> adj(int v) { return adj[v]; }

	@Override
	public void addEdge(Edge e) {

		adj[e.from()].add(e);

	}
	@Override
	public void setStartAddition(Bag<Edge> sa) {}
	@Override
	public void setFinishAddition(Bag<Edge> fa) {}
	@Override
	public int getSubStart() {return 0; }
	@Override
	public int getSubFinish() {return 0; }

	@Override
	public String toString(){

		String s = "";

		for (int i = 0; i < V(); i++) {
			for(Edge e : adj(i)) s += e.toString() + " ";
			s += "\n";
		}

		return s;

	}
}