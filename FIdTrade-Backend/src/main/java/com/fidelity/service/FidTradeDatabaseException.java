package com.fidelity.service;

public class FidTradeDatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public FidTradeDatabaseException() {
		super();
	}

	public FidTradeDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public FidTradeDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public FidTradeDatabaseException(String message) {
		super(message);
	}

	public FidTradeDatabaseException(Throwable cause) {
		super(cause);
	}

}
