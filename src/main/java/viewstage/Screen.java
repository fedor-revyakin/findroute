package viewstage;

import javax.enterprise.context.*;
import javax.inject.*;

import java.util.List;

import routecreation.SchemeInstructions;

@Named
@SessionScoped
public class Screen implements java.io.Serializable {

	public Screen() {}

	private final String startPage = "facilityScheme";

	private final String addition = "floor";

	IViewStage defaultViewStage = new DefaultViewStage(startPage);

	IViewStage routeViewStage = new RouteViewStage(this);

	IViewStage doubleViewStage = new DoubleViewStage(this);

	IViewStage currentViewStage = defaultViewStage;

	public String currentView() {return currentViewStage.currentView();}

	public String nextView() {return currentViewStage.nextView();}

	public String previousView() {return currentViewStage.previousView();}

	public String trig() {return currentViewStage.trig();}

	public String toDisplay() {return currentViewStage.toDisplay();}

	public void changeRoute(List<SchemeInstructions> instructions) {

		routeViewStage.setSchemeInstructions(instructions);

		currentViewStage = routeViewStage;
	}

	String getStartPage() {return startPage; }

	String getAddition() {return addition;}

	void resetCurrentPath() {currentViewStage.resetCurrentPath(); }

}

