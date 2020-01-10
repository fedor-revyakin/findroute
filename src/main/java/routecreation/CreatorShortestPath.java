package routecreation;

import tools.*;

import java.util.Iterator;

import javax.enterprise.context.*;
import javax.inject.*;

import facility.Facility;

@Named
@RequestScoped
public class CreatorShortestPath {
	
	@Inject
	private Facility facility;

	public CreatorShortestPath() {}

	public Iterator<Edge> createFloorPath(int floorNumber, int start, int finish) {

		ISectorRepresentation floorRepresentation = facility.getFloorRepresentation(floorNumber);

		return new DijkstraShortestPath(floorRepresentation, start, finish).iterator();
	}

	public Iterator<Edge> createStairPath(int start, int finish) {

		return new DijkstraShortestPath(facility.getStairRepresentation(), start, finish).iterator();

	}

	public Iterator<Edge> createCompositePath(ISectorRepresentation composite, int start, int finish) {

		return new DijkstraShortestPath(composite, start, finish).iterator();
	}

	public ISectorRepresentation createComposite() {

		return facility.createCompositeRepresentation();
	}


}