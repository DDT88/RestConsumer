package com.banca.RestConsumer.apiFabrick.model.response.base;

import java.util.ArrayList;

public abstract class  AbstractResponseClientFabrick<T>{
  
	public String status;
    public ArrayList<Error> error;

    public T payload;
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<Error> getError() {
		return error;
	}
	public void setError(ArrayList<Error> error) {
		this.error = error;
	}
	
	
	public T getPayload() {
		return payload;
	}
	public void setPayload(T payload) {
		this.payload = payload;
	}
  
    
    
}
