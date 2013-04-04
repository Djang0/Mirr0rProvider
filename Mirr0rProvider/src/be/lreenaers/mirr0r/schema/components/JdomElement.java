package be.lreenaers.mirr0r.schema.components;

import org.jdom.Element;
import org.jdom.Namespace;

import be.lreenaers.mirr0r.schema.interfaces.XmlObject;
/**
 * Inherits from org.jdom.Element, without changing any behavior. The only
 * difference is the implementation of XmlObject interface.
 * @author Ludovic Reenaers
 *
 */
@SuppressWarnings("serial")
public class JdomElement extends Element implements XmlObject{
	/**
	 * Constructor from superclass.
	 * @see org.jdom.Element
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomElement() {
		super();
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @see org.jdom.Element
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomElement(String name) {
		super(name);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param namespace
	 * @see org.jdom.Element
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomElement(String name, Namespace namespace) {
		super(name, namespace);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param uri
	 * @see org.jdom.Element
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomElement(String name, String uri) {
		super(name, uri);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param prefix
	 * @param uri
	 * @see org.jdom.Element
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomElement(String name, String prefix, String uri) {
		super(name, prefix, uri);
	}

}
