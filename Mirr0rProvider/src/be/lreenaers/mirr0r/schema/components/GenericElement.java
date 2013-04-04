package be.lreenaers.mirr0r.schema.components;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.jdom.Attribute;
import org.jdom.Element;

import be.lreenaers.mirr0r.schema.interfaces.XmlAttribute;
import be.lreenaers.mirr0r.schema.interfaces.XmlElement;
import be.lreenaers.mirr0r.schema.interfaces.XmlObject;
import be.lreenaers.mirr0r.schema.interfaces.XmlValue;

public class GenericElement implements XmlElement {
	protected ArrayList<XmlAttribute> attributes;
	protected ArrayList<XmlElement> elements;
	protected XmlValue value;
	protected String name;
	protected Element element;
	protected JPanel panel;

	@Override
	public void addAttribute(XmlAttribute attribute) {
		XmlAttribute tmpAttr = null;
		Iterator<XmlAttribute> iter = this.attributes.iterator();
		while(iter.hasNext()){
			tmpAttr=iter.next();
			if(tmpAttr.getName().equals(attribute.getName())){
				this.attributes.remove(tmpAttr);
				this.attributes.add(attribute);
			}
		}
	}

	@Override
	public void addAttributes(ArrayList<XmlAttribute> attrList) {
		Iterator<XmlAttribute> iterlocal = this.attributes.iterator();
		Iterator<XmlAttribute> iter = attrList.iterator();
		XmlAttribute tmpLocalAttr = null;
		XmlAttribute tmpAttr = null;
		while(iter.hasNext()){
			tmpAttr = iter.next();
			while (iterlocal.hasNext()){
				tmpLocalAttr = iterlocal.next();
				if(tmpAttr.getName().equals(tmpLocalAttr.getName())){
					this.attributes.remove(tmpLocalAttr);
					this.attributes.add(tmpAttr);
				}
			}
		}
		this.attributes.addAll(attributes);

	}

	@Override
	public void addElement(XmlElement element) {
		this.elements.add(element);
	}

	@Override
	public void addElements(ArrayList<XmlElement> elements) {
		this.elements.addAll(elements);
	}

	@Override
	public XmlAttribute getAttribute(String attributeName) {
		XmlAttribute attr = null;
		XmlAttribute tmpAttr = null;
		Iterator<XmlAttribute> iter = this.attributes.iterator();
		while(iter.hasNext()){
			tmpAttr = iter.next();
			if (tmpAttr.getName().equals(attributeName)){
				attr = tmpAttr;
			}
		}
		return attr;
	}

	@Override
	public ArrayList<XmlAttribute> getAttributes() {
		return this.attributes;
	}

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

	@Override
	public ArrayList<XmlElement> getElements() {
		return this.elements;
	}

	@Override
	public XmlValue getValue() {
		return this.value;
	}

	@Override
	public void setValue(XmlValue value) {
		this.value = value;

	}

	@Override
	public JComponent getSwing() {
		Iterator<XmlElement> iterEl = this.elements.iterator();
		Iterator<XmlAttribute> iterAttr = this.attributes.iterator();
		XmlAttribute attr = null;
		XmlElement el = null;
		while(iterAttr.hasNext()){
			attr = iterAttr.next();
			this.panel.add(attr.getSwing());
		}
		while(iterEl.hasNext()){
			el = iterEl.next();
			this.panel.add(el.getSwing());
		}
		this.panel.add(this.value.getSwing());
		return this.panel;
	}

	@Override
	public XmlObject getXml() {
		Iterator<XmlElement> iterEl = this.elements.iterator();
		Iterator<XmlAttribute> iterAttr = this.attributes.iterator();
		XmlAttribute attr = null;
		XmlElement el = null;
		while(iterAttr.hasNext()){
			attr = iterAttr.next();
			this.element.setAttribute((Attribute)attr.getXml());
		}
		while(iterEl.hasNext()){
			el = iterEl.next();
			this.element.addContent((Element)el.getXml());
		}
		this.element.addContent(this.value.getContent());
		return (JdomElement)this.element;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;

	}

}
