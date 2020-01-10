package routecreation;

import edu.princeton.cs.algs4.Queue;

public class SchemeInstructions {

	private final int floorNumber;

	private Queue<IDisplayElement> pageEdgeInstructions = new Queue<IDisplayElement>();

	private Queue<IDisplayElement> pagePointInstructions = new Queue<IDisplayElement>();

	public SchemeInstructions(int floorNumber) {

		this.floorNumber = floorNumber;
	}

	public void addPointDisplayElement(IDisplayElement element) {

		pagePointInstructions.enqueue(element);
	}

	public void addEdgeDisplayElement(IDisplayElement element) {

		pageEdgeInstructions.enqueue(element);
	}

	public int getFloorNumber() {return floorNumber; }

	public String toDisplay() {

		String s = "";

		for (IDisplayElement element : pageEdgeInstructions) s = s + element.toDisplay();

		for (IDisplayElement element : pagePointInstructions) s = s + element.toDisplay();

		return s;
	}

}
