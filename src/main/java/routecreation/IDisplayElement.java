package routecreation;

//import java.util.Comparator;

public interface IDisplayElement {

	public String toDisplay();

}

/*class ZIndexCompare implements Comparator<IDisplayElement> {
	
	public int compare(IDisplayElement elt1, IDisplayElement elt2) {

		IDisplayElementTune tuneElement1 = (IDisplayElementTune)elt1;

		IDisplayElementTune tuneElement2 = (IDisplayElementTune)elt2;

		Integer zIndex1 = tuneElement1.getZIndexNumber();

		Integer zIndex2 = tuneElement2.getZIndexNumber();

		return zIndex1.compare(zIndex1, zIndex2);
	}
}*/
