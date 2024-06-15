package com.project.apigateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

	@GetMapping(value = "/fallback")
	public ResponseEntity<String> fallback() {
		return new ResponseEntity<>("FallbackMethod", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
