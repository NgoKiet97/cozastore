package com.cybersoft.cozastore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CozastoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CozastoreApplication.class, args);
	}

}
