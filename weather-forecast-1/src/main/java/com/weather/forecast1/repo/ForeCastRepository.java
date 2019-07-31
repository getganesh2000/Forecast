package com.weather.forecast1.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.weather.forecast1.model.Forecast;

public interface ForeCastRepository extends MongoRepository<Forecast, String> {

	//User findFirstByName(String name);
	
	@Query("{cityName:'?0'}")
    Forecast findCustomByAddress(String cityName);
}
