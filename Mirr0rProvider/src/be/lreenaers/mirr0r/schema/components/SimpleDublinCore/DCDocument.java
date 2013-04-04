/**
 * 
 */
package be.lreenaers.mirr0r.schema.components.SimpleDublinCore;

import java.util.ArrayList;

import org.jdom.Document;
import org.jdom.Element;

import be.lreenaers.mirr0r.components.utils.Utils;
import be.lreenaers.mirr0r.schema.components.GenericDocument;
import be.lreenaers.mirr0r.schema.components.GenericValue;
import be.lreenaers.mirr0r.schema.interfaces.XmlElement;

/**
 * A Dublin Core document.
 * @author Ludovic Reenaers
 * @since 1.0 
 * @version 1.0
 */
public class DCDocument extends GenericDocument {
	
	/**
	 * Default constructor.
	 * @since 1.0
	 */
	public DCDocument(){
		this.elements = new ArrayList<XmlElement>();
		this.rootEl = new Element("descriptionSet");
		this.rootEl.setNamespace(Utils.DCX_NAMESPACE);
		this.rootEl.addNamespaceDeclaration(Utils.DC_NAMESPACE);
		this.doc = new Document(this.rootEl);
		DCXDescriptionElement dcxDesc = new DCXDescriptionElement();
		DCNameElement dcName = new DCNameElement();
		GenericValue val = new GenericValue();
		dcName.setValue(val);
		dcxDesc.addElement(dcName);
		this.elements.add(dcxDesc);
		
	//	this.elements.add(e);
	}
	

}
