package com.banca.RestConsumer.dto.response.entity;


public class SaldoDTO  {
	    
	public  double balance;
	
	
	
	public SaldoDTO() {
	
	}
	public SaldoDTO(double balance) {
		super();
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	
	

}
