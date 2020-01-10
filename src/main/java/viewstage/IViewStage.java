package viewstage;

import java.util.List;
import routecreation.SchemeInstructions;

public interface IViewStage {

	String currentView();

	String nextView();

	String previousView();

	String trig();

	void setSchemeInstructions(List<SchemeInstructions> instructions);

	void resetCurrentPath();

	String toDisplay();
}