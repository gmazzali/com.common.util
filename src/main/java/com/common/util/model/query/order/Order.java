package com.common.util.model.query.order;

/**
 * La enumeraci�n que determina los ordenes en los que vamos a poder ordenar los atributos de una consulta.
 * 
 * <ul>
 * <li>{@link Order#ASC}</li>
 * <li>{@link Order#DESC}</li>
 * </ul>
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public enum Order {
	/**
	 * Determina un orden ascendente del atributo.
	 */
	ASC,
	/**
	 * Determina un orden descendente del atributo.
	 */
	DESC;
}