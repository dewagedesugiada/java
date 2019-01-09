package com.sti.maven.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dewa")
public class TestController {

	
	
@GetMapping("/hello")
	    public String index() {
	        return "Hello Wordl !";
	    }
	
	@GetMapping("/test")
	public String test(@RequestParam("data") String data) {
		return "hello" + data ;
	}
	
	@GetMapping("/test/{data}")
	public String testsaja(@PathVariable("data") String data) {
		return "hello" + data ;
	}
	
	
}
