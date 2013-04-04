package be.lreenaers.mirr0r.schema.components.SimpleDublinCore;

import org.jdom.Element;

import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.components.GenericElement;

public class DCNameElement extends GenericElement {
	public DCNameElement(){
		this.element = new Element("name");
		this.element.setNamespace(Utils.DC_NAMESPACE);
	}
}
