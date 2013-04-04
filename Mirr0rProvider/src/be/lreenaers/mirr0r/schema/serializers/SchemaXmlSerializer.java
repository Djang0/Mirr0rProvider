package be.lreenaers.mirr0r.schema.serializers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

/**
 * Serialize to XML.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 */
public abstract class SchemaXmlSerializer extends SchemaSerializer {
	Document myDocument;
	Element descriptionSet;
	String defaultFileName;
	String defaultExtension;

	/**
	 * Writes the XML output to a given File. If file exists it appends an int
	 * before the extension.
	 * 
	 * @throws IOException
	 * @since beta
	 */
	protected void writeFile() throws IOException {
		XMLOutputter outputter = new XMLOutputter();
		String pth = this.path + this.defaultFileName + this.defaultExtension;
		File f = new File(pth);
		if (f.exists()) {
			int i = 0;
			while (f.exists()) {
				i++;
				f = new File(this.path + this.defaultFileName + i
						+ this.defaultExtension);
			}
			pth = this.path + this.defaultFileName + i + this.defaultExtension;
			f = null;
		}
		FileWriter writer = new FileWriter(pth);
		outputter.output(myDocument, writer);
		writer.close();
	}

}
