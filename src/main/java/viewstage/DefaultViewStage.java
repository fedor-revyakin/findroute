package viewstage;

import java.util.*;
import routecreation.SchemeInstructions;

public class DefaultViewStage implements IViewStage {

	private final String startPage;

	DefaultViewStage(String startPage) {this.startPage = startPage; }

	public String currentView() {return startPage; }

	public String nextView() {return currentView(); }

	public String previousView() { return currentView(); }

	public String trig() {return currentView();}

	public String toDisplay() {return "";}

	public void setSchemeInstructions(List<SchemeInstructions> instructions){}

	public void resetCurrentPath(){};

}