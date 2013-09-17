package com.common.util.exception.error;

/**
 * La clase que nos permite definir un error dado un mensaje que describe el mismo y los par�metros que complementan el mensaje.
 * 
 * @author Guillermo Mazzali
 * @version 1.0
 */
public class ErrorDetail {

	/**
	 * El mensaje que describe el error.
	 */
	protected final String message;
	/**
	 * El arreglo de elementos que complementan el detalle del error.
	 */
	protected final Object[] parameters;

	/**
	 * El constructor por copia de un detalle de un error.
	 * 
	 * @param message
	 *            El mensaje que describe el error.
	 * @param parameters
	 *            El listado de los par�metros que detallan el error.
	 */
	public ErrorDetail(String message, Object... parameters) {
		super();
		this.message = message;
		this.parameters = parameters;
	}

	/**
	 * El constructor de un detalle solo con un mensaje de error.
	 * 
	 * @param message
	 *            El mensaje que contiene una descripci�n del error.
	 */
	public ErrorDetail(String message) {
		this(message, (Object[]) null);
	}

	/**
	 * El constructor por omisi�n.
	 */
	public ErrorDetail() {
		this(null, (Object[]) null);
	}

	/**
	 * La funci�n que despliega el detalle de error de la forma:
	 * 
	 * <pre>
	 * message [parameter1, parameter2, etc...]
	 * </pre>
	 * 
	 * @return El mensaje que contiene el detalle formateado.
	 */
	@Override
	public String toString() {
		String parameter = "";
		if (this.parameters != null) {
			parameter = " [";
			for (int index = 0; index < this.parameters.length; index++) {
				parameter += this.parameters[index];

				if (index < this.parameters.length - 1) {
					parameter += ", ";
				}
			}
			parameter = " ]";
		}
		return this.message + " " + parameter;
	}

	/**
	 * La funci�n encargada de retornar el mensaje que contiene el detalle de un error.
	 * 
	 * @return El mensaje de detalle del error.
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * La funci�n encargada de retornar el conjunto de par�metros que recibimos para complementar el detalle del error.
	 * 
	 * @return El conjunto de elementos para complementar el detalle del error.
	 */
	public Object[] getParameters() {
		return this.parameters;
	}
}