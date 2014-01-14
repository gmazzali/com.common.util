package com.common.util.holder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * La clase encargada de mantener el contexto de la aplicaci�n al alcance de toda la aplicaci�n.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class HolderApplicationContext {

	/**
	 * El contexto de la aplicaci�n para manejar Spring.
	 */
	private static ApplicationContext context;

	/**
	 * La funci�n encargada de inicializar el contexto de la aplicaci�n dado un conjunto de ubicaciones de los archivos de configuraci�n para Spring.
	 * 
	 * @param locations
	 *            El conjunto de ubicaciones de los archivos de configuraci�n para Spring.
	 */
	public static ApplicationContext initApplicationContext(String[] locations) {
		HolderApplicationContext.context = new ClassPathXmlApplicationContext(locations);
		return HolderApplicationContext.context;
	}

	/**
	 * La funci�n encargada de retornar el contexto de la aplicaci�n para que pueda usarse dentro del sistema.
	 * 
	 * @return El contexto de la aplicaci�n actual.
	 */
	public static ApplicationContext getContext() {
		return HolderApplicationContext.context;
	}

	/**
	 * La funci�n encargada de retornar el beans que corresponde con el nombre que recibimos.
	 * 
	 * @param beanName
	 *            El nombre del bean que queremos recuperar desde el contexto.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static Object getBean(String beanName) {
		return HolderApplicationContext.context.getBean(beanName);
	}

	/**
	 * La funci�n encargada de retornar el beans que corresponde con el tipo que recibimos.
	 * 
	 * @param beanClass
	 *            La clase que define el tipo del bean que queremos recuperar desde el contexto.
	 * @return El elemento que corresponde con el bean que recuperamos.
	 */
	public static <E> E getBean(Class<E> beanClass) {
		return beanClass.cast(HolderApplicationContext.context.getBean(beanClass));
	}
}