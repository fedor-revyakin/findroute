package viewstage;

import facility.*;

import javax.inject.*;
import javax.enterprise.context.*;
import tools.RouteRequirements;

@Named
@RequestScoped
public class ParseInputData {

	public ParseInputData(){}

	@Inject
	private RouteRequirements req;

	@Inject
	private FacilityDescription facilityDescription;

	public void calculate() {

		calculateStartData(req.getStartFloorNumber(), req.getStartSubject());

		calculateFinishData(req.getFinishFloorNumber(), req.getFinishSubject());


		
	}


	private void calculateStartData(Integer floorNumber, String subject) {

		try {

			req.setStartVertex(facilityDescription.getNumberVertexOfSubjectFloor(floorNumber, subject));

			req.setStartStair(facilityDescription.getNumberStairs(floorNumber));

		} catch (IllegalArgumentException e) {

			preciseStart(floorNumber, subject);
		}
	}

	private void preciseStart(Integer floorNumber, String subject) {

		try {

			Integer n = facilityDescription.getFloorNumberOfSubject(subject);

			req.setStartFloorNumber(n);

			req.setStartVertex(facilityDescription.getNumberVertexOfSubjectFloor(n, subject));

			req.setStartStair(facilityDescription.getNumberStairs(n));

		} catch (IllegalArgumentException e) {}
	}


	private void calculateFinishData(Integer floorNumber, String subject) {

		try {

			req.setFinishVertex(facilityDescription.getNumberVertexOfSubjectFloor(floorNumber, subject));

			req.setFinishStair(facilityDescription.getNumberStairs(floorNumber));

		} catch (IllegalArgumentException e) {

			preciseFinish(floorNumber, subject);
		}
	}

	private void preciseFinish(Integer floorNumber, String subject) {

		try {

			Integer n = facilityDescription.getFloorNumberOfSubject(subject);

			req.setFinishFloorNumber(n);

			req.setFinishVertex(facilityDescription.getNumberVertexOfSubjectFloor(n, subject));

			req.setFinishStair(facilityDescription.getNumberStairs(n));

		} catch (IllegalArgumentException e) {}
	}
}


	/*public void setStartRouteData(String floor, String subject) {

		facilityDescription.initialize();

		req.setStartFloorNumber(floor);
		req.setStartSubject(subject);

		if (req.getStartFloorNumber() != null) {

			req.setStartVertex(facilityDescription.getNumberVertexOfSubjectFloor(req.getStartFloorNumber(),subject));

			req.setStartStair(facilityDescription.getNumberStairs(req.getStartFloorNumber()));

		} else {

			Integer f = facilityDescription.getFloorNumberOfSubject(subject);

			if (f != null) {

				req.setStartFloorNumber(Integer.toString(f));

				req.setStartVertex(facilityDescription.getNumberVertexOfSubjectFloor(f,subject));

				req.setStartStair(facilityDescription.getNumberStairs(f));
			}
		}
	}

	public void setFinishRouteData(String floor, String subject){

		req.setFinishFloorNumber(floor);
		req.setFinishSubject(subject);

		if (req.getFinishFloorNumber() != null) {

		req.setFinishVertex(facilityDescription.getNumberVertexOfSubjectFloor(req.getFinishFloorNumber(),subject));

		req.setFinishStair(facilityDescription.getNumberStairs(req.getFinishFloorNumber()));

		} else {

		Integer f = facilityDescription.getFloorNumberOfSubject(subject);

			if (f != null) {

				req.setFinishFloorNumber(Integer.toString(f));

				req.setFinishVertex(facilityDescription.getNumberVertexOfSubjectFloor(f,subject));

				req.setFinishStair(facilityDescription.getNumberStairs(f));
			}
		}
	}*/

