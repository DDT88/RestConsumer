package com.banca.RestConsumer.apiFabrick.model.response.entity;

import java.util.ArrayList;

public class TransactionInformation {

	public ArrayList<List> list;

	public static class List{
	    public String transactionId;
	    public String operationId;
	    public String accountingDate;
	    public String valueDate;
	    public Type type;
	    public double amount;
	    public String currency;
	    public String description;
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public String getOperationId() {
			return operationId;
		}
		public void setOperationId(String operationId) {
			this.operationId = operationId;
		}
		public String getAccountingDate() {
			return accountingDate;
		}
		public void setAccountingDate(String accountingDate) {
			this.accountingDate = accountingDate;
		}
		public String getValueDate() {
			return valueDate;
		}
		public void setValueDate(String valueDate) {
			this.valueDate = valueDate;
		}
		public Type getType() {
			return type;
		}
		public void setType(Type type) {
			this.type = type;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		
	 
	    
	}
	
	public static class Type{
	    public String enumeration;
	    public String value;
		public String getEnumeration() {
			return enumeration;
		}
		public void setEnumeration(String enumeration) {
			this.enumeration = enumeration;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	    
	    
	}

	public ArrayList<List> getList() {
		return list;
	}

	public void setList(ArrayList<List> list) {
		this.list = list;
	}
	
	
}
