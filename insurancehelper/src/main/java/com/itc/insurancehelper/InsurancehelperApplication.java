package com.itc.insurancehelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// generate the component scan for the following packages:
@ComponentScan(basePackages = {
		"com.itc.insurancehelper.auth",
		"com.itc.insurancehelper.user",
		"com.itc.insurancehelper.config",
		"com.itc.insurancehelper.config.controller",
		"com.itc.insurancehelper"       // 👈 root, to cover everything
})
public class InsurancehelperApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsurancehelperApplication.class, args);
	}

}
