package be.lreenaers.mirr0r.schema.serializers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;

import be.lreenaers.mirr0r.schema.Schema;
import be.lreenaers.mirr0r.schema.SchemaAssociation;
import be.lreenaers.mirr0r.schema.beans.SchemaBean;
import be.lreenaers.mirr0r.schema.beans.SimpleDublinCore;

/**
 * Serializes {@link SimpleDublinCore} beans.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public class SimpleDublinCoreSerializer extends SchemaXmlSerializer {

	Namespace DC_NAMESPACE;
	Namespace DCX_NAMESPACE;

	/**
	 * Constructor.
	 */
	public SimpleDublinCoreSerializer() {
		this.path = "";
		this.defaultFileName = "Simple_DC";
		this.defaultExtension = ".xml";
	}

	/**
	 * Does nothing! must be overrided by concrete class.
	 * 
	 * @param sch
	 *            The schema to serialize.
	 * @param path
	 *            the path where Serialized files will be stored.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void serialize(Schema sch, String path) throws SecurityException,
	IllegalArgumentException, NoSuchMethodException,
	IllegalAccessException, InvocationTargetException,
	ClassNotFoundException, IOException {
		this.path = path;
		this.setUpSerializer();
		Iterator<SchemaBean> iter = sch.getBuilder().getBeanList().iterator();
		while (iter.hasNext()) {
			SimpleDublinCore dc = (SimpleDublinCore) iter.next();
			Iterator<String> keys = sch.getAssociationNames().iterator();
			Element description = new Element("description");
			description.setNamespace(DCX_NAMESPACE);
			while (keys.hasNext()) {
				SchemaAssociation assoc = sch.getAssociation(keys.next());
				Element item = new Element(assoc.getXml());
				item.setNamespace(DC_NAMESPACE);
				item.addContent(this.invokeGetter(sch.getBeanClassName(), dc,
						assoc.getXml()));
				description.addContent(item);
			}
			descriptionSet.addContent(description);
		}
		this.writeFile();
	}

	private void setUpSerializer() {

		DC_NAMESPACE = Namespace.getNamespace("dc",
		"http://purl.org/dc/elements/1.1/");
		DCX_NAMESPACE = Namespace
		.getNamespace("dcx", "http://purl.org/dc/xml/");
		this.createDocument();
	}

	private void createDocument() {
		this.createRootElement();
		myDocument = new Document(descriptionSet);

	}

	private void createRootElement() {
		descriptionSet = new Element("descriptionSet");
		descriptionSet.setNamespace(DCX_NAMESPACE);
		descriptionSet.addNamespaceDeclaration(DC_NAMESPACE);
	}
}
