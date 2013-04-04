package be.lreenaers.mirr0r.factories;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import be.lreenaers.mirr0r.schema.Schema;

import com.csvreader.CsvReader;

/**
 * Once SchemaAssociation are set, this factory provides a way to generate beans
 * from SchemaAssocitions. It then serializes it.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public class BeanFactory {
	/**
	 * Allow Class to be accessed in a static way.
	 * 
	 * @param sch
	 *            The Schema instance for which serialization has been
	 *            requested.
	 * @param path
	 *            The path of the directory where serialization will append.
	 * @param reader
	 *            The CsvReader instance that contains serializable data.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void serializeSchema(Schema sch, String path, CsvReader reader)
			throws SecurityException, IllegalArgumentException, IOException,
			ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {
		sch.getBuilder().createBeanList(sch, reader);
		sch.getSerializer().serialize(sch, path);
	}
}
