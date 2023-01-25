package com.banca.RestConsumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadSaldo;
import com.banca.RestConsumer.apiFabrick.model.response.ResponseClientFabrickReadTransaction;
import com.banca.RestConsumer.dto.request.NewBonificoDTO;
import com.banca.RestConsumer.service.IOperationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class RestConsumerApplicationTests{

	  @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;
	  
	  @Autowired
	  private IOperationService _operatService;
	  
	  private Long accounId =  (long) 14537780;
	 
	 @Test
	 void ReadSaldoController() throws Exception {
		   mockMvc.perform(get("/api/"+accounId+"/readSaldo").contentType(MediaType.APPLICATION_JSON)
			        //.content(objectMapper.writeValueAsString(tutorial))
			        )
			        .andExpect(status().isOk())
			        .andDo(print());
		 
	
	 }
	 
	 @Test
	 void ReadTransaction() throws Exception{
		 
		   mockMvc.perform(get("/api/"+accounId+"/readTransazioni").contentType(MediaType.APPLICATION_JSON)
				    .param("fromAccountingDate", "2019-01-01")
		            .param("toAccountingDate", "2019-12-01")  
			        )
			        .andExpect(status().isOk());
		 
	 }
	 
	 @Test
	 void NewBonifico() throws Exception{
		 
		 NewBonificoDTO request =  new NewBonificoDTO();
		 request.amount = "200";
		 request.currency = "EUR";
		 request.description ="Test inserimento";
		 request.receiverName="Test";
		 request.executionDate = "2023-01-12";
		 
		   mockMvc.perform(post("/api/"+accounId+"/bonifico").contentType(MediaType.APPLICATION_JSON)
				 .content(objectMapper.writeValueAsString(request))
			        )
			        .andExpect(status().isForbidden());
		 
	 }
	 
	 @Test
	 void GetIban() throws Exception{
		 
		 String result =_operatService.GetIban(accounId);
		 assertNotNull(result);
		 
		 
	 }
	 
	 @Test
	 void GetSaldo() throws Exception{
		 ResponseClientFabrickReadSaldo result   = _operatService.readSaldo(accounId);
		 assertNotNull(result);
		 assertEquals("OK",result.status);
		 assertNotNull(result.payload);	
		 
		 
	 }
	 
	 @Test
	 void GetTransaction() throws Exception{
		 ResponseClientFabrickReadTransaction result   = _operatService.readTransazioni(accounId,"2019-01-01","2019-12-01");
		 assertNotNull(result);
		 assertEquals("OK",result.status);
		 assertNotNull(result.payload);	
		 
	 }
	 

	 
	 
	

	  
}
