package com.weather.forecast1.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.forecast1.model.Forecast;

@Service
public class ForeCastLookupService {

	private static final Logger logger = LoggerFactory.getLogger(ForeCastLookupService.class);
	
	@Value("${forecast.url}")
	private String urlName;
        
    @Async("threadPoolTaskExecutor")
    public CompletableFuture < Forecast > findUser(String user,String cityName) throws InterruptedException {
        
    	logger.info("Looking up " + user);
        //String urlString = String.format("https://api.darksky.net/forecast/e46b84f36fe01b0031f5cee09b28974b/%s?exclude=minutely,hourly,daily,alerts,flags&units=si", user);
    	
    	String urlString = String.format(urlName + "/%s?exclude=minutely,hourly,daily,alerts,flags&units=si", user);
        JSONParser parser = new JSONParser();
        JSONObject json = null;
		try {
			URL url = new URL(urlString);
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String line;
			while ((line = reader.readLine()) != null)
			{
				json=(JSONObject)parser.parse(line);
			}
		}
		catch(Exception ex) {
			logger.info("Error from findUser"+ ex.toString());
		}
        
		
		Optional<JSONObject> elem = Optional.of((JSONObject)json.get("currently"));
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Forecast results = null;
		try {
			if (elem.isPresent()) results = mapper.readValue(elem.get().toJSONString(), Forecast.class);
		}
		catch(Exception ex) {
			logger.info("Error from findUser"+ ex.toString());
		}
        
		results.setCityName(cityName);
		results.setTimezone(json.get("timezone").toString());
        Thread.sleep(1000L);
        return CompletableFuture.completedFuture(results);
    }
    
}
