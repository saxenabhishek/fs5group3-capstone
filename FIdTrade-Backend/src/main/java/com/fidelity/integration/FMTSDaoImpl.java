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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        
        if (responseEntity.getStatusCode().is2xxSuccessful()) 
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
	public boolean verifyClient() {
		return false;
	}
	
}
