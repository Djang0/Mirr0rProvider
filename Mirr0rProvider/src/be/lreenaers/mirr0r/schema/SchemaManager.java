package be.lreenaers.mirr0r.schema;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import be.lreenaers.mirr0r.factories.BeanFactory;
import be.lreenaers.mirr0r.factories.SchemaFactory;

import com.csvreader.CsvReader;

/**
 * Manage Schemas and trigger serialization.
 * 
 * @author Ludovic Reenaers
 * @version 1.0.1
 * @since beta
 * 
 */
public class SchemaManager {
	private HashMap<String, Schema> schemaList;
	private List<String> availableSchemasList;
	private String defaultSchemaName;
	private CsvReader reader;

	/**
	 * Constructor.
	 */
	public SchemaManager() {
		this.defaultSchemaName = "Dublin Core";
		this.schemaList = new HashMap<String, Schema>();
		this.availableSchemasList = new ArrayList<String>();
		this.availableSchemasList.add(this.defaultSchemaName);
		this.setupSchema();
	}

	/**
	 * Removes all enable Schema and their associations.
	 * 
	 * @see Schema
	 * @see SchemaAssociation
	 * @since 1.0
	 */
	public void removeAllActiveSchema() {
		this.schemaList.clear();
		this.setupSchema();
	}

	private void setupSchema() {
		this.enableSchema(this.defaultSchemaName, SchemaFactory
				.getSchema(this.defaultSchemaName));
	}

	/**
	 * Completely removes a schema from the logical configuration.
	 * 
	 * @param schName
	 *            The identifier of the schema.
	 * @see Schema
	 * @since beta
	 */
	public void removeSchema(String schName) {
		this.schemaList.remove(schName);
	}

	/**
	 * Adds an empty schema to the logical configuration, giving its name.
	 * 
	 * @param schName
	 *            The logical name of the schema.
	 * @return The identifier of the schema.
	 * @see Schema
	 * @since beta
	 */
	public String addSchema(String schName) {
		return this.enableSchema(schName, SchemaFactory.getSchema(schName));
	}

	/**
	 * Set Accessible a schema giving its name and a new Schema instance.
	 * 
	 * @param schName
	 *            The name of the schema.
	 * @param sch
	 *            The schema itself.
	 * @return The unique schema name (same as tab pane label)
	 * @see Schema
	 * @since beta
	 */
	public String enableSchema(String schName, Schema sch) {
		if (this.schemaList.containsKey(schName)) {
			int i = 1;
			while (this.schemaList.containsKey(schName + i)) {
				i++;
			}
			schName = schName + i;
			this.schemaList.put(schName, sch);

		} else {
			this.schemaList.put(schName, sch);
		}
		return schName;
	}

	/**
	 * Returns the default schema name
	 * 
	 * @return A String representing the default schema name.
	 * @see Schema
	 * @since beta
	 */
	public String getDefaultSchemaName() {
		return this.defaultSchemaName;
	}

	/**
	 * Returns a list of available(for enabling purpose) schema names.
	 * 
	 * @return An Object[] of available name String representations.
	 * @see Schema
	 * @since beta
	 */
	public Object[] getAvailableSchemaNames() {
		return this.availableSchemasList.toArray();
	}

	/**
	 * Returns a list of active schema names.
	 * 
	 * @return A Set of String.
	 * @see Set
	 * @see String
	 * @since beta
	 * 
	 */
	public Set<String> getSchemaNames() {
		return this.schemaList.keySet();
	}

	/**
	 * Sets the CsvReader to be used for serialization.
	 * 
	 * @param reader
	 *            A CsvReader
	 * @see CsvReader
	 * @since beta
	 */
	public void setReader(CsvReader reader) {
		this.reader = reader;
	}

	/**
	 * Triggers Schema(s) serialization.
	 * 
	 * @param path
	 *            The path serialized files will be stored.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @since beta
	 * 
	 */
	public void serializeSchemas(String path) throws SecurityException,
			IllegalArgumentException, IOException, ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Iterator<Schema> schs = this.schemaList.values().iterator();
		while (schs.hasNext()) {
			Schema sch = schs.next();
			BeanFactory.serializeSchema(sch, path, this.reader);
		}
	}

	/**
	 * Returns a schema giving its name.
	 * 
	 * @param name
	 *            Unique name of the schema.
	 * @return A Schema.
	 * @see Schema
	 * @see String
	 * @since beta
	 */
	public Schema getSchema(String name) {
		return this.schemaList.get(name);
	}

	/**
	 * Sets an XML property of an association of a schema.
	 * 
	 * @param schemaName
	 *            The unique name of the Schema
	 * @param associationName
	 *            The unique name of the Association.
	 * @param xmlFieldName
	 *            The property value.
	 * @see Schema
	 * @see SchemaAssociation
	 * @since beta
	 */
	public void putXmlAssociation(String schemaName, String associationName,
			String xmlFieldName) {
		
		this.getSchema(schemaName).putXmlAssociation(associationName,
				xmlFieldName);
	}
	/**
	 * Sets a Common value for an association of a schema.
	 * 
	 * @param schemaName
	 *            The unique name of the Schema
	 * @param associationName
	 *            The unique name of the Association.
	 * @param value
	 *            The property value.
	 * @see Schema
	 * @see SchemaAssociation
	 * @since 1.0
	 */
	public void putCommonAssociation(String schemaName, String associationName,
			String value) {
		this.getSchema(schemaName).putCommonAssociation(associationName,
				value);
	}

	/**
	 * Sets an CSV property of an association of schema.
	 * 
	 * @param schemaName
	 *            The unique name of the Schema
	 * @param associationName
	 *            The unique name of the Association.
	 * @param csvCol
	 *            The property value.
	 * @see Schema
	 * @see SchemaAssociation
	 * @since beta
	 */
	public void putCsvAssociation(String schemaName, String associationName,
			String csvCol) {
		this.getSchema(schemaName).putCsvAssociation(associationName, csvCol);
	}
}
