package com.training.sprint1.exceptions;

import java.util.Arrays;

public class NomineeNotFoundException extends Exception{

	public NomineeNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NomineeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NomineeNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NomineeNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NomineeNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "NomineeNotFoundException [getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getCause()=" + getCause() + ", toString()=" + super.toString()
				+ ", fillInStackTrace()=" + fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
				 + ", getSuppressed()="
				+ Arrays.toString(getSuppressed()) + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
	
	
	
	
	
	

}
