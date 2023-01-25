package com.banca.RestConsumer.service;



import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickNewPayment;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadSaldo;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadTransaction;
import com.banca.RestConsumer.dto.request.NewBonificoDTO;
import com.banca.RestConsumer.exceptions.CustomException;


public interface IOperationService {

	ResponseClientFabrickReadSaldo readSaldo(Long accountId) throws CustomException;
	String GetIban(Long accountId) throws CustomException;
	ResponseClientFabrickNewPayment NewPayment(Long accountId, String iban , NewBonificoDTO bonificoDTO) throws CustomException;
	ResponseClientFabrickReadTransaction readTransazioni(Long accountId , String fromAccountingDate , String toAccountingDate ) throws CustomException;
}
