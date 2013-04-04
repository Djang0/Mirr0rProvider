package be.lreenaers.mirr0r.factories;

import be.lreenaers.mirr0r.schema.Schema;
import be.lreenaers.mirr0r.schema.builders.SimpleDcBuilder;
import be.lreenaers.mirr0r.schema.serializers.SimpleDublinCoreSerializer;

/**
 * This factory builds Schema instances properly, according to there schema
 * names.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public class SchemaFactory {
	/**
	 * Returns an initiated Schema according to the schema name.
	 * 
	 * @param schemaName
	 *            Name of the schema to initialize.
	 * @return A schema instance
	 * @see Schema
	 * @since beta
	 */
	public static Schema getSchema(String schemaName) {
		Schema schema = new Schema();
		if (schemaName.equals("Dublin Core")) {
			schema.setBuilder(new SimpleDcBuilder());
			schema.setSerializer(new SimpleDublinCoreSerializer());
			schema
					.setBeanClassName("be.lreenaers.mirr0r.schema.beans.SimpleDublinCore");
		}
		return schema;
	}
}
