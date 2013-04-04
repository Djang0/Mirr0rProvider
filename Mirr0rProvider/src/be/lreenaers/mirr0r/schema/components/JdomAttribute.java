package be.lreenaers.mirr0r.schema.components;

import org.jdom.Attribute;
import org.jdom.Namespace;

import be.lreenaers.mirr0r.schema.interfaces.XmlObject;

/**
 * Inherits from org.jdom.Attribute, without changing any behavior. The only
 * difference is the implementation of XmlObject interface.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since 1.0
 * 
 */
@SuppressWarnings("serial")
public class JdomAttribute extends Attribute implements XmlObject {
	/**
	 * Constructor from superclass.
	 * @see org.jdom.Attribute
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomAttribute() {
		super();
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param value
	 * @see org.jdom.Attribute
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomAttribute(String name, String value) {
		super(name, value);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param value
	 * @param namespace
	 * @see org.jdom.Attribute
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomAttribute(String name, String value, Namespace namespace) {
		super(name, value, namespace);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param value
	 * @param type
	 * @see org.jdom.Attribute
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomAttribute(String name, String value, int type) {
		super(name, value, type);
	}
	/**
	 * Constructor from superclass.
	 * @param name
	 * @param value
	 * @param type
	 * @param namespace
	 * @see org.jdom.Attribute
	 * @see XmlObject
	 * @since 1.0
	 */
	public JdomAttribute(String name, String value, int type,
			Namespace namespace) {
		super(name, value, type, namespace);
	}

}
