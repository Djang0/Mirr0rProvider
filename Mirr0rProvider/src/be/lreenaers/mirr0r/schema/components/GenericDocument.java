package be.lreenaers.mirr0r.schema.components;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jdom.Document;
import org.jdom.Element;

import be.lreenaers.mirr0r.schema.interfaces.XmlDocument;
import be.lreenaers.mirr0r.schema.interfaces.XmlElement;
import be.lreenaers.mirr0r.schema.interfaces.XmlObject;

public class GenericDocument implements XmlDocument {
	protected ArrayList<XmlElement> elements;
	protected Element rootEl;
	protected Document doc;
	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.XmlDocument#addElement(be.lreenaers.mirr0r.schema.interfaces.XmlElement)
	 */
	@Override
	public void addElement(XmlElement element) {
		this.elements.add(element);
	}

	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.XmlDocument#addElements(java.util.ArrayList)
	 */
	@Override
	public void addElements(ArrayList<XmlElement> elements) {
		this.elements.addAll(elements);
	}

	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.XmlDocument#getElement(java.lang.String)
	 */
	@Override
	public ArrayList<XmlElement> getElement(String elementName) {
		ArrayList<XmlElement> ells = new ArrayList<XmlElement>();
		Iterator<XmlElement> iter = this.elements.iterator();
		XmlElement el;
		while(iter.hasNext()){
			el = iter.next();
			if(!el.getName().equals(elementName)){
				ells.add(el);
			}
		}
		return ells;
	}

	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.XmlDocument#getElements()
	 */
	@Override
	public ArrayList<XmlElement> getElements() {
		return this.elements;
	}

	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.Swingable#getSwing()
	 */
	@Override
	public JComponent getSwing() {
		JPanel panel = new JPanel();
		Iterator<XmlElement> iter = this.elements.iterator();
		XmlElement el;
		while(iter.hasNext()){
			el = iter.next();
			panel.add(el.getSwing());
		}
		return panel;
	}

	/* (non-Javadoc)
	 * @see be.lreenaers.mirr0r.schema.interfaces.Xmlizable#getXml()
	 */
	@Override
	public XmlObject getXml() {
		Iterator<XmlElement> iter = this.elements.iterator();
		XmlElement el;
		while(iter.hasNext()){
			el = iter.next();
			this.rootEl.addContent((Element)el.getXml());
		}
		return (JdomDocument)this.doc;
	}

}
