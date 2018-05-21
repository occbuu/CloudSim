package com.hieu.demo.controller;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hieu.results.*;
import com.hieu.requests.*;

@RestController
@RequestMapping("/")
public class MyController {
	
	@RequestMapping("/hello")  
    public String hello(){  
        return"Hello!";  
    }  
	
	@RequestMapping("/testing")
	public ResponseEntity<?> read(@RequestBody MyRequest body) {
		SingleResult res = new SingleResult();
		res.setResult("Success");
		res.setMessage("Test bua xua um sum...");

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/myorders", method = RequestMethod.GET)
	public ResponseEntity<?> getOrders(@RequestParam(name = "status", required = false) String orderStatus,
			@RequestHeader HttpHeaders httpHeaders) {
		SingleResult res = new SingleResult();
		res.setResult("Success");
		res.setMessage("Test bua xua um sum...: " + orderStatus);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/myassets", method = RequestMethod.GET)
	public ResponseEntity<?> getMyAssets(@RequestHeader HttpHeaders httpHeaders) 
	{
		SingleResult res = new SingleResult();
		res.setResult("Success");
		res.setMessage("Test getMyAssets... ");
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}