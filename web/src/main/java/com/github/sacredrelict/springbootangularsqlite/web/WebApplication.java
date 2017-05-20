package com.github.sacredrelict.springbootangularsqlite.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
		"com.github.sacredrelict.springbootangularsqlite.web",
		"com.github.sacredrelict.springbootangularsqlite.core",
		"com.github.sacredrelict.springbootangularsqlite.data"
})
@EntityScan(basePackages = "com.github.sacredrelict.springbootangularsqlite.data.entity")
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

}
