package facility;

import tools.Vertex;

import javax.enterprise.context.*;
import javax.inject.*;//@Named
import javax.annotation.*;


@Named
@ApplicationScoped
public class Coordinates implements java.io.Serializable {

	@Inject
	AccessDB dataBaseIn;

	/*private ST<Integer,FloorCoordinates> facilityCoordinates = new ST<Integer,FloorCoordinates>();

	public Coordinates() {}
	*/

	/*public static Coordinates getInstanceForWrite() {return instance; }

	public static IReadOnlyCoordinates getInstanceForRead() {return (IReadOnlyCoordinates)instance; }
	*/

	public Vertex getVertex(int floorNumber, int vertexNumber) {

	/*	FloorCoordinates floorCoordinates = facilityCoordinates.get(floorNumber);

		return floorCoordinates.getVertex(vertexNumber);

	*/
		double[] coordinatesVertex = dataBaseIn.getVertex(floorNumber, vertexNumber);

		return new Vertex(coordinatesVertex[0], coordinatesVertex[1]);

	}

	/*

	private void addFloorCoordinates(int floorNumber, ST<Integer,Vertex> floorVertecies) {

		facilityCoordinates.put(floorNumber, new FloorCoordinates(floorVertecies));
	}

	public String toDisplay() {

		String s = "";

		for (Integer i : facilityCoordinates.keys()) {
			s  	+= "Coordinates of floor number "
				+ i
				+ facilityCoordinates.get(i).toDisplay();
			}

		return s;
	}

	@PostConstruct
	public void toRead() {

		//load Coordinates
		String[] coordinatesFloorName = {"/coordinatesFloor1.txt", "/coordinatesFloor2.txt", "/coordinatesFloor3.txt"};

		for (String s : coordinatesFloorName) {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(s);

			Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
			reader.useLocale(Locale.US);

			In in = new In(reader);
			
			int floorNumber = in.readInt();

			int count = in.readInt();

			ST<Integer,Vertex> floorVertices = new ST<Integer, Vertex>();

			for(int i = 0; i < count; i++) {

				int vertexNumber = in.readInt();

				double x = in.readDouble();

				double y = in.readDouble();

				floorVertices.put(vertexNumber,new Vertex(x,y));
			}

			addFloorCoordinates(floorNumber, floorVertices);
		}
	}

}

class FloorCoordinates {

	private ST<Integer, Vertex> floorVertecies;

	public FloorCoordinates(ST<Integer, Vertex> floorVertecies) {

		this.floorVertecies = floorVertecies;
	}

	Vertex getVertex(int vertexNumber) {return floorVertecies.get(vertexNumber); }

	public String toDisplay() {

		String s = "";

		for (Integer numberVertex : floorVertecies.keys())
			s 	+= Integer.toString(numberVertex)
				+ floorVertecies.get(numberVertex).toString();

		return s;
	}
	*/
}