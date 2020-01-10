package tools;

import java.util.Arrays;

import javax.enterprise.context.*;
import javax.inject.*;

@Named
@RequestScoped
public class RouteRequirements {

	private  Integer startVertex;
	private  Integer finishVertex;

	private  Integer startFloorNumber;
	private  Integer finishFloorNumber;

	private  String startSubject;
	private  String finishSubject;

	private  int[] startStair = new int[0];
	private  int[] finishStair = new int[0];

	public RouteRequirements() {}

	public void setStartFloorNumber(Integer startFloorNumber) { this.startFloorNumber = startFloorNumber; }

	public void setFinishFloorNumber(Integer finishFloorNumber)  { this.finishFloorNumber = finishFloorNumber; }

	public void setStartSubject(String startSubject) {
		this.startSubject = startSubject;
	}

	public String getStartSubject() { return startSubject; }

	public String getFinishSubject() { return finishSubject; }

	public void setFinishSubject(String finishSubject) {
		this.finishSubject = finishSubject;
	}

	public Integer getStartFloorNumber() {
		return startFloorNumber;
	}

	public void setStartVertex(Integer startVertex) {
		this.startVertex = startVertex;
	}

	public Integer getFinishFloorNumber() {
		return finishFloorNumber;
	}

	public void setFinishVertex(Integer finishVertex) {
		this.finishVertex = finishVertex;
	}

	public void setStartStair(int[] verticesNumber) {
		startStair = verticesNumber;
	}

	public void setFinishStair(int[] verticesNumber) {
		finishStair = verticesNumber;
	}

	public int[] getStartStair() {
		return startStair;
	}

	public int[] getFinishStair() {
		return finishStair;
	}
	
	public Integer getStartVertex() {
		return startVertex;
	}

	public Integer getFinishVertex() {
		return finishVertex;	
	}
	
	public boolean isAvailable() {

		if (startVertex != null && finishVertex != null) return (!isOnePoint());

		return false;
	}

	public KindOfRouteEnum getKindRoute() {

		if (!isAvailable()) return KindOfRouteEnum.NO_ROUTE;

		if (startFloorNumber.equals(finishFloorNumber)) return KindOfRouteEnum.ONE_FLOOR;
		
		if (startStair.length == 0 || finishStair.length == 0) return KindOfRouteEnum.NO_ROUTE;

		boolean isStartOnStair = false;
		for (int x : startStair) 
			if (x == startVertex) isStartOnStair = true;

		boolean isFinishOnStair = false;
		for (int x : finishStair) 
			if (x == finishVertex) isFinishOnStair = true;

		if (isStartOnStair && isFinishOnStair) return KindOfRouteEnum.STAIR_ROUTE;
			else if (isFinishOnStair) return KindOfRouteEnum.START_COMPOSITE_ROUTE;
			else if (isStartOnStair) return KindOfRouteEnum.FINISH_COMPOSITE_ROUTE;
			else return KindOfRouteEnum.COMPOSITE_ROUTE;

	}

	private boolean isOnePoint() {

		return (startFloorNumber.equals(finishFloorNumber) && startVertex.equals(finishVertex));	

	}


	/////
	public CauseEnum getStartCause() {

		return defineCause(startVertex, startFloorNumber, startSubject);
	}

	public CauseEnum getFinishCause() {

		return defineCause(finishVertex, finishFloorNumber, finishSubject);
	}

	private CauseEnum defineCause(Integer vertex, Integer floor, String subject) {

		if (floor == null & subject == null) return CauseEnum.NO_SPECIFY_DATA;
			
		if (floor != null & subject != null & vertex == null) return CauseEnum.NO_SUBJECT_ON_FLOOR;
			
		if (floor == null & subject != null & vertex == null) return CauseEnum.NO_SUBJECT_ON_FACILITY;

		if (floor != null & subject == null & vertex == null) return CauseEnum.NO_SPECIFY_SUBJECT;

		return CauseEnum.CORRECT;
	}



}