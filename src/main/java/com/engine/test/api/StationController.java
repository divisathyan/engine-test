package com.engine.test.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.engine.test.model.Prediction;

@Controller
public class StationController {
	
	//configure the URL by station selection if required
	private final String stationURL = "https://api.tfl.gov.uk/StopPoint/940GZZLUGPS/Arrivals";
	
	private static final Logger logger = LogManager.getLogger(StationController.class); 
	
	@Autowired
	private StationArrivalRestClient restClient;

	@RequestMapping("/arrivals")
	public String loadArrivals(Model model) {
		try {
			
			List<Prediction> predictions = restClient.callStationArrivals(stationURL);
			
			Map<String, Map<String, List<Prediction>>> arrivalsMap = predictions.stream()
					.collect(Collectors.groupingBy(Prediction::getDirection,
							    Collectors.groupingBy(Prediction::getPlatformName,
							    		Collectors.toList())));
			
			model.addAttribute("arrivals", arrivalsMap);
			
		} catch (Exception e) {
			logger.error("Error calling the TFL REST API: " + e.getLocalizedMessage());
			model.addAttribute("message", "Error calling the TFL REST API: " + e.getLocalizedMessage());
			return "error";
		}
		
		return "index";
	}
	
	
}
