package com.training.sprint1.exceptions;

import java.util.Arrays;

public class BeneficiaryNotFoundException extends Exception {

	public BeneficiaryNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BeneficiaryNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "BeneficiaryNotFoundException [getMessage()=" + getMessage() + ", getLocalizedMessage()="
				+ getLocalizedMessage() + ", getCause()=" + getCause() + ", toString()=" + super.toString()
				+ ", fillInStackTrace()=" + fillInStackTrace() + ", getStackTrace()=" + Arrays.toString(getStackTrace())
				+ ", getSuppressed()=" + Arrays.toString(getSuppressed()) + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
