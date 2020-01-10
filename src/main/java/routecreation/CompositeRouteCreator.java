package routecreation;

import tools.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Bag;

import java.util.Iterator;

public abstract class CompositeRouteCreator extends RouteCreator {

	public CompositeRouteCreator() {}

	IRepresentationConfigure compositeRepresentation;

	ST<Integer, Queue<Edge>> possibleStartPath = new ST<Integer, Queue<Edge>>();

	ST<Integer, Queue<Edge>> possibleFinishPath = new ST<Integer, Queue<Edge>>();

	void addStartAddition() {

		for (int x : req.getStartStair()) {

			Queue<Edge> onePossiblePath = new Queue<Edge>();

			Iterator<Edge> i = csp.createFloorPath(req.getStartFloorNumber(), req.getStartVertex(), x);

			while(i.hasNext()) onePossiblePath.enqueue(i.next());

			possibleStartPath.put(x, onePossiblePath);
		}

		Bag<Edge> startAddition = new Bag<Edge>();

		for (int k : possibleStartPath.keys()) {

			Queue<Edge> path = possibleStartPath.get(k);

			Iterator<Edge> i = path.iterator();

			double weight = 0.0;

			int finish = 0;

			while (i.hasNext()) {

				Edge e = i.next();

				weight += e.weight();

				finish = e.to();

			}

			startAddition.add(Edge.createEdge(compositeRepresentation.getSubStart(), finish, weight, req.getStartFloorNumber()));
		}

		compositeRepresentation.setStartAddition(startAddition);
	}


	void addFinishAddition() {

		for (int x : req.getFinishStair()) {

			Queue<Edge> onePossiblePath = new Queue<Edge>();

			Iterator<Edge> i = csp.createFloorPath(req.getFinishFloorNumber(), x, req.getFinishVertex());

			while (i.hasNext()) onePossiblePath.enqueue(i.next());

			possibleFinishPath.put(x, onePossiblePath);
		}

		Bag<Edge> finishAddition = new Bag<Edge>();

		for (int k : possibleFinishPath.keys()) {

			Queue<Edge> path = possibleFinishPath.get(k);

			Iterator<Edge> i = path.iterator();

			double weight = 0.0;

			while (i.hasNext()) {

				Edge e = i.next();

				weight += e.weight();

			}

			finishAddition.add(Edge.createEdge(k, compositeRepresentation.getSubFinish(), weight, req.getFinishFloorNumber()));
		}

		compositeRepresentation.setFinishAddition(finishAddition);

	}

	void insertStartAddition() {

		int finish = temporaryPath.next().to();

		for (Edge e : possibleStartPath.get(finish)) shortestPathStep.enqueue(stepCommandCreator.createFloorCommand(e));

			

	}

	Edge prepareFinishAddition() {

		Queue<Edge> preTemporary = new Queue<Edge>();

		Edge e = temporaryPath.next();

		while (temporaryPath.hasNext()) {

			preTemporary.enqueue(e);

			e = temporaryPath.next();
		}

		temporaryPath = preTemporary.iterator();

		return e;
	}

	void insertFinishAddition(Edge endRouteEdge) {

		int start = endRouteEdge.from();

		for (Edge e : possibleFinishPath.get(start)) shortestPathStep.enqueue(stepCommandCreator.createFloorCommand(e));
	}
}


