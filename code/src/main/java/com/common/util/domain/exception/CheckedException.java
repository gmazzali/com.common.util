package com.common.util.domain.exception;

import com.common.util.domain.model.log.Log;

/**
 * The checked exceptions.
 * 
 * @see UncheckedException
 * @see Log
 * 
 * @since 14/03/2017
 * @author Guillermo S. Mazzali
 * @version 1.0
 */
public class CheckedException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * The LOGs of this exception.
	 */
	protected Log log;

	/**
	 * The default constructor.
	 */
	public CheckedException() {
		super();
		this.log = new Log();
	}

	/**
	 * The constructor of an {@link CheckedException}.
	 * 
	 * @param cause
	 *            The cause of the problem inside this exception.
	 */
	public CheckedException(Throwable cause) {
		super(cause);
		this.log = new Log();
	}

	/**
	 * The constructor of an {@link CheckedException}.
	 * 
	 * @param log
	 *            The log inside this exception.
	 */
	public CheckedException(Log log) {
		super();
		this.log = log;
	}

	/**
	 * The constructor of an {@link CheckedException}.
	 * 
	 * @param cause
	 *            The cause of the problem inside this exception.
	 * @param defaultMessage
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public CheckedException(Throwable cause, String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage, cause);
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * The constructor of an {@link CheckedException}.
	 * 
	 * @param defaultMessage
	 *            The default message for the log entry.
	 * @param keyMessage
	 *            The message key for retrieve the final message.
	 * @param parameters
	 *            The parameters for retrieve the final message.
	 */
	public CheckedException(String defaultMessage, String keyMessage, Object... parameters) {
		super(defaultMessage);
		this.log = new Log();
		this.log.addError(defaultMessage, keyMessage, parameters);
	}

	/**
	 * Retrieve the logs of this exception like a string.
	 * 
	 * @return The log of this exception like a string..
	 */
	@Override
	public String toString() {
		return this.log.toString();
	}

	/**
	 * Retrieve the logs of this exception.
	 * 
	 * @return The log of this exception.
	 */
	public Log getLog() {
		return log;
	}
}