package be.lreenaers.mirr0r.components.utils;

import java.awt.Color;
import java.awt.Insets;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import org.jdom.Namespace;

import be.lreenaers.mirr0r.components.Converter;

/**
 * Static Utilities.
 * 
 * @author Ludovic Reenaers
 * @version 1.1
 * @since beta
 */
public class Utils {
	/** Insets with no margin, useful for icon's buttons. */
	public final static Insets noInsets = new Insets(0, 0, 0, 0);
	/** Color object for enabled fields. */
	public final static Color enabled = new Color(255, 255, 255);
	/** Color object for enabled fields. */
	public final static Color disabled = new Color(192, 192, 192);
	/** ResourceBundle Singleton */
	private static ResourceBundle translator;
	/**Dublin Core XML elements namespace 1.1*/
	public final static Namespace DC_NAMESPACE = Namespace.getNamespace("dc","http://purl.org/dc/elements/1.1/");
	/**Dublin Core XML namespace */
	public final static Namespace DCX_NAMESPACE = Namespace.getNamespace("dcx", "http://purl.org/dc/xml/");
	/**Singleton String[] of column names */
	private static String[] columnList;

	private Utils() {

	}
	public static JComboBox getColumnCombo(){
		JComboBox box = new JComboBox();
		for (int i = 0; i < columnList.length; i++) {
			box.addItem(columnList[i]);
		}
		return box;
	}
	
	public static void setColumnList(String[] lst){
		columnList = lst;
	}
	/**
	 * Returns a translator. If Singleton wasn't initiated with :
	 * <code>getTranslator(String country, String language)</code>\n resource
	 * wil be in initiated with default parameters(US english).
	 * 
	 * @return The ResourceBundle translator.
	 * @see ResourceBundle
	 * @since 1.0
	 */
	public static ResourceBundle getTranslator() {
		if (translator == null) {
			setTranslator(null, null);
		}
		return translator;
	}

	/**
	 * Returns a translator. since it is a singleton method, parameters will
	 * only be taken into accounts for the first run of method.
	 * 
	 * @param country
	 *            The country location.
	 * @param language
	 *            The language used for the given country.
	 * @return The ResourceBundle translator.
	 * @see ResourceBundle
	 * @since 1.0
	 */
	public static ResourceBundle getTranslator(String country, String language) {
		if (translator == null) {
			setTranslator(country, language);
		}
		return translator;

	}

	private static void setTranslator(String country, String language) {
		if (country == null || language == null) {
			language = new String("en");
			country = new String("US");
		}
		Locale currentLocale;
		currentLocale = new Locale(language, country);
		translator = ResourceBundle.getBundle("MessagesBundle", currentLocale);
	}

	/**
	 * Returns a given file's extension.
	 * 
	 * @param f
	 *            The file of which the extension is returned.
	 * @return A string representation of the file extension.
	 * @see File
	 * @since beta
	 */
	public static String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}

	/**
	 * Build an Icon that is suitable for JButton usage.
	 * 
	 * @param path
	 *            the path of the Icon picture.
	 * @return An icon usable with JButton
	 * @see javax.swing.JButton
	 * @see ImageIcon
	 * @since beta
	 */
	public static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = Converter.class.getResource(path);
		return new ImageIcon(imgURL);
	}
}
