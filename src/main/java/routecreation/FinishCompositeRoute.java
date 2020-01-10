package routecreation;

import tools.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Bag;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.annotation.PostConstruct;

import java.util.Iterator;

@Named
@RequestScoped
public class FinishCompositeRoute extends CompositeRouteCreator {

	public FinishCompositeRoute(){}

	Edge endRouteEdge;

	void createShortestPath() {

		addFinishAddition();

		temporaryPath = csp.createCompositePath((ISectorRepresentation)compositeRepresentation, req.getStartVertex(), compositeRepresentation.getSubFinish());
	}

	void chooseRouteStartAddition() {}

	void calculateRouteStair() {

		endRouteEdge = prepareFinishAddition();

		produceRouteStair();

	}

	void chooseRouteFinishAddition() {

		insertFinishAddition(endRouteEdge);

	}

	@PostConstruct
	private void init() {
		compositeRepresentation = (IRepresentationConfigure)csp.createComposite(); 
	}

	
}