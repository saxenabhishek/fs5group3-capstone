package com.fidelity.service;

public class PreferenceDatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public PreferenceDatabaseException() {
		super();
	}

	public PreferenceDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PreferenceDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public PreferenceDatabaseException(String message) {
		super(message);
	}

	public PreferenceDatabaseException(Throwable cause) {
		super(cause);
	}

}