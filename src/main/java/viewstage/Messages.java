package viewstage;

import javax.inject.*;
import javax.enterprise.context.*;

import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;

import tools.CauseEnum;
import tools.RouteRequirements;

@Named
@RequestScoped
public class Messages {

	@Inject
	private RouteRequirements req;

	private FacesContext ctx = FacesContext.getCurrentInstance();

	public String showMessage() {

		CauseEnum startCause = req.getStartCause();
		CauseEnum finishCause = req.getFinishCause();

		addMessage(startCause, "from");
		addMessage(finishCause, "to");

		return null;

	}

	private void addMessage(CauseEnum cause, String component) {

		switch (cause) {

			case CORRECT:;

			default: ctx.addMessage("changeRoute:" + component, new FacesMessage(FacesMessage.SEVERITY_WARN,"Неполные данные", defineMessage(cause)));
		}

	}

	private String defineMessage(CauseEnum cause) {

		switch (cause) {

			case NO_SUBJECT_ON_FLOOR: return "Такого объекта на этаже нет";

			case NO_SUBJECT_ON_FACILITY: return "Такого объекта в здании нет";

			case NO_SPECIFY_DATA: return "NO SPECIFY DATA";

			case NO_SPECIFY_SUBJECT: return "NO_SPECIFY_SUBJECT";

			default: return "";
		}

	}

}