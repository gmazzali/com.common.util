package com.common.util.business.converter.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.common.util.business.converter.Converter;
import com.common.util.business.converter.ConverterService;
import com.common.util.business.tool.VerifierUtil;
import com.common.util.business.tool.collection.CollectionUtil;
import com.common.util.business.util.ApplicationLogger;
import com.common.util.domain.exception.BusinessException;

/**
 * La clase que nos permite manipular los conversores que nos ofrece SPRING para convertir entidades entre ellas.
 * 
 * @since 05/02/2014
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ConverterServiceImpl implements ConverterService {

	private static final long serialVersionUID = 1L;

	/**
	 * El mapa que contiene los conversores de acuerdo a la clase origen y la destino.
	 */
	private Map<Class<?>, Map<Class<?>, Converter<?, ?>>> converters;

	/**
	 * Permite inicializar el servicio de conversión.
	 * 
	 * @param convertersSet
	 *            El listado de los converters.
	 * @param <C>
	 *            Los tipos de converters.
	 */
	public <C extends Converter<?, ?>> void init(Collection<C> convertersSet) {
		VerifierUtil.checkNotNull(convertersSet, "The set of converters cannot be null");

		this.converters = new ConcurrentHashMap<Class<?>, Map<Class<?>, Converter<?, ?>>>();
		for (Converter<?, ?> converter : convertersSet) {
			try {
				Class<?> sourceClass = (Class<?>) ((ParameterizedType) converter.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
				Class<?> targetClass = (Class<?>) ((ParameterizedType) converter.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
				Map<Class<?>, Converter<?, ?>> targetMap = this.converters.get(sourceClass);
				if (targetMap == null) {
					targetMap = new ConcurrentHashMap<Class<?>, Converter<?, ?>>();
					this.converters.put(sourceClass, targetMap);
				}
				Converter<?, ?> converterMap = targetMap.get(targetClass);
				if (converterMap == null) {
					targetMap.put(targetClass, converter);
				} else {
					ApplicationLogger.warn(this,
							"Duplicated converter to \"" + sourceClass.getSimpleName() + "\" to \"" + targetClass.getSimpleName() + "\"");
					throw new BusinessException("Duplicated converter to \"" + sourceClass.getSimpleName() + "\" to \"" + targetClass.getSimpleName()
							+ "\"", "base.converter.error.duplicated", sourceClass.getSimpleName(), targetClass.getSimpleName());
				}
			} catch (Exception ex) {
				ApplicationLogger.error(this, "The generics parameters class of the converter service cannot be empty", ex);
				throw new BusinessException("The generics parameters class of the converter service cannot be empty",
						"base.converter.error.parameter.empty");
			}
		}
	}

	@Override
	public <S, T> boolean canConvert(Class<S> sourceClass, Class<T> targetClass) {
		Map<Class<?>, Converter<?, ?>> targetMap = this.converters.get(sourceClass);
		if (targetMap != null) {
			Converter<?, ?> converterMap = targetMap.get(targetClass);
			if (converterMap != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <S, T> T convert(S source, Class<T> targetClass) {
		VerifierUtil.checkNotNull(source, "The source cannot be null");
		VerifierUtil.checkNotNull(targetClass, "The target class cannot be null");

		if (this.canConvert(source.getClass(), targetClass)) {
			return ((Converter<S, T>) this.converters.get(source.getClass()).get(targetClass)).convert(source);
		} else {
			ApplicationLogger.error(this, "Doesn't exist converter between \"" + source.getClass().getSimpleName() + "\" to \"" + targetClass.getSimpleName() + "\"");
			throw new BusinessException("Doesn't exist converter between \"" + source.getClass().getSimpleName() + "\" to \""
					+ targetClass.getSimpleName() + "\"", "base.converter.error.not.exist", source.getClass().getSimpleName(),
					targetClass.getSimpleName());
		}
	}

	@Override
	public <S, T> List<T> convert(Collection<S> collection, Class<T> returnClass) {
		List<T> resultList = new ArrayList<T>();
		if (CollectionUtil.isNotEmpty(collection)) {
			S type = collection.iterator().next();
			if (this.canConvert(type.getClass(), returnClass)) {
				for (S source : collection) {
					resultList.add(this.convert(source, returnClass));
				}
			}
		}
		return resultList;
	}
}