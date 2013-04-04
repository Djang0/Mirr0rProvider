package be.lreenaers.mirr0r.components.utils;

import java.io.File;

import javax.swing.filechooser.FileFilter;


/**
 * This class is a file filter for JFileChooser to display only selectable
 * files, extends FileFilter.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 */
public class CsvFileFilter extends FileFilter {
	String info;

	/**
	 * Constructor.
	 * 
	 * @param Info
	 *            Will appear in the file format selection combobox of the
	 *            JFileChooser.
	 * @see String
	 * @see javax.swing.JFileChooser
	 * @see FileFilter
	 * @since beta
	 */
	public CsvFileFilter(String Info) {
		this.info = Info;
	}

	/**
	 * Returns if the file is acceptable for this filter (here: .csv)
	 * 
	 * @param f
	 *            The file to check compatibility.
	 * @see File
	 * @since beta
	 */
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);
		if (extension != null) {
			if (extension.equals("csv")) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	/**
	 * Returns the description the filter.
	 * 
	 * @return A string representation of the filter.
	 */
	public String getDescription() {
		return this.info;
	}
}
