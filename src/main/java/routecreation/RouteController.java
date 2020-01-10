package routecreation;

import tools.*;

import edu.princeton.cs.algs4.Queue;
import java.util.Iterator;

import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class RouteController {

	public RouteController() {}

	@Inject
	private RouteRequirements req;

	@Inject
	private Guide guide;

	@Inject
	private FloorRouteCreator floorRouteCreator;

	@Inject
	private StairRouteCreator stairRouteCreator;

	@Inject
	private StartCompositeRoute startCompositeRoute;

	@Inject
	private FinishCompositeRoute finishCompositeRoute;

	@Inject
	private EntireCompositeRoute entireCompositeRoute;

	private RouteCreator routeCreator;

	public void createRoute() {

		if (!req.isAvailable()) return;

		routeCreator = defineMethod();

		Queue<IStepCommand> route = routeCreator.createRoute();

		for (IStepCommand isp : route) isp.nextStep();

		return;
	}

	private RouteCreator defineMethod() {

		switch (req.getKindRoute()) {

			case ONE_FLOOR: return floorRouteCreator;

			case STAIR_ROUTE: return stairRouteCreator;

			case START_COMPOSITE_ROUTE: return startCompositeRoute;

			case FINISH_COMPOSITE_ROUTE: return finishCompositeRoute;

			case COMPOSITE_ROUTE: return entireCompositeRoute;

			default: return null;
		}
	}
	
}