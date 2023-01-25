package com.banca.RestConsumer.exceptions;


public class CustomException extends Exception {

	
	private static final long serialVersionUID = 1L;
	
	private String description;
	private String code;

	public CustomException() {
		super();
	}

	public CustomException(String description ,String code) {
		super(description);
		this.code=code;
		this.description=description;
		
	}
	
	public CustomException(String description ) {
		super(description);
		
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCodice(String code) {
		this.code= code;
	}

	

}
