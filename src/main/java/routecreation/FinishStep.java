package routecreation;

import tools.Edge;

class FinishStep implements IStepCommand {

	private Guide guide;

	FinishStep(Guide guide) {this.guide = guide; }

	public void nextStep() { guide.finishRoute();}

	public String testStepCommand() {return ""; }
}