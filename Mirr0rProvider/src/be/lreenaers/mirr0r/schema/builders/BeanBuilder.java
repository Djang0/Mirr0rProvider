package be.lreenaers.mirr0r.schema.builders;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import be.lreenaers.mirr0r.schema.Schema;
import be.lreenaers.mirr0r.schema.beans.SchemaBean;

import com.csvreader.CsvReader;

/**
 * Abstract class that represent a generic BeanBuilder.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public abstract class BeanBuilder {
	ArrayList<SchemaBean> beans = new ArrayList<SchemaBean>();
	CsvReader reader;

	/**
	 * This method is used to invoke a SchemaBean's setter according to its
	 * SchemaAssociation's XML Property (a.k.a method name).
	 * 
	 * @param beansClass
	 *            The full class name of the SchemaBean used.
	 * @param bean
	 *            The SchemaBean itself.
	 * @param methName
	 *            The name of the method to be called (without the "set"
	 *            prefix).
	 * @param value
	 *            The String value to be set using called setter.
	 * @throws ClassNotFoundException
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @see be.lreenaers.mirr0r.schema.SchemaAssociation
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @since beta
	 */
	@SuppressWarnings("unchecked")
	public void invokeSetter(String beansClass, SchemaBean bean,
			String methName, String value) throws ClassNotFoundException,
			SecurityException, NoSuchMethodException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		Class cls = Class.forName(beansClass);
		Method meth = cls.getMethod("set" + methName, String.class);
		meth.invoke(bean, value);

	}

	/**
	 * Returns an ArrayList of SchemaBean that were built.
	 * 
	 * @see be.lreenaers.mirr0r.schema.beans.SchemaBean
	 * @see ArrayList
	 * @since beta
	 */
	public ArrayList<SchemaBean> getBeanList() {
		return this.beans;
	}

	/**
	 * Creates the beans list. To be overrided by concretes class.
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
	};
}
