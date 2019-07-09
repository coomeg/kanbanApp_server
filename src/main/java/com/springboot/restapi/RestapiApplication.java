package com.springboot.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestapiApplication {

    @RequestMapping("/")
    public String home() {
        return "Hello Docker World ini";
    }

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}
}
