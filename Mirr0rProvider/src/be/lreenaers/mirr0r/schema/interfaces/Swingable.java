package be.lreenaers.mirr0r.schema.interfaces;

import javax.swing.JComponent;

/**
 * Provides a way to build recursively (decorator pattern) a swing UI according
 * to the XML structure.
 * 
 * @author Ludovic Reenaers
 * @version 1.0 
 * @since 1.0
 * 
 */
public interface Swingable {
	/**
	 * Returns the swing component to add to the GUI.
	 * @return JComponent representing the implementer.
	 * @since 1.0 
	 */
	public JComponent getSwing();
}
