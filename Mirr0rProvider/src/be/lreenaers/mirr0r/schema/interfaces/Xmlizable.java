package be.lreenaers.mirr0r.schema.interfaces;

/**
 * Implementing this interface means that the implementer will have to provide a way to generate XML.
 *   
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 */
public interface Xmlizable {
	/**
	 * Generic method to get the XML facilities object.
	 * 
	 * @return A XmlObject which is a common type for Any kind of XML facility object, depending on the chosen implementation.
	 * @see XmlObject
	 * @since 1.0
	 */
	public XmlObject getXml();
}
