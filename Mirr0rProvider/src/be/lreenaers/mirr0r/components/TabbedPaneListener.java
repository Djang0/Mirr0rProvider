package be.lreenaers.mirr0r.components;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This listener is listening to JTabbedPaneWithCloseIcons, for status changeds.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 */
public class TabbedPaneListener implements ChangeListener {
	Converter parent;
	int tabCount;

	/**
	 * Constructor of the Listener.
	 * 
	 * @param parent
	 *            Mirror Converter's main Panel
	 * @see ChangeListener
	 * @see Converter
	 * @since beta
	 */
	public TabbedPaneListener(Converter parent) {
		this.parent = parent;
		this.tabCount = 0;
	}

	/**
	 * This method is called when tab selection has changed, it ensures that the
	 * main application panel is aware of the selected tab info.
	 * 
	 * @param e
	 *            The ChangeEvent itself.
	 * @see ChangeEvent
	 * @since beta
	 */
	public void stateChanged(ChangeEvent e) {
		try {
			JTabbedPaneWithCloseIcons source = (JTabbedPaneWithCloseIcons) e
					.getSource();
			this.parent.setSelectedTabIndex(source.getSelectedIndex());
			this.parent.setSelectedTabName(source.getTitleAt(source
					.getSelectedIndex()));
		} catch (Exception e1) {

		}
	}
}
