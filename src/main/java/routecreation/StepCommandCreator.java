package routecreation;

import tools.Edge;

import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class StepCommandCreator {

	@Inject
	private Guide guide;

	public StepCommandCreator(){}

	public IStepCommand createFloorCommand(Edge floorEdge) {

		return new FloorStep(floorEdge,guide);
		/*
		Queue<ICommandStep> result = new Queue<ICommandStep>();

		for(Edge e : floorEdges) {result.add(new FloorStep(e,guide)); }

		return result;*/
	} 

	public IStepCommand createStairCommand(Edge stairEdge) {

		return new StairStep(stairEdge,guide);
		
		/*Queue<ICommandStep> result = new Queue<ICommandStep>();

		for(Edge e : stairEdges) {result.add(new StairStep(e,guide)); }

		return result;*/
	} 


	public IStepCommand createFinishCommand() {

		return new FinishStep(guide);
	}

}