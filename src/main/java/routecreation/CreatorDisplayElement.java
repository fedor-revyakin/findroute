package routecreation;

import javax.enterprise.context.*;
import javax.inject.*;

import edu.princeton.cs.algs4.In;

import java.io.*;
import java.util.*;

@Named
@RequestScoped
public class CreatorDisplayElement {

	public CreatorDisplayElement() {}

	public IDisplayElement createStartElement(double x, double y) {

		return new StartDisplayElement(x,y);
	}

	public IDisplayElement createEdgeElement(double fromX, double fromY, double toX, double toY) {

		return new EdgeDisplayElement(fromX, fromY, toX, toY);
	}

	public IDisplayElement createEdgeFinishElement() {

		return new EdgeFinishDisplayElement();
	}

	public IDisplayElement createFinishElement(double x, double y) {

		return new FinishDisplayElement(x,y);

	}

	public IDisplayElement createUpstairsElement(double x, double y) {

		return new UpstairsDisplayElement(x,y);

	}

	public IDisplayElement createDownstairsElement(double x, double y) {

		return new DownstairsDisplayElement(x,y);

	}

	public IDisplayElement createFromStairsToFloorElement(double x, double y) {

		return new FromStairsToFloorDisplayElement(x,y);
	}
}

class StartDisplayElement implements IDisplayElement, IDisplayElementTune {

	private final double x;
	private final double y;
	private String dplElementFileName = "/icon_start.txt";

	private double iconWidth;
	private double iconHeight;


	public StartDisplayElement(double x, double y) {this.x = x; this.y = y; }

	public String toDisplay() {

		return getStartElement();

	}

	public void setAdditionElement(){}
	
	private String getStartElement() {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		iconWidth = in.readDouble();
		iconHeight = in.readDouble();

		String result = "";

		String s1 = "<g transform=\"translate(";

		String s2 = ")\" viewBox=\"0 0 30 45\" xmlns:bx=\"https://boxy-svg.com\" >\n";

		String translate = Double.toString(x - iconWidth/2.0) + " " + Double.toString(y - iconHeight);

		String s3 = s1 + translate + s2;

		String text = in.readAll();

		result = s3 + text;

		in.close();

		return result;
	}

}



class EdgeDisplayElement implements IDisplayElement, IDisplayElementTune {

	private final double fromX;
	private final double fromY;

	private final double toX;
	private final double toY;

	private String dplElementFileName = "/icon_edge_start.txt";

	private String startEdgeElement = "";

	public EdgeDisplayElement(double fromX, double fromY, double toX, double toY) {
		this.fromX = fromX; this.fromY = fromY; this.toX = toX; this.toY = toY; }

	public String toDisplay() {

		return getStartEdgeElement() + getEdgeElement();
	}

	public void setAdditionElement(){ 

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		startEdgeElement = in.readLine() + "\n";

		startEdgeElement += "<path d=\"M " + fromX + ", " + fromY + " "; 

	}

	private String getStartEdgeElement() {return startEdgeElement; }

	private String getEdgeElement() {

		return "L " + toX + ", " + toY + " ";
	}

}

class EdgeFinishDisplayElement implements IDisplayElement {

	public String toDisplay() { return "\"/>\n" + "</g>\n";}
}


class FinishDisplayElement implements IDisplayElement, IDisplayElementTune {

	private double x;
	private double y;
	private String dplElementFileName = "/icon_finish.txt";

	private String element;

	private double iconWidth;
	private double iconHeight;

	public FinishDisplayElement(double x, double y) {

		this.x = x; this.y = y;
	
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		iconWidth = in.readDouble();
		iconHeight = in.readDouble();

		element = in.readAll();

		in.close(); 
	}

	public String toDisplay() {

		return getFinishElement();
	}

	public void setAdditionElement() {

		x += iconWidth;
	}

	private String getFinishElement() {

		String s1 = "<g transform=\"translate(";

		String s2 = ")\" viewBox=\"0 0 30 45\" xmlns:bx=\"https://boxy-svg.com\" >\n";

		String translate = Double.toString(x - iconWidth/2.0) + " " + Double.toString(y - iconHeight);

		String s3 = s1 + translate + s2;

		return s3 + element;
	}
}

class UpstairsDisplayElement implements IDisplayElement, IDisplayElementTune {

	private final double x;
	private final double y;

	private double iconWidth;
	private double iconHeight;

	private String dplElementFileName = "/icon_upstair.txt";

	public UpstairsDisplayElement(double x, double y) {this.x = x; this.y = y; }

	public String toDisplay() {
		return getUpstairElement();
	}

	private String getUpstairElement() {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		iconWidth = in.readDouble();
		iconHeight = in.readDouble();

		String s1 = "<g transform=\"translate(";

		String s2 = ")\" viewBox=\"0 0 30 45\" xmlns:bx=\"https://boxy-svg.com\" >\n";

		String translate = Double.toString(x - iconWidth/2.0) + " " + Double.toString(y - iconHeight);

		String s3 = s1 + translate + s2;

		String text = in.readAll();

		in.close();

		return s3 + text;
	}

	public void setAdditionElement() {}
}

class DownstairsDisplayElement implements IDisplayElement, IDisplayElementTune {

	private final double x;
	private final double y;

	private double iconWidth;
	private double iconHeight;

	private String dplElementFileName = "/icon_downstair.txt";

	public DownstairsDisplayElement(double x, double y) {this.x = x; this.y = y; }

	public String toDisplay() {
		return getDownstairElement();
	}

	private String getDownstairElement() {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		iconWidth = in.readDouble();
		iconHeight = in.readDouble();

		String s1 = "<g transform=\"translate(";

		String s2 = ")\" viewBox=\"0 0 30 45\" xmlns:bx=\"https://boxy-svg.com\" >\n";

		String translate = Double.toString(x - iconWidth/2.0) + " " + Double.toString(y - iconHeight);

		String s3 = s1 + translate + s2;

		String text = in.readAll();

		in.close();

		return s3 + text;
	}


	public void setAdditionElement() {}
}

class FromStairsToFloorDisplayElement implements IDisplayElement, IDisplayElementTune {

	private final double x;
	private final double y;

	private double iconWidth;
	private double iconHeight;

	private String dplElementFileName = "/icon_stair_to_floor.txt";

	public FromStairsToFloorDisplayElement(double x, double y) {this.x = x; this.y = y; }

	public String toDisplay() {
		return getStairsToFloor();
	}

	private String getStairsToFloor() {

		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dplElementFileName);

		Scanner reader = new Scanner​(new BufferedInputStream(inputStream), "UTF-8");
		reader.useLocale(Locale.US);

		In in = new In(reader);

		iconWidth = in.readDouble();
		iconHeight = in.readDouble();

		String s1 = "<g transform=\"translate(";

		String s2 = ")\" viewBox=\"0 0 30 45\" xmlns:bx=\"https://boxy-svg.com\" >\n";

		String translate = Double.toString(x - iconWidth/2.0) + " " + Double.toString(y - iconHeight);

		String s3 = s1 + translate + s2;

		String text = in.readAll();

		in.close();

		return s3 + text;
	}


	public void setAdditionElement() {}
}

interface IDisplayElementTune {

	public void setAdditionElement();

}


