package routecreation;

import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;

import javax.enterprise.context.*;
import javax.inject.*;

import viewstage.Screen;
import tools.*;
import facility.Coordinates;

@Named
@RequestScoped
public class Guide {

	@Inject
	CreatorDisplayElement creatorElement;

	@Inject
	private RouteRequirements req;

	@Inject
	Coordinates coordinates;

	@Inject
	Screen screen;

	IStatusGuide startRouteStatus;
	IStatusGuide floorRouteStatus;
	IStatusGuide stairRouteStatus;

	ArrayList<SchemeInstructions> instructions = new ArrayList<SchemeInstructions>();

	IStatusGuide currentStatus;

	SchemeInstructions currentInstructions;

	Vertex lastVertex;

	int lastFloorNumber;

	private boolean upstairs = false;

	public Guide() {}

	public void passOnFloor(Edge e) {

		currentStatus.passOnFloor(e);
	}

	public void enterOnStair(Edge e){

		currentStatus.enterOnStair(e);
	}

	public void finishRoute() {

		currentStatus.finishRoute();

		screen.changeRoute(instructions);
	}

	boolean isWayUpstairs() {return upstairs; }

	void setWayUpstairs() { upstairs = true;}

	void setWayDownstairs() {upstairs = false; }

	public void initialize() {

		currentInstructions = new SchemeInstructions(req.getStartFloorNumber());

		lastVertex = coordinates.getVertex(req.getStartFloorNumber(), req.getStartVertex());

		lastFloorNumber = req.getStartFloorNumber();

		IDisplayElement startElement = creatorElement.createStartElement(lastVertex.x(), lastVertex.y());

		currentInstructions.addPointDisplayElement(startElement);

		startRouteStatus = new StartRouteStatus(this);
		floorRouteStatus = new FloorRouteStatus(this);
		stairRouteStatus = new StairRouteStatus(this);

		currentStatus = startRouteStatus;
	}
}
