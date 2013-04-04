package be.lreenaers.mirr0r.schema.interfaces;
/**
 * Represents an XML attribute.
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0 
 *
 */
public interface XmlAttribute extends Swingable, Xmlizable, Nameable {
	/**
	 * Returns the value of the attribute
	 *  
	 * @return
	 * @see XmlValue
	 * @since 1.0
	 */
	public XmlValue getValue();
	/**
	 * Sets the value of the attribute.
	 * 
	 * @param value The value to set for the current attribute.
	 * @since 1.0
	 */
	public void setValue(XmlValue value);
}
