package be.lreenaers.mirr0r.schema.components.SimpleDublinCore;

import org.jdom.Element;

import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.components.GenericElement;

public class DCXDescriptionElement extends GenericElement {
	public DCXDescriptionElement(){
		this.element = new Element("description");
		this.element.setNamespace(Utils.DCX_NAMESPACE);
	}
}
