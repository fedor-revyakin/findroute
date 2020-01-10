package facility;

import tools.*;
import edu.princeton.cs.algs4.*;

import java.io.*;

import java.util.*;
import javax.annotation.*;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@ApplicationScoped
public class Facility implements java.io.Serializable {

	private ST<Integer,ISectorRepresentation> floorsRepresentation = new ST<Integer,ISectorRepresentation>();

	private ISectorRepresentation stairRepresentation;

	public Facility() {};

	private void addFloor(int floorNumber, int countVertices) {

		ISectorRepresentation floorRepresentation = createSectorRepresentation(countVertices);

		floorsRepresentation.put(floorNumber, floorRepresentation);

	}

	private void addStairRepresentation(int countVertices) {

		stairRepresentation = createSectorRepresentation(countVertices);
	}

	private IRepresentationConfigure addEdgesIntoStair() {return (IRepresentationConfigure)stairRepresentation; }

	private IRepresentationConfigure addEdgesIntoFloor(int floorNumber) { return (IRepresentationConfigure)floorsRepresentation.get(floorNumber); }


	public ISectorRepresentation getFloorRepresentation(int floorNumber) { return floorsRepresentation.get(floorNumber); }

	public ISectorRepresentation getStairRepresentation() { return stairRepresentation; }

	///
	private ISectorRepresentation createSectorRepresentation(int countVertices) { return new SectorRepresentation(countVertices); }

	public ISectorRepresentation createCompositeRepresentation() { return new CompositeRepresentation(stairRepresentation); }

	public String toDisplay() {

		if (floorsRepresentation.size() == 0) return "No floor data";

		String a = "Count of floors: " + floorsRepresentation.size() + "\n";

		String b = "";

		for (Integer i : floorsRepresentation.keys()) {

			b 	+= "Floor " + i + ":\n"
				+"Count of vertices: " + floorsRepresentation.get(i).V() + "\n"
				+"Edges and weight:\n"
				+ floorsRepresentation.get(i).toString()
				+"\n";
			}

			b 	+=stairRepresentation.toString();

		return a + b;

		}

	@PostConstruct
	public void toRead() {

		String[] floorsName = {"floor1.txt", "floor2.txt", "floor3.txt"};

		
		for (String s : floorsName) {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/" + s);

			Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
			reader.useLocale(Locale.US);

			In in = new In(reader);

			int floorNumber = in.readInt();//1,2,3

			int v = in.readInt();//20

			addFloor(floorNumber, v);

			IRepresentationConfigure floorRepresentation = addEdgesIntoFloor(floorNumber);

			//read edges count 20
			int E = in.readInt();

			for (int i = 0; i < E; i++) {

				int from = in.readInt();

    			int to = in.readInt();

    			double weight = in.readDouble();

    			tools.Edge other = tools.Edge.createEdge(to, from, weight, floorNumber);
				tools.Edge either = tools.Edge.createEdge(from, to, weight, floorNumber);

				floorRepresentation.addEdge(other);
				floorRepresentation.addEdge(either);
			}
        }

    
      	InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/stair.txt");

		Scanner reader = new Scanner(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		//load Stair
		In in = new In(reader);
	
		int v = in.readInt();

		addStairRepresentation(v);

		IRepresentationConfigure stairRepresentation = addEdgesIntoStair();

		//read edges count 8
		int E = in.readInt();

		for (int i = 0; i < E; i++) {

			int from = in.readInt();

    		int to = in.readInt();

    		double weight = in.readDouble();

    		int floorNumber = in.readInt();

    		tools.Edge e = tools.Edge.createEdge(from, to, weight, floorNumber);

			stairRepresentation.addEdge(e);
		}

		in.close();


	}
}

