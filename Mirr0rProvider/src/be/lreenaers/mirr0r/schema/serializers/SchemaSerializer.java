package be.lreenaers.mirr0r.schema.serializers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import be.lreenaers.mirr0r.schema.Schema;
import be.lreenaers.mirr0r.schema.beans.SchemaBean;

/**
 * Abstract class that represents a Schema Serializer. To be overrided by
 * concrete class.
 * 
 * @author Ludovic Reenaers
 * @version 1.0
 * @since beta
 * 
 */
public abstract class SchemaSerializer {

	String path;

	/**
	 * Does nothing! must be overrided by concrete class.
	 * 
	 * @param sch
	 *            The schema to serialize.
	 * @param path
	 *            the path where Serialized files will be stored.
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void serialize(Schema sch, String path) throws SecurityException,
			IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException,
			ClassNotFoundException, IOException {
	}

	@SuppressWarnings("unchecked")
	/**
	 * invoke a SchemaBean's getter giving its name.
	 * 
	 * @param beansClass      SchemaBean's full class name.
	 * @param SchemaBean      The bean itself.
	 * @param methName        The name of the getter without the "get" prefix.
	 * @see SchemaBean
	 * @since beta
	 */
	public String invokeGetter(String beansClass, SchemaBean bean,
			String methName) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, ClassNotFoundException {
		Class cls = Class.forName(beansClass);
		Method meth = cls.getMethod("get" + methName);
		String rs = "";
		try{
		rs = meth.invoke(bean).toString();
		}catch(NullPointerException e){
//			System.out.println("bean null"+bean);
//			System.out.println("meth null"+meth);
//			System.out.println("invoke null "+meth.invoke(bean));
			e.printStackTrace();
		}
		return rs;
	}
}
