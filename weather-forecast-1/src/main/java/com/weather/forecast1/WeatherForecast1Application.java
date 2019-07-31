package com.weather.forecast1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.core.task.TaskExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.weather.forecast1.repo.ForeCastRepository;


@SpringBootApplication
@EnableAsync
public class WeatherForecast1Application implements CommandLineRunner { 

	private static final Logger logger = LoggerFactory.getLogger(WeatherForecast1Application.class);
	
	@Autowired
	ForeCastRepository repository;
	
	@Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(1000);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("Async-");
        return executor;
    }
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherForecast1Application.class, args);
	}
	
	@Override
	 public void run(String... args) throws Exception {
		repository.deleteAll();
	}
}
