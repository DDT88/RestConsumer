package com.banca.RestConsumer.config;


import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadSaldo;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadTransaction;
import com.banca.RestConsumer.dto.response.ResponseResultReadSaldo;
import com.banca.RestConsumer.dto.response.ResponseResultReadTransazioni;



@Configuration
public class ModelMapperConfig {
	
	
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper mm = new ModelMapper();
		//mm.getConfiguration().setSkipNullEnabled(true);
			
		//definizione di quali mapper hanno proprietà diverse
		mm.addMappings(typeMapperBalance);
		return mm;
	}
	
	

	//definizione mapping specifico per il bilancio
	PropertyMap<ResponseClientFabrickReadSaldo, ResponseResultReadSaldo> typeMapperBalance = new PropertyMap<ResponseClientFabrickReadSaldo,ResponseResultReadSaldo>(){
        @Override
		protected void configure(){
	    		map().payload.setBalance(source.payload.balance);   		
	    }
	  };

		//definizione mapping specifico per il bilancio
		PropertyMap<ResponseClientFabrickReadTransaction, ResponseResultReadTransazioni> typeMapperTransazioni = new PropertyMap<ResponseClientFabrickReadTransaction,ResponseResultReadTransazioni>(){
	        @Override
			protected void configure(){
	        	
		    		//map().setPayload(source.payload);		
		    }
		  };
	  
}
