package tools;

public interface ISectorRepresentation {

	int V();

	Iterable<Edge> adj(int v);

}
