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
public class StartCompositeRoute extends CompositeRouteCreator {

	public StartCompositeRoute(){}

	void createShortestPath() {

		addStartAddition();

		temporaryPath = csp.createCompositePath((ISectorRepresentation)compositeRepresentation, compositeRepresentation.getSubStart(), req.getFinishVertex());

	}

	void chooseRouteStartAddition() {

		insertStartAddition();
	}

	void calculateRouteStair() {

		produceRouteStair();

	}

	void chooseRouteFinishAddition() {}

	@PostConstruct
	private void init() {
		compositeRepresentation = (IRepresentationConfigure)csp.createComposite(); 
	}

}