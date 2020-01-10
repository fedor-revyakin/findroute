package routecreation;

import tools.*;
import edu.princeton.cs.algs4.Queue;

import java.util.Iterator;

import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class StairRouteCreator extends RouteCreator {

	public StairRouteCreator(){}

	void createShortestPath() {temporaryPath = csp.createStairPath(req.getStartVertex(), req.getFinishVertex());}

	void chooseRouteStartAddition() {}

	void calculateRouteStair() {produceRouteStair(); }

	void chooseRouteFinishAddition() {}

}