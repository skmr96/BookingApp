package com.irctc.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.irctc.booking.service.DataPopulatorService;

@SpringBootApplication
@ComponentScan(basePackages = "com.irctc.booking")
@Configuration
public class IrctcBookingApplication implements CommandLineRunner  
{
	@Autowired
	DataPopulatorService dataPopulator;
	
	public static void main(String[] args)  {
		SpringApplication.run(IrctcBookingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dataPopulator.populateInitialData();
	}

}
