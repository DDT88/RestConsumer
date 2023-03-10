package com.banca.RestConsumer.apiFabrick.model.response.base;

public class Error {

	private String code;
	private String description;
	private String params;
	
	public Error(String code, String description, String params) {
		super();
		this.code = code;
		this.description = description;
		this.params = params;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
}
