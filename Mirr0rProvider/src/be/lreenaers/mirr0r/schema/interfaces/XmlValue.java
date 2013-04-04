package be.lreenaers.mirr0r.schema.interfaces;

/**
 * Generic XML value object. Real classes will have to implement Swingable and
 * Xmlizable as well.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 * 
 */
public interface XmlValue extends Swingable, Xmlizable {
	/**
	 * Returns the content of the value object.
	 * 
	 * @return The applicable content of the value object. More specifically, in
	 *         case of a multiple value object, the value returned will the
	 *         selected one.
	 * @see Swingable
	 * @see Xmlizable
	 * @since 1.0
	 */
	public String getContent();
	/**
	 * Sets the content of the value object.
	 * 
	 * @see Swingable
	 * @see Xmlizable
	 * @since 1.0
	 */
	public void setContent(String value);
}
