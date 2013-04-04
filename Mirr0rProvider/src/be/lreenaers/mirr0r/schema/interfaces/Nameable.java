package be.lreenaers.mirr0r.schema.interfaces;
/**
 * Provides a way to name objects.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 *
 */
public interface Nameable {
	/**
	 * Returns the name of the object.
	 * @return The name of the object.
	 * @since 1.0
	 */
	public String getName();
	/**
	 * Sets the name of the object.
	 * @param name The name to set.
	 * @since 1.0
	 */
	public void setName(String name);
}
