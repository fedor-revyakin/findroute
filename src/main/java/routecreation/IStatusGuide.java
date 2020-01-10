package routecreation;

import tools.Edge;
import tools.Vertex;

public interface IStatusGuide {

	public void passOnFloor(Edge e);

	public void enterOnStair(Edge e);

	public void finishRoute();

}

class StartRouteStatus implements IStatusGuide {

	Guide guide;

	public StartRouteStatus(Guide guide) {this.guide = guide; }

	public void passOnFloor(Edge e) {

		Vertex nextVertex = guide.coordinates.getVertex(e.floorNumber(), e.to());

		IDisplayElement edgeElement = guide.creatorElement.createEdgeElement(guide.lastVertex.x(), guide.lastVertex.y(), nextVertex.x(), nextVertex.y());

		IDisplayElementTune edgeTune = (IDisplayElementTune)edgeElement;

		edgeTune.setAdditionElement();

		guide.currentInstructions.addEdgeDisplayElement(edgeElement);

		guide.lastVertex = nextVertex;

		guide.currentStatus = guide.floorRouteStatus;

	}

	public void enterOnStair(Edge e) {

		if (e.floorNumber() > guide.lastFloorNumber) guide.setWayUpstairs();
			else guide.setWayDownstairs();

		guide.lastFloorNumber = e.floorNumber();

		guide.currentStatus = guide.stairRouteStatus;
	}

	public void finishRoute() {}
}

class FloorRouteStatus implements IStatusGuide {

	Guide guide;

	public FloorRouteStatus(Guide guide) {this.guide = guide; }

	public void passOnFloor(Edge e){

		Vertex nextVertex = guide.coordinates.getVertex(e.floorNumber(), e.to());

		IDisplayElement edgeElement = guide.creatorElement.createEdgeElement(guide.lastVertex.x(), guide.lastVertex.y(), nextVertex.x(), nextVertex.y());
	
		guide.currentInstructions.addEdgeDisplayElement(edgeElement);

		guide.lastVertex = nextVertex;
	}

	public void enterOnStair(Edge e){

		guide.currentInstructions.addEdgeDisplayElement(guide.creatorElement.createEdgeFinishElement());

		if (e.floorNumber() > guide.lastFloorNumber) guide.setWayUpstairs();
			else guide.setWayDownstairs();

		guide.lastFloorNumber = e.floorNumber();

		guide.currentStatus = guide.stairRouteStatus;
	}

	public void finishRoute(){

		guide.currentInstructions.addEdgeDisplayElement(guide.creatorElement.createEdgeFinishElement());

		IDisplayElement finishPointElement = guide.creatorElement.createFinishElement(guide.lastVertex.x(), guide.lastVertex.y());

		/*IDisplayElementTune finishTune = (IDisplayElementTune)finishElement;

		finishTune.closeEdgeElement();*/



		guide.currentInstructions.addPointDisplayElement(finishPointElement);

		guide.instructions.add(guide.currentInstructions);

		//проверка маршрута по этажу
		//for(SchemeInstructions scheme : (Iterable<SchemeInstructions>)guide.instructions) System.out.println(scheme.toDisplay());

	}

}

class StairRouteStatus implements IStatusGuide {

	Guide guide;

	public StairRouteStatus(Guide guide) {this.guide = guide; }

	public void passOnFloor(Edge e){

		//закончить формирование схемы
		guide.currentInstructions.addEdgeDisplayElement(guide.creatorElement.createEdgeFinishElement());

		IDisplayElement stairLastElement = (guide.isWayUpstairs()) ? guide.creatorElement.createUpstairsElement(guide.lastVertex.x(), guide.lastVertex.y()) : guide.creatorElement.createDownstairsElement(guide.lastVertex.x(), guide.lastVertex.y());

		guide.currentInstructions.addPointDisplayElement(stairLastElement);

		guide.instructions.add(guide.currentInstructions);

		//создать новую схему и добавить в нее символ "начало пути от лестницы" и символ "ребро"
		guide.currentInstructions = new SchemeInstructions(e.floorNumber());

		Vertex temporaryVertex = guide.coordinates.getVertex(e.floorNumber(), e.from());

		IDisplayElement stairNextElement = guide.creatorElement.createFromStairsToFloorElement(temporaryVertex.x(), temporaryVertex.y());

		guide.currentInstructions.addPointDisplayElement(stairNextElement);
		
		//добавить символ "ребро"
		Vertex nextVertex = guide.coordinates.getVertex(e.floorNumber(), e.to());

		guide.lastVertex = nextVertex;

		IDisplayElement edgeElement = guide.creatorElement.createEdgeElement(temporaryVertex.x(), temporaryVertex.y(), nextVertex.x(), nextVertex.y());

		IDisplayElementTune edgeTune = (IDisplayElementTune)edgeElement;

		edgeTune.setAdditionElement();

		guide.currentInstructions.addEdgeDisplayElement(edgeElement);

		guide.lastVertex = nextVertex;

		guide.currentStatus = guide.floorRouteStatus;

	}

	public void enterOnStair(Edge e){ guide.lastFloorNumber = e.floorNumber();}

	public void finishRoute(){

		/*
		расчет координат для IDisplayElement stairElement
		*/
		IDisplayElement stairElement = (guide.isWayUpstairs()) ? guide.creatorElement.createUpstairsElement(guide.lastVertex.x(), guide.lastVertex.y()) : guide.creatorElement.createDownstairsElement(guide.lastVertex.x(), guide.lastVertex.y());
		
		guide.currentInstructions.addPointDisplayElement(stairElement);

		/*
		расчет координат для IDisplayElement finishElement
		*/

		IDisplayElement finishElement = guide.creatorElement.createFinishElement(guide.lastVertex.x(), guide.lastVertex.y());

		IDisplayElementTune finishTune = (IDisplayElementTune)finishElement;

		finishTune.setAdditionElement();

		guide.currentInstructions.addPointDisplayElement(finishElement);


		guide.instructions.add(guide.currentInstructions);

		//проверка маршрута по этажу
		//for(SchemeInstructions scheme : (Iterable<SchemeInstructions>)guide.instructions) System.out.println(scheme.toDisplay());

	}

}


