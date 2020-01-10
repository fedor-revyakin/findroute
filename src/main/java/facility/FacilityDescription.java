package facility;

//import tools.*;


//import java.util.*;
import javax.annotation.*;
import javax.enterprise.context.*;
import javax.inject.*;

@Named
@ApplicationScoped
public class FacilityDescription implements java.io.Serializable {

	@Inject
	AccessDB dataBaseIn;
/*
	private ST<Integer,FloorDescription> floorsDescripton = new ST<Integer,FloorDescription>();

	private ST<Integer, int[]> numberStairs = new ST<Integer, int[]>();


	private void addNumberStairs(int floorNumber, int[] numbers) {numberStairs.put(floorNumber, numbers); }

	private void addFloorDescription(int floorNumber) {floorsDescripton.put(floorNumber, new FloorDescription()); }

	private FloorDescription addSubjectsIntoDescription(int floorNumber) {return floorsDescripton.get(floorNumber); }

*/

	public Integer getNumberVertexOfSubjectFloor(Integer floorNumber, String subject) throws IllegalArgumentException {

	/*	FloorDescription floor = floorsDescripton.get(floorNumber);

		return floor.getNumberVertexOfSubject(subject);

	*/

		if (floorNumber == null) throw new IllegalArgumentException();

		return dataBaseIn.getNumberVertexOfSubjectFloor(floorNumber, subject);
	}

	public Integer getFloorNumberOfSubject(String subject) {

		return dataBaseIn.getFloorNumberOfSubject(subject);

		/*Iterator<Integer> countFloors = floorsDescripton.keys().iterator();

		int x = 0;

		while (countFloors.hasNext()) {

			x = countFloors.next();

			FloorDescription floor = floorsDescripton.get(x);

			if (floor.getNumberVertexOfSubject(subject) != null) return x;

		}

		return null;

		*/
	}

	public int[] getNumberStairs(int floorNumber) {

		String[] stringNumberStairs = dataBaseIn.getNumberStairs(floorNumber);

		int[] numberStairs = new int[stringNumberStairs.length];

		for (int i = 0; i < numberStairs.length; i++) numberStairs[i] = Integer.parseInt(stringNumberStairs[i]);

		return numberStairs;
		//return numberStairs.get(floorNumber); 

	}

	/*@PostConstruct
	public void initialize() {

		String[] floorsName = {"floorDescription1.txt", "floorDescription2.txt", "floorDescription3.txt"};

		for (String s : floorsName) {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/" + s);

			Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
			reader.useLocale(Locale.US);

			In in = new In(reader);

			int floorNumber = in.readInt();//1,2,3

			addFloorDescription(floorNumber);

			FloorDescription floorDescripton = addSubjectsIntoDescription(floorNumber);

			//
			int v = in.readInt();//

			for (int i = 0; i < v; i++) {

				String resultSubject = "";

				String nextToken = in.readString();

				while (!nextToken.equals("=")) {

					resultSubject += nextToken;

					nextToken = in.readString();
				}

				int vertexNumber = in.readInt();

				floorDescripton.addSubject(resultSubject, vertexNumber);
			}
        }

        String[] numberStairsName = {"floorStairs1.txt", "floorStairs2.txt", "floorStairs3.txt"};

        for (String s : numberStairsName) {

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/" + s);

			Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
			reader.useLocale(Locale.US);

			In in = new In(reader);

			int floorNumber = in.readInt();//1,2,3

			int[] numberStairs = in.readAllInts();

			addNumberStairs(floorNumber, numberStairs);

		}
	}
	*/
}