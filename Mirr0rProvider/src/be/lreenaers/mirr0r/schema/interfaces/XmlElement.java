package be.lreenaers.mirr0r.schema.interfaces;

import java.util.ArrayList;
/**
 * The representation of an element. Provides way to serialize XmlObjects to XML an to swing components. 
 * @author Ludovic Reenaers
 * @version 1.0 
 * @since 1.0
 *
 */
public interface XmlElement extends Swingable, Xmlizable, Nameable {
	/**
	 * Returns a list of attributes, if there are.
	 * @return an ArrayList of XmlAttribute.
	 * @see XmlAttribute
	 * @since 1.0
	 */
	public ArrayList<XmlAttribute> getAttributes();
	/**
	 * Returns a list of children elements, if there are.
	 * @return an ArrayList of XmlElement.
	 * @see XmlElement
	 * @since 1.0
	 */
	public ArrayList<XmlElement> getElements();
	/**
	 * Returns a list of children elements giving their name.
	 * @param elementName The name of requested elements.
	 * @return the requested list of XmlElement
	 * @see XmlElement
	 * @since 1.0
	 */
	public ArrayList<XmlElement> getElement(String elementName);
	/**
	 * Returns an attribute giving its name.
	 * @param attributeName The name of the requested attribute.
	 * @return the requested XmlAttribute
	 * @see XmlAttribute
	 * @since 1.0
	 */
	public XmlAttribute getAttribute(String attributeName);
	/**
	 * Adds a list of children elements to the current element.
	 * @param elements An ArrayList of XmlElements
	 * @see XmlElement
	 * @since 1.0
	 */
	public void addElements(ArrayList<XmlElement> elements);
	/**
	 * Adds a child element to the current element.
	 * @param element The XmlElement
	 * @see XmlElement
	 * @since 1.0
	 */
	public void addElement(XmlElement element);
	/**
	 * Adds a list of attributes to the current element.
	 * @param attributes An ArrayList of XmlAttributes
	 * @see XmlAttribute
	 * @since 1.0
	 */
	public void addAttributes(ArrayList<XmlAttribute> attrList);
	/**
	 * Adds a child attribute to the current element.
	 * @param attribute The XmlAttribute
	 * @see XmlAttribute
	 * @since 1.0
	 */
	public void addAttribute(XmlAttribute attribute);
	/**
	 * Returns the value of the element.
	 * @return The only  XmlValue object belonging to the element.
	 * @see XmlValue
	 * @since 1.0
	 */
	public XmlValue getValue();
	/**
	 * Sets the value of the content;
	 * @param value the String value
	 * @since 1.0
	 */
	public void setValue(XmlValue value);
	
}
