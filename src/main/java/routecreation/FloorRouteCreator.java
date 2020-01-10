package routecreation;

import tools.*;
import edu.princeton.cs.algs4.Queue;

import javax.enterprise.context.*;
import javax.inject.*;

import java.util.Iterator;

@Named
@RequestScoped
public class FloorRouteCreator extends RouteCreator {

	public FloorRouteCreator(){}

	void createShortestPath() {

		Iterator<Edge> i = csp.createFloorPath(req.getStartFloorNumber(), req.getStartVertex(), req.getFinishVertex());

		while(i.hasNext()) shortestPathStep.enqueue(stepCommandCreator.createFloorCommand(i.next()));
				
	}

	void chooseRouteStartAddition() {}

	void calculateRouteStair() {}

	void chooseRouteFinishAddition() {}
}