package com.muf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.muf"})
@EnableAsync
@EnableCaching
@EnableScheduling
public class MufApplication {

	public static void main(String[] args) {
		SpringApplication.run(MufApplication.class, args);
	}

}
