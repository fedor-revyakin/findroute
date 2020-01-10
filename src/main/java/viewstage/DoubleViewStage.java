package viewstage;

import java.util.*;
import routecreation.SchemeInstructions;


public class DoubleViewStage implements IViewStage {

	Screen screen;

	public DoubleViewStage(Screen screen) {this.screen = screen;}

	public String currentView() {

		String result = screen.routeViewStage.currentView();

		screen.currentViewStage = screen.routeViewStage;

		return result;
	}

	public String nextView() {

		String result = screen.routeViewStage.nextView();

		screen.currentViewStage = screen.routeViewStage;

		return result;
	}

	public String previousView() {

		String result = screen.routeViewStage.previousView();

		screen.currentViewStage = screen.routeViewStage;

		return result;
	}

	public String trig() {return currentView();}

	public String toDisplay() {return ""; }

	public void setSchemeInstructions(List<SchemeInstructions> instructions){}

	public void resetCurrentPath() {screen.currentViewStage = screen.defaultViewStage; }
}