package be.lreenaers.mirr0r;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import be.lreenaers.mirr0r.components.Converter;

/**
 * Application entry point, runs Mirror converter's GUI.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 */
public class Mirror {
	/**
	 * Application entry point, sets local language and appropriate resource
	 * bundle configuration then runs Mirror converter's GUI.
	 * 
	 * @param args
	 *            Command line arguments.
	 * @see Converter
	 * @see Locale
	 * @see ResourceBundle
	 * @since beta
	 */
	public static void main(String[] args) {
		final String[] ar = args;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI(ar);
			}
		});
	}

	private static Locale setLocals(String[] args) {
		String language;
		String country;
		if (args.length != 2) {
			language = new String("en");
			country = new String("US");
		} else {
			language = new String(args[0]);
			country = new String(args[1]);
		}
		Locale currentLocale;
		currentLocale = new Locale(language, country);
		return currentLocale;

	}

	private static void createAndShowGUI(String[] args) {
		Locale currentLocale = setLocals(args);
		ResourceBundle translator = ResourceBundle.getBundle("MessagesBundle",
				currentLocale);
		JFrame frame = new JFrame(translator.getString("appName") + "-"
				+ translator.getString("appVersion"));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Converter(frame, translator));
		frame.pack();
		frame.setVisible(true);
	}
}
