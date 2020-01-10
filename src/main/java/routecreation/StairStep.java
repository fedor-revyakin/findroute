package routecreation;

import tools.Edge;

class StairStep implements IStepCommand {

	private Edge edge;
	private Guide guide;

	StairStep(Edge edge, Guide guide) {this.edge = edge; this.guide = guide; }

	public void nextStep() { guide.enterOnStair(edge);}

	public String testStepCommand() {

		return edge.toString() + " - StairStep";
	}
}