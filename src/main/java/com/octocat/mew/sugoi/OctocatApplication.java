package com.octocat.mew.sugoi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"me.ramswaroop.jbot", "com.octocat.mew.sugoi"})
public class OctocatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OctocatApplication.class, args);
	}
}
