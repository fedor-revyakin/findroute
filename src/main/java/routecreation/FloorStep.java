package routecreation;

import tools.Edge;

class FloorStep implements IStepCommand {

	private Edge edge;
	private Guide guide;

	FloorStep(Edge edge, Guide guide) {this.edge = edge; this.guide = guide; }

	public void nextStep() { guide.passOnFloor(edge);}

	public String testStepCommand() {
		return edge.toString() + " - FloorStep";
	}
}