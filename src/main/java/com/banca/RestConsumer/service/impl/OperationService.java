package com.banca.RestConsumer.service.impl;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.banca.RestConsumer.apiFabrick.model.request.RequestClientFabrickNewPayment;
import com.banca.RestConsumer.apiFabrick.model.request.RequestClientFabrickNewPayment.Account;
import com.banca.RestConsumer.apiFabrick.model.request.RequestClientFabrickNewPayment.Creditor;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickAccountInformation;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickNewPayment;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadSaldo;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadTransaction;
import com.banca.RestConsumer.dto.request.NewBonificoDTO;
import com.banca.RestConsumer.exceptions.CustomException;
import com.banca.RestConsumer.service.IOperationService;


@Service
public class OperationService implements IOperationService {
	
	@Value( "${spring.apikey}" )
	private String apikey;
	
	@Value( "${spring.authschema}" )
	private String authschema;
	
	@Value( "${spring.baseurl}" )
	private String baseurl;
	
	@Value( "${spring.urlsaldo}" )
	private String urlsaldo;
	
	@Value( "${spring.urlnewpayments}" )
	private String urlnewpayments;
	

	
	
	@Value("${spring.urlgetcashAccount}")
	private String urlgetcashaccount;
	private static final Logger LOGGER = LogManager.getLogger(OperationService.class);
	
	private HttpHeaders CreateHeader() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/json");
		headers.add("Auth-Schema", authschema);
		headers.add("Api-Key", apikey);
	
		
		 return headers;
	}
	
	
	public ResponseClientFabrickReadSaldo readSaldo(Long accountId) throws CustomException{
		try {
		String uri = baseurl+urlsaldo+accountId+"/balance";
		RestTemplate restTemplate= new RestTemplate();
		
    	HttpEntity<String> entity = new HttpEntity<>(CreateHeader());
		
		ResponseClientFabrickReadSaldo  result= restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseClientFabrickReadSaldo.class).getBody();
			
		 return result;
		}catch (HttpClientErrorException e) {
           
			LOGGER.error("Exception leggi saldo" + e.getResponseBodyAsString());
			throw new CustomException(e.getResponseBodyAsString());		
		}
		
	}
	
	public String GetIban(Long accountId) throws CustomException {
		
		try {			
		String uri = baseurl+urlgetcashaccount+accountId;
		RestTemplate restTemplate= new RestTemplate();
		HttpEntity<String> entity = new HttpEntity<>(CreateHeader());
		ResponseClientFabrickAccountInformation  result= restTemplate.exchange(uri, HttpMethod.GET, entity, ResponseClientFabrickAccountInformation.class).getBody();
					
		return result.payload.getIban();
		}catch (HttpClientErrorException e) {
	        LOGGER.error("Exception GetIban" + e.getResponseBodyAsString());
			throw new CustomException(e.getResponseBodyAsString());		
		}
		
	}
	
	public ResponseClientFabrickNewPayment NewPayment(Long accountId, String iban , NewBonificoDTO bonificoDTO) throws CustomException {
		try
		{
			String uri = baseurl+urlnewpayments+accountId+"/payments/money-transfers";
			RestTemplate restTemplate= new RestTemplate();
			RequestClientFabrickNewPayment request = new RequestClientFabrickNewPayment();
			
			request.executionDate =  bonificoDTO.executionDate;
			request.description = bonificoDTO.description;
			request.amount =  Double.parseDouble(bonificoDTO.amount);
			request.currency=bonificoDTO.currency;
			
		
			Account account = new Account();
			account.accountCode=iban;
			
			Creditor creditor =  new Creditor();
			creditor.account =account;
			creditor.name=bonificoDTO.description;
			request.creditor =  creditor;
			
			HttpEntity<RequestClientFabrickNewPayment> entity = new HttpEntity<>(request ,CreateHeader());
			
			ResponseClientFabrickNewPayment response = restTemplate.postForEntity(uri, entity, ResponseClientFabrickNewPayment.class).getBody();
				
			return response;
		}catch (HttpClientErrorException e) {
	           
				LOGGER.error("Exception leggi saldo" + e.getResponseBodyAsString());
				throw new CustomException(e.getResponseBodyAsString());		
		}
	}
	
	
	public ResponseClientFabrickReadTransaction readTransazioni(Long accountId , String fromAccountingDate , String toAccountingDate ) throws CustomException {
		try
		{
		String uri = baseurl+urlsaldo+accountId+"/transactions";
		RestTemplate restTemplate= new RestTemplate();
		
		 HttpEntity<String> entity = new HttpEntity<>(CreateHeader());
		 UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri)
			        // Add query parameter
			        .queryParam("fromAccountingDate", fromAccountingDate)
			        .queryParam("toAccountingDate", toAccountingDate);
		 
		 ResponseClientFabrickReadTransaction  result= restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, entity, ResponseClientFabrickReadTransaction.class).getBody();
			
		 return result;
		}catch (HttpClientErrorException e) {
	           
			LOGGER.error("Exception leggi saldo" + e.getResponseBodyAsString());
			throw new CustomException(e.getResponseBodyAsString());		
		}
		
	}
	
	
}
