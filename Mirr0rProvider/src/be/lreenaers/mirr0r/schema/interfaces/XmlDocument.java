package be.lreenaers.mirr0r.schema.interfaces;

import java.util.ArrayList;
/**
 * Represents an XML document.
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 *
 */
public interface XmlDocument extends Swingable, Xmlizable {
	/**
	 * Returns a list of XML elements contained in the document.
	 * @return An ArrayList of XmlElement
	 * @see XmlElement
	 * @since 1.0
	 */
	public ArrayList<XmlElement> getElements();
	/**
	 * Returns a list of elements, giving there name.
	 * @param elementName The name of elements.
	 * @return The requested list of XmlElement.
	 * @see XmlElement
	 * @since 1.0
	 */
	public ArrayList<XmlElement> getElement(String elementName);
	/**
	 * Adds a list of elements to the current document.
	 * @param elements The list of elements to add.
	 * @see XmlElements
	 * @since 1.0
	 */
	public void addElements(ArrayList<XmlElement> elements);
	/**
	 * Adds an element to the current document.
	 * @param element The element to add.
	 * @see XmlElement
	 * @since 1.0
	 */
	public void addElement(XmlElement element);
	
}
