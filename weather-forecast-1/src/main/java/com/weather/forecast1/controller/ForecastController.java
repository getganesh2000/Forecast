package com.weather.forecast1.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import com.weather.forecast1.model.Forecast;
import com.weather.forecast1.repo.ForeCastRepository;
import com.weather.forecast1.service.ForeCastLookupService;

enum longaltitude{
	
	campbel("37.2872,-121.94996"),
	omaha("41.2565,-95.9345"),
	austin("30.2672,-97.7431"),
	niseko("42.8048,140.6874"),
	nara("34.6851,135.8048"),
	jakarta("6.2088,106.8456");

	private String loc;

	longaltitude(String loc) {
		this.loc = loc;
	}

	public String loc() {
		return loc;
	}
}


@Controller
public class ForecastController {

private static final Logger logger = LoggerFactory.getLogger(ForecastController.class);

	@Value("${spring.application.name}")
	String appName;
	
	@Autowired
	ForeCastRepository repository;

	
	@Autowired
    private ForeCastLookupService lookupService;
	
	Date dt = new Date();
	
	@GetMapping("/")
	public String homePage(Model model) throws Exception{
		List<Forecast> lobjs = new ArrayList<Forecast>();
		
         
        Forecast u = repository.findCustomByAddress("Austin");
        if (u!=null && u.getCityName().equalsIgnoreCase("austin") ) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page1 = lookupService.findUser(longaltitude.austin.loc(),"Austin");
        	u=page1.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
        
        u = repository.findCustomByAddress("Campbel");
        if (u!=null && u.getCityName().equalsIgnoreCase("Campbel")) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page2 = lookupService.findUser(longaltitude.campbel.loc(),"Campbel");
        	u=page2.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
        
        u = repository.findCustomByAddress("Jakarta");
        if (u!=null && u.getCityName().equalsIgnoreCase("Jakarta")) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page3 = lookupService.findUser(longaltitude.jakarta.loc(),"Jakarta");
        	u=page3.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
        
        u = repository.findCustomByAddress("Omaha");
        if (u!=null && u.getCityName().equalsIgnoreCase("Omaha")) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page4 = lookupService.findUser(longaltitude.omaha.loc(),"Omaha");
        	u=page4.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
        
        
        u = repository.findCustomByAddress("Niseko");
        if (u!=null && u.getCityName().equalsIgnoreCase("niseko")) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page5 = lookupService.findUser(longaltitude.niseko.loc(),"Niseko");
        	u=page5.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
                
        u = repository.findCustomByAddress("Nara");
        if (u!=null && u.getCityName().equalsIgnoreCase("nara")) {
        	lobjs.add(u);
        }else {
        	CompletableFuture < Forecast > page6 = lookupService.findUser(longaltitude.nara.loc(),"Nara");
        	u=page6.get();
        	u.setTimestamp(dt);
        	lobjs.add(u);
        	repository.save(u);
        	logger.info("--> " + u);
        }
        
        
        model.addAttribute("forecasts",lobjs);
        model.addAttribute("appName",appName);
        
		return "home";
	}
	
}
