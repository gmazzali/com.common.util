package com.common.util.business.converter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * La interfaz que define el comportamiento b�sico de un convertidor de un objeto a otro dentro del sistema.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public interface ConverterService extends Serializable {

	/**
	 * La funci�n encargada de validar que un objeto de una clase fuente puede ser convertida en un objeto de una clase destino dentro del convertidor
	 * que definimos dentro de este elemento.
	 * 
	 * @param sourceClass
	 *            La clase del objeto fuente.
	 * @param targetClass
	 *            La clase del objeto destino.
	 * @param <S>
	 *            La clase de de origen.
	 * @param <T>
	 *            La clase de destino.
	 * @return TRUE en caso de que el convertidor actual pueda convertir de la clase fuente a la destino, en caso contrario retornamos FALSE.
	 */
	public <S, T> boolean canConvert(Class<S> sourceClass, Class<T> targetClass);

	/**
	 * La funci�n encargada de convertir un objeto de una clase origen en un objeto de otra clase destino.
	 * 
	 * @param source
	 *            El objeto que vamos a ocupar de fuente para la conversi�n.
	 * @param targetClass
	 *            La clase a la que vamos a convertir el objeto fuente.
	 * @param <S>
	 *            La clase de de origen.
	 * @param <T>
	 *            La clase de destino.
	 * @return El objeto de la clase destino que contiene los datos del objeto de la clase fuente.
	 */
	public <S, T> T convert(S source, Class<T> targetClass);

	/**
	 * Se encarga de convertir una colecci�n de elementos de un tipo a una lista de otro tipo.
	 * 
	 * @param collection
	 *            La colecci�n de elementos que vamos a convertir.
	 * @param returnClass
	 *            La clase de los elementos a los que queremos convertir la colecci�n.
	 * @param <S>
	 *            La clase de de origen.
	 * @param <T>
	 *            La clase de destino.
	 * @return El listado con los elementos de la primer colecci�n convertidos.
	 */
	public <S, T> List<T> convert(Collection<S> collection, Class<T> returnClass);
}