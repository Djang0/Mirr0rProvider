package be.lreenaers.mirr0r.schema;

/**
 * Associate a CSV Column to an XML element/attribute.
 * 
 * @author Ludovic Reenaers
 * @version 1.0.1
 * @since beta
 * 
 */
public class SchemaAssociation {
	private String xml;
	private String csv;
	private String commonValue;
	private boolean isSet;

	/**
	 * Constructor.
	 */
	public SchemaAssociation() {
		this.isSet = false;
	}

	/**
	 * Returns the XML property of the association.
	 * 
	 * @return A String representing XML element/attribute
	 * @see String
	 * @since beta
	 */
	public String getXml() {
		return xml;
	}

	/**
	 * Sets the XML property of the association.
	 * 
	 * @param xml
	 *            the XML element/attribute of the association
	 * @see String
	 * @since beta
	 * 
	 */
	public void setXml(String xml) {
		this.xml = xml;
		if (this.csv != null || this.commonValue != null ) {
			this.isSet = true;
		}
	}
	/**
	 * Returns the commonValue property. Which is a value that will be used
	 * in every record for a given XML property
	 * 
	 * @return A String representing the commonValue
	 * @see String
	 * @since 1.0
	 */
	public String getCommonValue() {
		return commonValue;
	}
	/**
	 * Sets the commonValue property. Which is a value that will be used
	 * in every record for a given XML property
	 * 
	 * @param commonValue   The Value to set
	 * @see String
	 * @since 1.0
	 */
	public void setCommonValue(String commonValue) {
		if(this.xml != null){
			this.isSet = true;
		}
		this.commonValue = commonValue;
		this.csv = null;
	}

	/**
	 * Returns the CSV property of the association.
	 * 
	 * @return A String representing CSV Column.
	 * @see String
	 * @since beta
	 */
	public String getCsv() {
		return csv;
	}

	/**
	 * Sets the CSV property of the association.
	 * 
	 * @param csv
	 *            the CSV Column of the association
	 * @see String
	 * @since beta
	 * 
	 */
	public void setCsv(String csv) {
		this.csv = csv;
		this.commonValue = null;
		if (this.xml != null) {
			this.isSet = true;
		}
	}

	/**
	 * Tells if the association is set and ready for use.
	 * 
	 * @return <code>true</code> if the association is ready and
	 *         <code>false</code> if not.
	 */
	public boolean getIsSet() {
		return isSet;
	}
}
