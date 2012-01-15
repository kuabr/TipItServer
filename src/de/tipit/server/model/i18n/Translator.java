package de.tipit.server.model.i18n;

import de.tipit.server.model.entity.InternationalDescription;
import de.tipit.server.model.entity.InternationalName;
import de.tipit.server.model.entity.InternationalShortDescr;
import de.tipit.server.transfer.data.ContextTO;

public class Translator {

	public Translator() {
		// TODO
	}

	public InternationalDescription translateDescription(ContextTO context, String description) {
		InternationalDescription result = new InternationalDescription();
		result.setDescrDE(description); // TODO
		result.setDescrEN(description); // TODO
		return result;
	}

	public InternationalName translateName(ContextTO context, String name) {
		InternationalName result = new InternationalName();
		result.setNameDE(name); // TODO
		result.setNameEN(name); // TODO
		return result;
	}

	public InternationalShortDescr translateShortDescr(ContextTO context, String shortDescr) {
		InternationalShortDescr result = new InternationalShortDescr();
		result.setDescrDE(shortDescr); // TODO
		result.setDescrEN(shortDescr); // TODO
		return result;
	}
}
