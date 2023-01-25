package com.banca.RestConsumer.controller;

import java.util.ArrayList;
import java.util.Iterator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.banca.RestConsumer.dto.response.ResponseResultError;
import com.banca.RestConsumer.dto.response.base.ErrorResponse;
import com.banca.RestConsumer.exceptions.CustomException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ControllerAdvice
@RestController
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {


	
	ErrorResponse errore =  new ErrorResponse();
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ResponseResultError> exceptionCustomHandler(Exception e) throws JsonMappingException, JsonProcessingException{
		
		TranformException(e.getMessage());		
		ResponseResultError response =  new ResponseResultError();
		response.errors =  new ArrayList<>();
		response.errors.add(errore);
		response.status ="KO";
	
		return new ResponseEntity<ResponseResultError>(response,HttpStatus.FORBIDDEN);
	}
	
	
   /*
    * Questo metodo serve per transformare la stringa di errore in json visto che è diversa la response in caso di success e error.
    * Cambia error in errors.
    */
	public  void TranformException(String testo) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper =  new ObjectMapper();
		JsonNode json =  mapper.readTree(testo);
		
		Iterator<JsonNode> elements = json.get("errors").elements();
		while (elements.hasNext()) { 
			JsonNode element = elements.next(); 
			errore.setCode(element.findValue("code").asText());
			errore.setDescription(element.findValue("description").asText());
			}

	}
	

}
