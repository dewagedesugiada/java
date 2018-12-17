package com.sti.maven.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dewa")
public class TestController {

//	@Autowired
	
	
@GetMapping("/hello")
	    public String index() {
	        return "Hello Wordl !";
	    }

//	
//	@GetMapping("/test")
//	public String Test(@RequestParam("data") String data) {
//		return "hello" + data ;
//	}
//	
//	@GetMapping("/test/{data}")
//	public String Testsaja(@PathVariable("data") String data) {
//		return "hello" + data ;
//	}
//	
//	@PostMapping("/test")
//	public String testpost(@RequestBody Customer customer) {
//		return "Hello" + customer.getCustomerNumber() ;
//	}
	
	//@GetMapping("/mhs")
//	public Mahasiswa mhs(@RequestParam(value="name", defaultValue="World!")String name) {
//	return new Mahasiswa(counter.incrementAndGet(),
//			String.format(template, name)) ;
//	}
	
}
