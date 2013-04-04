package be.lreenaers.mirr0r.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JTabbedPane;

/**
 * A JTabbedPane which has a close ('X') icon on each tab.
 * 
 * To add a tab, use the method addTab(String, Component)
 * 
 * To have an extra icon on each tab use the method addTab(String, Component,
 * Icon). Only clicking the 'X' closes the tab.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 */
@SuppressWarnings("serial")
public class JTabbedPaneWithCloseIcons extends JTabbedPane implements
		MouseListener {
	/**
	 * A JTabbedPane which has a close ('X') icon on each tab. Extends
	 * JTabbedPane and implements MouseListener.
	 * 
	 * @see JTabbedPane
	 * @see MouseListener
	 * @since beta
	 */
	private Converter parent;

	/**
	 * Only available constructor, build a tabbed pane with close icons.
	 * 
	 * @since beta
	 */
	public JTabbedPaneWithCloseIcons() {
		super();
		addMouseListener(this);
	}

	protected void setParent(Converter comp) {
		this.parent = comp;
	}

	/**
	 * Add simple tab with close icon, enclosing a given component.
	 * 
	 * @param title
	 *            The labeled title of the tab.
	 * @param component
	 *            The tab enclosed component.
	 * @see String
	 * @see Component
	 * @since beta
	 */
	public void addTab(String title, Component component) {

		this.addTab(title, component, null);
	}

	/**
	 * Add simple tab with close icon and an extra icon in the tab's label,
	 * enclosing a given component.
	 * 
	 * @param title
	 *            The labeled title of the tab.
	 * @param component
	 *            The tab enclosed component.
	 * @param extraIcon
	 *            An additional icon.
	 * @see String
	 * @see Component
	 * @see Icon
	 * @since beta
	 */
	public void addTab(String title, Component component, Icon extraIcon) {
		super.addTab(title, new CloseTabIcon(extraIcon), component);
	}

	/**
	 * Handles to close the tab when 'X' icon is clicked.
	 * 
	 * @param e
	 *            The mouse event.
	 * @see MouseEvent
	 * @since beta
	 */
	public void mouseClicked(MouseEvent e) {

		int tabNumber = getUI().tabForCoordinate(this, e.getX(), e.getY());
		if (tabNumber < 0)
			return;
		Rectangle rect = ((CloseTabIcon) getIconAt(tabNumber)).getBounds();
		if (rect.contains(e.getX(), e.getY())) {
			// the tab is being closed
			this.parent.removeSchema(this.getTitleAt(tabNumber));
			this.removeTabAt(tabNumber);
		}
	}

	/**
	 * Not overrided from MouseListener. Does Nothing.
	 * 
	 * @param e
	 *            The mouse event.
	 * @see MouseEvent
	 * @see MouseListener
	 * @since beta
	 */
	public void mouseEntered(MouseEvent e) {

	}

	/**
	 * Not overrided from MouseListener. Does Nothing.
	 * 
	 * @param e
	 *            The mouse event.
	 * @see MouseEvent
	 * @see MouseListener
	 * @since beta
	 */
	public void mouseExited(MouseEvent e) {

	}

	/**
	 * Not overrided from MouseListener. Does Nothing.
	 * 
	 * @param e
	 *            The mouse event.
	 * @see MouseEvent
	 * @see MouseListener
	 * @since beta
	 */
	public void mousePressed(MouseEvent e) {

	}

	/**
	 * Not overrided from MouseListener. Does Nothing.
	 * 
	 * @param e
	 *            The mouse event.
	 * @see MouseEvent
	 * @see MouseListener
	 * @since beta
	 */
	public void mouseReleased(MouseEvent e) {

	}
}

/**
 * The class which generates the 'X' icon for the tabs. The constructor accepts
 * an icon which is extra to the 'X' icon, so you can have tabs like in
 * JBuilder. This value is null if no extra icon is required.
 */
class CloseTabIcon implements Icon {
	private int x_pos;
	private int y_pos;
	private int width;
	private int height;
	private Icon fileIcon;

	/**
	 * Constructor, uses icon to build the extra icon.
	 * 
	 * @param fileIcon
	 *            The extra icon.
	 * @see Icon
	 * @since beta
	 */
	public CloseTabIcon(Icon fileIcon) {

		this.fileIcon = fileIcon;
		width = 16;
		height = 16;
	}

	/**
	 * Paints the 'X' icon at the given position and add potential extra icon.
	 * 
	 * @param c
	 *            The tab enclosed component.
	 * @param g
	 *            A Graphics object for 'x' drawing.
	 * @param x
	 *            The x coordinate of the 'X' icon.
	 * @param y
	 *            The y coordinate of the 'X' icon.
	 * @see Graphics
	 * @see Component
	 * @since beta
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {

		this.x_pos = x;
		this.y_pos = y;

		Color col = g.getColor();

		g.setColor(Color.black);
		int y_p = y + 2;
		int x_p = x;
		g.drawLine(x_p + 1, y_p, x_p + 12, y_p);
		g.drawLine(x_p + 1, y_p + 13, x_p + 12, y_p + 13);
		g.drawLine(x_p, y_p + 1, x_p, y_p + 12);
		g.drawLine(x_p + 13, y_p + 1, x_p + 13, y_p + 12);
		g.drawLine(x_p + 3, y_p + 3, x_p + 10, y_p + 10);
		g.drawLine(x_p + 3, y_p + 4, x_p + 9, y_p + 10);
		g.drawLine(x_p + 4, y_p + 3, x_p + 10, y_p + 9);
		g.drawLine(x_p + 10, y_p + 3, x_p + 3, y_p + 10);
		g.drawLine(x_p + 10, y_p + 4, x_p + 4, y_p + 10);
		g.drawLine(x_p + 9, y_p + 3, x_p + 3, y_p + 9);
		g.setColor(col);
		if (fileIcon != null) {
			fileIcon.paintIcon(c, g, x + width, y_p);
		}
	}

	/**
	 * Returns the extra icon's width.
	 * 
	 * @returns width An integer value for extra icon's width.
	 * @see int
	 * @see Icon
	 * @since beta
	 */
	public int getIconWidth() {

		return width + (fileIcon != null ? fileIcon.getIconWidth() : 0);
	}

	/**
	 * Returns the extra icon's height.
	 * 
	 * @returns width An integer value for extra icon's height.
	 * @see int
	 * @see Icon
	 * @since beta
	 */
	public int getIconHeight() {

		return height;
	}

	/**
	 * Returns the tab's rectangle bounds.
	 * 
	 * @returns rectangle An Rectangle object for representing tab's rectangle
	 *          bounds.
	 * @see Rectangle
	 * @since beta
	 */
	public Rectangle getBounds() {

		return new Rectangle(x_pos, y_pos, width, height);
	}
}