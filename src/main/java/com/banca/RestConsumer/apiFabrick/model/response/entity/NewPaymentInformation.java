package com.banca.RestConsumer.apiFabrick.model.response.entity;

import java.util.ArrayList;
import java.util.Date;

public class NewPaymentInformation {

	  public String moneyTransferId;
	    public String status;
	    public String direction;
	    public Creditor creditor;
	    public Debtor debtor;
	    public String cro;
	    public String uri;
	    public String trn;
	    public String description;
	    public Date createdDatetime;
	    public Date accountedDatetime;
	    public String debtorValueDate;
	    public String creditorValueDate;
	    public Amount amount;
	    public boolean isUrgent;
	    public boolean isInstant;
	    public String feeType;
	    public String feeAccountId;
	    public ArrayList<Fee> fees;
	    public boolean hasTaxRelief;

	    public class Account{
	        public String accountCode;
	        public String bicCode;
	    }

	    public class Address{
	        public Object address;
	        public Object city;
	        public Object countryCode;
	    }

	    public class Amount{
	        public int debtorAmount;
	        public String debtorCurrency;
	        public int creditorAmount;
	        public String creditorCurrency;
	        public String creditorCurrencyDate;
	        public int exchangeRate;
	    }

	    public class Creditor{
	        public String name;
	        public Account account;
	        public Address address;
	    }

	    public class Debtor{
	        public String name;
	        public Account account;
	    }

	    public class Fee{
	        public String feeCode;
	        public String description;
	        public double amount;
	        public String currency;
	    }
	    
}
