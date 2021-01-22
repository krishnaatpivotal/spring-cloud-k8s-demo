package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

public class K8sDemoController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
	public String hello() {
    	System.out.println("Within the Controller");
		String name = restTemplate.getForObject("http://k8s-name-service", String.class);
		return "Hola " + name;
	}
}
