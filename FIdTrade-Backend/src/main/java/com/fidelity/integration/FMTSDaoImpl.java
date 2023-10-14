package com.fidelity.integration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fidelity.business.Client;
import com.fidelity.business.ClientFMTS;
import com.fidelity.business.Price;

@Repository("FMTSDao")
public class FMTSDaoImpl implements FMTSDao {
	
	@Autowired
	private RestTemplate restTemplate;
    
	@Override
	public List<Price> getCurrentPricesFromFMTS(String category, String instrumentId){
		String url = "http://localhost:3000/fmts/trades/prices/";		
		if (category != null)
			url+= category;
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        
        RequestCallback requestCallback = restTemplate.httpEntityCallback(new HttpEntity<>(headers));
        ResponseExtractor<ResponseEntity<byte[]>> responseExtractor = restTemplate.responseEntityExtractor(byte[].class);
        ResponseEntity<byte[]> responseEntity = restTemplate.execute(url, HttpMethod.GET, requestCallback, responseExtractor);
        List<Price> prices= null;
        
        if (responseEntity != null && responseEntity.getStatusCode().is2xxSuccessful()) 
        {
            byte[] responseBody = responseEntity.getBody();
	        ObjectMapper objectMapper = new ObjectMapper();
	        TypeReference<List<Price>> typeReference = new TypeReference<>() {};
			try {
				prices = objectMapper.readValue(responseBody, typeReference);
				if (instrumentId != null) {
					Optional<Price> price= prices.stream().filter(p -> p.getInstrument().getInstrumentId().equals(instrumentId)).findAny();
					List<Price> insPrice= new ArrayList<>();
					insPrice.add(price.get());
		            return insPrice;
				}

			} catch (StreamReadException e) {
				e.printStackTrace();
			} catch (DatabindException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return prices;
	}
	
	@Override
	public ClientFMTS verifyClientInformation(Client client) {
		ClientFMTS response = null;
		RestTemplate restTemplate = new RestTemplate();

		ClientFMTS clientFMTS = new ClientFMTS();
		clientFMTS.setClientId("");
		clientFMTS.setEmail(client.getPerson().getEmail());
		clientFMTS.setToken(""); 

		String url = "http://localhost:3000/fmts/client"; 

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println(clientFMTS);
		HttpEntity<ClientFMTS> requestEntity = new HttpEntity<>(clientFMTS, headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
				String.class);

		if (responseEntity.getStatusCode().equals(HttpStatus.NOT_ACCEPTABLE)) 
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
		else if (responseEntity.getStatusCode().equals(HttpStatus.NOT_FOUND)) 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			response = mapper.readValue(responseEntity.getBody(), ClientFMTS.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return response;
	}	
}
