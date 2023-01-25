package com.banca.RestConsumer.controller;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickNewPayment;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadSaldo;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadTransaction;
import com.banca.RestConsumer.dto.request.NewBonificoDTO;
import com.banca.RestConsumer.dto.response.ResponseResultError;
import com.banca.RestConsumer.dto.response.ResponseResultNewBonifico;
import com.banca.RestConsumer.dto.response.ResponseResultReadSaldo;
import com.banca.RestConsumer.dto.response.ResponseResultReadTransazioni;
import com.banca.RestConsumer.exceptions.CustomException;
import com.banca.RestConsumer.service.IOperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;

import io.swagger.v3.oas.annotations.media.Schema;


@RestController
@RequestMapping("/api")
public class OperationController {

	private static final Logger LOGGER = LogManager.getLogger(OperationController.class);
	
	@Autowired
	IOperationService _operationService;
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Operation(summary = "Lettura Saldo", 
			description = "API per la lettura del saldo")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",description = "${api.response-codes.ok.desc}", content = @Content(schema = @Schema(implementation =  ResponseResultReadSaldo.class) )),
			@ApiResponse(responseCode = "403", description = "${api.response-codes.forbiden.desc}",content = @Content(schema = @Schema(implementation =  ResponseResultError.class) ) ),
			 })
	@GetMapping(value="{accountId}/readSaldo" , produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ResponseResultReadSaldo> readSaldo(@PathVariable("accountId")Long accountId) throws CustomException{
		LOGGER.info("readSaldo " + accountId);
		ResponseResultReadSaldo postResponse = new ResponseResultReadSaldo();
		try {
			ResponseClientFabrickReadSaldo responseClient = _operationService.readSaldo(accountId);
		
			 postResponse = modelMapper.map(responseClient, ResponseResultReadSaldo.class);
		
			return ResponseEntity.ok().body(postResponse);
		
		}catch (CustomException e) {
			LOGGER.error("Exception read Saldo " + e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}
	
	@Operation(summary = "Nuovo Bonifico", 
			description = "API per la creazione di un nuovo bonifico")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",description = "${api.response-codes.ok.desc}", content = @Content(schema = @Schema(implementation =  ResponseResultNewBonifico.class) )),
			@ApiResponse(responseCode = "403", description = "${api.response-codes.forbiden.desc}",content = @Content(schema = @Schema(implementation =  ResponseResultError.class) ) ),
			 })
	@PostMapping(value="{accountId}/bonifico" , produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ResponseResultNewBonifico> createBonifico(@PathVariable("accountId") Long accountId ,@RequestBody NewBonificoDTO request ) throws CustomException {
		LOGGER.info("Crea Bonifico per accountId" + accountId);
		try {
			String iban = _operationService.GetIban(accountId);
			ResponseClientFabrickNewPayment responseClient = _operationService.NewPayment(accountId, iban, request);
			
			ResponseResultNewBonifico postResponse = modelMapper.map(responseClient, ResponseResultNewBonifico.class);
			
			
			return ResponseEntity.ok().body(postResponse);
		}catch (CustomException e) {
			LOGGER.error("Exception crea bonifico " + e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}
	
	@Operation(summary = "Lettura transazioni", 
			description = "API per la lettura delle transazioni")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200",description = "${api.response-codes.ok.desc}", content = @Content(schema = @Schema(implementation =  ResponseResultReadTransazioni.class) )),
			@ApiResponse(responseCode = "403", description = "${api.response-codes.forbiden.desc}",content = @Content(schema = @Schema(implementation =  ResponseResultError.class) ) ),
			 })
	@GetMapping(value="{accountId}/readTransazioni" , produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<ResponseResultReadTransazioni> readTransazioni(@PathVariable("accountId")Long accountId ,@RequestParam() String fromAccountingDate ,@RequestParam() String toAccountingDate  ) throws CustomException{
		LOGGER.info("readTransazioni " + accountId);
		try {
			
			ResponseClientFabrickReadTransaction responseClient = _operationService.readTransazioni(accountId,fromAccountingDate,toAccountingDate);
		
			
			ResponseResultReadTransazioni postResponse = modelMapper.map(responseClient, ResponseResultReadTransazioni.class);
		
			return ResponseEntity.ok().body(postResponse);
		
		}catch (CustomException e) {
			
			LOGGER.error("readTransazioni " + e.getMessage());
			throw new CustomException(e.getMessage());
		}
	}
	
	
}
