package com.banca.RestConsumer.dto.response.base;




public class ErrorResponse {

	private String description;
	private String code;
	private String params;
	
	
	public ErrorResponse() {}
		
	public ErrorResponse(String description, String code, String params) {
		super();
		this.description = description;
		this.code = code;
		this.params = params;
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
	public void setCode(String code) {
		this.code = code;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	
	
	

}
