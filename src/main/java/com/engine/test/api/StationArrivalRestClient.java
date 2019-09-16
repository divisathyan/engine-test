package com.engine.test.api;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.engine.test.model.Prediction;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StationArrivalRestClient implements CommandLineRunner {
	
	private static final Logger logger = LogManager.getLogger(StationArrivalRestClient.class);
	
	/**
	 * Calls the TFL REST API for the station URL
	 * @param stationURL
	 * @return List of Predictions of train arrivals
	 * @throws Exception
	 */
	public List<Prediction> callStationArrivals(String stationURL) throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		String response = "";
		
		try {
			response = restTemplate.getForObject(stationURL, String.class);
			logger.debug("REST API Call Response: " + response==null?"":response);
							
			//JSON to Object conversion
			ObjectMapper jsonMapper = new ObjectMapper();
			List<Prediction> predictions = Arrays.asList(jsonMapper.readValue(response, Prediction[].class));
			logger.info("Number of predictions:" + predictions.size());
			
			return predictions;
					
		} catch(JsonMappingException jme) {
			logger.error("JSON Response mapping error: " + jme.getMessage());
			throw jme;
		} catch (JsonParseException jpe) {
			logger.error("JSON Response parsing error: " + jpe.getMessage());
			throw jpe;
		} catch (IOException ioe) {
			logger.error("IO error: " + ioe.getMessage());
			throw ioe;
		}
		
	}

	/*
	 * Uncomment this method if you run it locally without web page.
	 */
	@Override
	public void run(String... args) throws Exception {
		try {
			//callStationArrivals("https://api.tfl.gov.uk/StopPoint/940GZZLUGPS/Arrivals");
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
	}

}
