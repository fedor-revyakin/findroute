package viewstage;

import java.util.*;
import routecreation.SchemeInstructions;


public class RouteViewStage implements IViewStage {

	private ArrayList<SchemeInstructions> resetCurrentinstructions;

	private List<SchemeInstructions> instructions = new ArrayList<SchemeInstructions>();

	private int currentItem = 0;

	Screen screen;

	public RouteViewStage(Screen screen) {this.screen = screen;}

	public String currentView() {

		int floorNumber = instructions.get(currentItem).getFloorNumber();

		return completeName(floorNumber);
	}

	public String nextView() {

		int ni = currentItem;

		if(++ni < instructions.size()) currentItem = ni;

		return currentView();
	}

	public String previousView() {

		int pri = currentItem;

		if (--pri >= 0) currentItem = pri;

		return currentView();
	}

	public String trig() {

		String result = screen.getStartPage();

		screen.currentViewStage = screen.doubleViewStage;

		return result;
	}

	public void setSchemeInstructions(List<SchemeInstructions> instructions) {

		this.instructions = instructions;

		currentItem = 0;

	}

	public void resetCurrentPath() {

		int floorNumber = instructions.get(currentItem).getFloorNumber();

		resetCurrentinstructions = new ArrayList<SchemeInstructions>();

		resetCurrentinstructions.add(new SchemeInstructions(floorNumber));

		instructions = resetCurrentinstructions;

		currentItem = 0;
	}

	public String toDisplay() { return instructions.get(currentItem).toDisplay();}

	private String completeName(int floorNumber) { return screen.getAddition() + floorNumber; }



}