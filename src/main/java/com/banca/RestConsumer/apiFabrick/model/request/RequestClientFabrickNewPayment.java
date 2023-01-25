package com.banca.RestConsumer.apiFabrick.model.request;

public class RequestClientFabrickNewPayment {

        public Creditor creditor;
	    public String executionDate;
	    public String description;
	    public double amount;
	    public String currency;

	    public static class Creditor{
	        public String name;
	        public Account account;
	    }

	    public static class Account{
	        public String accountCode;
	    }

		public RequestClientFabrickNewPayment() {
			
		}
	    
	    

	    
	    
}

