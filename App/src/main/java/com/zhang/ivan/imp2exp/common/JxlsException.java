package com.zhang.ivan.imp2exp.common;

public class JxlsException extends Exception {

	private static final long serialVersionUID = 3023760178200813800L;

	private String message;

	private Exception exception;

	public JxlsException(String message) {
		super();
		this.message = message;
		this.exception = new Exception();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}
