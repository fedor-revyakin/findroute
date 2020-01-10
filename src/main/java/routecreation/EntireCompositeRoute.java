package routecreation;

import tools.*;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.Bag;

import java.util.Iterator;

import javax.enterprise.context.*;
import javax.inject.*;
import javax.annotation.PostConstruct;

@Named
@RequestScoped
public class EntireCompositeRoute extends CompositeRouteCreator {

	public EntireCompositeRoute(){}

	Edge endRouteEdge;

	void createShortestPath() {

		addStartAddition();

		addFinishAddition();

		temporaryPath = csp.createCompositePath((ISectorRepresentation)compositeRepresentation, compositeRepresentation.getSubStart(), compositeRepresentation.getSubFinish());
	}

	void chooseRouteStartAddition() {

		insertStartAddition();
	}

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