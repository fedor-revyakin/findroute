package viewstage;

import javax.inject.*;
import javax.enterprise.context.*;

import routecreation.*;
import tools.*;

@Named
@SessionScoped
public class RoutePointsFromUser implements java.io.Serializable {

	private String startFloor;
	private String finishFloor;

	private String startSubject;
	private String finishSubject;

	@Inject
	private Screen screen;

	@Inject
	private ParseInputData creatorRequirements;

	@Inject
	private Guide guide;

	@Inject
	private Messages messages;
	
	@Inject
	private RouteController routeController;

	@Inject
	private RouteRequirements req;

	public String getInstructions() {

		creatorRequirements.calculate();

		if (!req.isAvailable()) {

			screen.resetCurrentPath();

			return messages.showMessage();
		}

		guide.initialize();

		routeController.createRoute();

		startFloor = req.getStartFloorNumber().toString();

		finishFloor = req.getFinishFloorNumber().toString();

		return screen.currentView();
	}

	public void setStartFloor(String i) {

		this.startFloor = i;

		Integer n = null;

		try {

			if (i.length() == 0) i = null;
			
			n = Integer.parseInt(i);

		} catch (Exception e) {}

		req.setStartFloorNumber(n);
	}

	public void setFinishFloor(String i) {

		this.finishFloor = i;

		Integer n = null;

		try {

			if (i.length() == 0) i = null;
			
			n = Integer.parseInt(i);

		} catch (Exception e) {}

		req.setFinishFloorNumber(n);
	}

	public String getStartFloor() {
		return startFloor;
	}

	public String getFinishFloor() {
		return finishFloor;
	}


	public void setStartSubject(String startSubject) {

		this.startSubject = startSubject;

		startSubject = startSubject.toLowerCase().replace(" ","");

		req.setStartSubject(startSubject);
	}

	public void setFinishSubject(String finishSubject) {

		this.finishSubject = finishSubject;

		finishSubject = finishSubject.toLowerCase().replace(" ","");

		req.setFinishSubject(finishSubject);
	}

	public String getStartSubject() {
		return startSubject;
	}

	public String getFinishSubject() {
		return finishSubject;
	}
	
}