package be.lreenaers.mirr0r.schema;

import java.util.HashMap;
import java.util.Set;

import be.lreenaers.mirr0r.schema.builders.BeanBuilder;
import be.lreenaers.mirr0r.schema.serializers.SchemaSerializer;

/**
 * A Schema is the logical representation of a bean. It contains
 * SchemaAssociations that are actually mappings of Csv columns to xml element.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public class Schema {
	private String beanClassName;
	private BeanBuilder builder;
	private SchemaSerializer ser;
	private HashMap<String, SchemaAssociation> assoc;

	/**
	 * Constructor.
	 */
	public Schema() {
		this.assoc = new HashMap<String, SchemaAssociation>();
	}

	/**
	 * Returns a list of string representing association names(e.g : ass1).
	 * 
	 * @return A Set of string.
	 * @see Set
	 * @see String
	 * @since beta
	 */
	public Set<String> getAssociationNames() {
		return this.assoc.keySet();
	}

	/**
	 * Return a SchemaAssociation, giving its name.
	 * 
	 * @param name
	 *            The name of the SchemaAssociation.
	 * @return A SchemaAssociation.
	 * @see SchemaAssociation
	 * @since beta
	 */
	public SchemaAssociation getAssociation(String name) {
		return this.assoc.get(name);
	}

	/**
	 * Remove a SchemaAssociation from the current Schema, giving its name.
	 * 
	 * @param name
	 *            The name of the SchemaAssociation.
	 * @see SchemaAssociation
	 * @since beta
	 */
	public void removeAssociation(String name) {
		this.assoc.remove(name);

	}

	/**
	 * Remove All SchemaAssociation contained in the current Schema.
	 * 
	 * @see SchemaAssociation
	 * @since beta
	 */
	public void removeAllAssociations() {
		this.assoc.clear();
	}

	/**
	 * Sets the XML property of a SchemaAssociation. If the association does not
	 * exists, it creates it.
	 * 
	 * @param name
	 *            The name of the SchemaAssociation.
	 * @param xmlProp
	 *            The value of the XML property (a.k.a element/attribute name)of
	 *            the association.
	 * @see SchemaAssociation
	 * @since beta
	 * 
	 */
	public void putXmlAssociation(String name, String xmlProp) {
		if (this.assoc.containsKey(name)) {
			SchemaAssociation sch = this.assoc.get(name);
			sch.setXml(xmlProp);
			this.assoc.put(name, sch);
		} else {
			SchemaAssociation association = new SchemaAssociation();
			association.setXml(xmlProp);
			this.assoc.put(name, association);
		}
	}
	/**
	 * Sets the common value of a SchemaAssociation. If the association does not
	 * exists, it creates it.
	 * 
	 * @param name
	 *            The name of the SchemaAssociation.
	 * @param value
	 *            The value of the value of
	 *            the association.
	 * @see SchemaAssociation
	 * @since beta
	 * 
	 */
	public void putCommonAssociation(String name, String value) {
		if (this.assoc.containsKey(name)) {
			SchemaAssociation sch = this.assoc.get(name);
			sch.setCommonValue(value);
			this.assoc.put(name, sch);
		} else {
			SchemaAssociation association = new SchemaAssociation();
			association.setCommonValue(value);
			this.assoc.put(name, association);
		}
	}

	/**
	 * Sets the CSV property of a SchemaAssociation. If the association does not
	 * exists, it creates it.
	 * 
	 * @param name
	 *            The name of the SchemaAssociation.
	 * @param csvCol
	 *            The value of the CSV property (a.k.a Column name)of the
	 *            association.
	 * @see SchemaAssociation
	 * @since beta
	 * 
	 */
	public void putCsvAssociation(String name, String csvCol) {
		if (this.assoc.containsKey(name)) {
			SchemaAssociation sch = this.assoc.get(name);
			sch.setCsv(csvCol);
			this.assoc.put(name, sch);
		} else {
			SchemaAssociation association = new SchemaAssociation();
			association.setCsv(csvCol);
			this.assoc.put(name, association);
		}
	}

	/**
	 * Returns the full class name of the bean that will be used for data
	 * mapping before serialization.
	 * 
	 * @return A String representing the SchemaBean's full class name.
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @see String
	 * @since beta
	 */
	public String getBeanClassName() {
		return beanClassName;
	}

	/**
	 * Sets the full class name of the bean that will be used for data mapping
	 * before serialization.
	 * 
	 * @param beanClassName
	 *            The full Class name of the SchemaBean.
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @see String
	 * @since beta
	 */
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;
	}

	/**
	 * Returns the builder that will be used by the factory, for SchemaBean
	 * creation purpose.
	 * 
	 * @return A compatible BeanBuilder.
	 * @see BeanBuilder
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @see be.lreenaers.mirr0r.factories.BeanFactory
	 * @since beta
	 */
	public BeanBuilder getBuilder() {
		return builder;
	}

	/**
	 * Sets the builder that will be used by the factory, for SchemaBean
	 * creation purpose.
	 * 
	 * @param builder
	 *            The BeanBuilder.
	 * @see BeanBuilder
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @see be.lreenaers.mirr0r.factories.BeanFactory
	 * @since beta
	 */
	public void setBuilder(BeanBuilder builder) {
		this.builder = builder;
	}

	/**
	 * Returns the serializer that will be used by the factory, for SchemaBeans
	 * serializing purpose.
	 * 
	 * @return A compatible BeanSerializer
	 * @see SchemaSerializer
	 * @see be.lreenaers.mirr0r.factories.BeanFactory
	 * @since beta
	 */
	public SchemaSerializer getSerializer() {
		return ser;
	}

	/**
	 * Sets the serializer that will be used by the factory, for SchemaBeans
	 * serializing purpose.
	 * 
	 * @param ser
	 *            The SchemaSerializer.
	 * @see SchemaSerializer
	 * @see be.lreenaers.mirr0r.factories.BeanFactory
	 * @since beta
	 */
	public void setSerializer(SchemaSerializer ser) {
		this.ser = ser;
	}
}
