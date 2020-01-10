package tools;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.ST;//map

public interface IRepresentationConfigure {

	void addEdge(Edge e);

	void setStartAddition(Bag<Edge> sa);

	void setFinishAddition(Bag<Edge> fa);

	int getSubStart();

	int getSubFinish();
	
}