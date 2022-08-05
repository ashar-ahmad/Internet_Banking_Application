package com.training.sprint1.exceptions;

import java.util.Arrays;

public class InvalidTransactionIDException extends Exception {

	public InvalidTransactionIDException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidTransactionIDException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidTransactionIDException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidTransactionIDException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidTransactionIDException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InvalidTransactionIDException [getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getCause()=" + getCause() + ", toString()=" + super.toString()
				+ ", fillInStackTrace()=" + fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", getSuppressed()=" + Arrays.toString(getSuppressed()) + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	

}
