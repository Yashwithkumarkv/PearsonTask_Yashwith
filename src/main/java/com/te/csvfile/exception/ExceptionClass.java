package com.te.csvfile.exception;

@SuppressWarnings("serial")
public class ExceptionClass extends RuntimeException {
	String msg;

	public ExceptionClass(String msg) {
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return this.msg;
	}

}
