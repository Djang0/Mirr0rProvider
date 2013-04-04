package be.lreenaers.mirr0r.schema.builders;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import be.lreenaers.mirr0r.schema.Schema;
import be.lreenaers.mirr0r.schema.SchemaAssociation;
import be.lreenaers.mirr0r.schema.beans.SchemaBean;
import be.lreenaers.mirr0r.schema.beans.SimpleDublinCore;

import com.csvreader.CsvReader;

/**
 * Concrete implementation of the BeanBuilder Abstract class. This
 * implementation suits for Simple Dublin Core serialization.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public class SimpleDcBuilder extends BeanBuilder {
	/**
	 * Creates the beans list. Overrided from BeanBuilder abstract class.
	 * 
	 * @param sch
	 *            The SchemaBean used.
	 * @param reader
	 *            The CsvReader that contains serializable data.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @see CsvReader
	 * @see SchemaBean
	 * @since beta
	 */
	public void createBeanList(Schema sch, CsvReader reader)
			throws SecurityException, IllegalArgumentException, IOException,
			ClassNotFoundException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException {

		while (reader.readRecord()) {
			SimpleDublinCore dc = new SimpleDublinCore();
			Iterator<String> it = sch.getAssociationNames().iterator();
			while (it.hasNext()) {
				String name = it.next();
				SchemaAssociation assoc = sch.getAssociation(name);
				if (assoc.getCsv()== null){
					this.invokeSetter(sch.getBeanClassName(), dc, assoc.getXml(),
							assoc.getCommonValue());
				}else{
					this.invokeSetter(sch.getBeanClassName(), dc, assoc.getXml(),
							reader.get(assoc.getCsv()));
				}
			}
			this.beans.add(dc);
		}
	}
}
