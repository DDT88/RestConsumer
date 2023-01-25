package com.banca.RestConsumer.dto.response.base;

import java.util.ArrayList;




public abstract class AbstractResultResponse<T> {

    public String status;
    public ArrayList<ErrorResponse> errors;
    public T payload;
    
   
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<ErrorResponse> getErrors() {
		return errors;
	}
	public void setError(ArrayList<ErrorResponse> errors) {
		this.errors = errors;
	}
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}

    
    
}
