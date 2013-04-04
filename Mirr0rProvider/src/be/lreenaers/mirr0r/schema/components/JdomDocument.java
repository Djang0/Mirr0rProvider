package be.lreenaers.mirr0r.schema.components;

import java.util.List;

import org.jdom.DocType;
import org.jdom.Document;
import org.jdom.Element;

import be.lreenaers.mirr0r.schema.interfaces.XmlObject;

/**
 * Inherits from org.jdom.Document, without changing any behavior. The only
 * difference is the implementation of XmlObject interface.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings("serial")
public class JdomDocument extends Document implements XmlObject {
	/**
	 * Constructor from superclass.
	 * @see org.jdom.Document
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomDocument() {
	}

	/**
	 * Constructor from superclass.
	 * 
	 * @param rootElement
	 * @see org.jdom.Document
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomDocument(Element rootElement) {
		super(rootElement);
	}

	/**
	 * Constructor from superclass.
	 * 
	 * @param content
	 * @see org.jdom.Document
	 * @see XmlObject
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	public JdomDocument(List content) {
		super(content);
	}

	/**
	 * Constructor from superclass.
	 * 
	 * @param rootElement
	 * @param docType
	 * @see org.jdom.Document
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomDocument(Element rootElement, DocType docType) {
		super(rootElement, docType);
	}

	/**
	 * Constructor from superclass.
	 * 
	 * @param rootElement
	 * @param docType
	 * @param baseURI
	 * @see org.jdom.Document
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomDocument(Element rootElement, DocType docType, String baseURI) {
		super(rootElement, docType, baseURI);
	}

}
