package routecreation;

import tools.*;

import edu.princeton.cs.algs4.Queue;
import java.util.Iterator;

import javax.inject.*;

public abstract class RouteCreator {

	@Inject
	RouteRequirements req;

	@Inject
	CreatorShortestPath csp;

	@Inject
	StepCommandCreator stepCommandCreator;

	Queue<IStepCommand> shortestPathStep = new Queue<IStepCommand>();

	Iterator<Edge> temporaryPath;
	

	Queue<IStepCommand> createRoute () {

		createShortestPath();

		chooseRouteStartAddition();

		calculateRouteStair();

		chooseRouteFinishAddition();

		shortestPathStep.enqueue(stepCommandCreator.createFinishCommand());

		return shortestPathStep;

	}

	void produceRouteStair(){

		Queue<Edge> oneFloor = new Queue<Edge>();

		int floorLastEdge = req.getStartFloorNumber();

		int floorNextEdge = 0;

		while (temporaryPath.hasNext()) {

			Edge e = temporaryPath.next();

			floorNextEdge = e.floorNumber();

			if (floorLastEdge == floorNextEdge) {

				while (floorLastEdge == floorNextEdge && temporaryPath.hasNext()) {

					oneFloor.enqueue(e);

					e = temporaryPath.next();

					floorNextEdge = e.floorNumber();
					
				}

				if (floorLastEdge == floorNextEdge) oneFloor.enqueue(e);

				//построить маршрут для одноэтажа
				Edge singleEdge = oneFloor.dequeue();

				int start = singleEdge.from();

				while (!oneFloor.isEmpty()) {singleEdge = oneFloor.dequeue(); }

				int finish = singleEdge.to();

				Iterator<Edge> oneFloorRouteIterator = csp.createFloorPath(floorLastEdge, start, finish);

				while(oneFloorRouteIterator.hasNext()) shortestPathStep.enqueue(stepCommandCreator.createFloorCommand(oneFloorRouteIterator.next()));

				//
				if (floorLastEdge != floorNextEdge) shortestPathStep.enqueue(stepCommandCreator.createStairCommand(e));

				//
				floorLastEdge = floorNextEdge;

			} 
			else {

				shortestPathStep.enqueue(stepCommandCreator.createStairCommand(e));

				floorLastEdge = floorNextEdge;
			}
		}
	}

	abstract void createShortestPath();

	abstract void chooseRouteStartAddition();

	abstract void calculateRouteStair();

	abstract void chooseRouteFinishAddition();

}