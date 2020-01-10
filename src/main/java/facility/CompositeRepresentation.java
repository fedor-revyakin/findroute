package facility;

import tools.*;
import java.util.Iterator;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;//map

public class CompositeRepresentation implements ISectorRepresentation, IRepresentationConfigure {

	private ISectorRepresentation stairRepresentation;

	private Bag<Edge> startAddition = new Bag<Edge>();

	private Bag<Edge> finishAddition = new Bag<Edge>();

	CompositeRepresentation(ISectorRepresentation stairRepresentation) {

		this.stairRepresentation = stairRepresentation;
	}

	@Override
	public int V() {

		int m = startAddition.size();

		if (finishAddition.size() > 0) { ++m; }

		return stairRepresentation.V() + m;

	}

	@Override
	public void addEdge(Edge e) {}

	@Override
	public Iterable<Edge> adj(int v) {

		Bag<Edge> result = new Bag<Edge>();

		//добавить ребро из начала, если есть
		if (v == getSubStart()) {

			for (Edge e : startAddition) result.add(e);
			
			return result;
		}

		//получить ребра из stairRep
		for (Edge e : stairRepresentation.adj(v))
			result.add(e);

		//добавить ребро из конца, если есть
		for (Edge e : finishAddition) {

			if (e.from() == v) { result.add(e); }
		}

		return result;
		
	}

	@Override
	public void setStartAddition(Bag<Edge> sa) {

		startAddition = sa;

	}

	@Override
	public void setFinishAddition(Bag<Edge> fa) {

		finishAddition = fa;

		/*check
		int x = V();

		Iterator<Edge> i = fa.iterator();

		int y = i.next().to();

		return (x == y);
		*/
	}

	@Override
	public int getSubStart() {

		return stairRepresentation.V();
	}

	@Override
	public int getSubFinish() {

		return getSubStart() + startAddition.size();
	}

}