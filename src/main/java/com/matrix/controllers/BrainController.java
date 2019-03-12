package com.matrix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matrix.models.Response;
import com.matrix.services.BrainManager;

@RestController
public class BrainController {

	@Autowired
	BrainManager brainManager;

	@RequestMapping(value = "/brain", method = RequestMethod.GET)
	public ResponseEntity<Response<Object>> getBrain(@RequestParam(defaultValue = "jarvis") String name) {
		Response<Object> response = brainManager.getBrain(name);
		if (response.getStatus().equalsIgnoreCase("success")) {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/brain", method = RequestMethod.POST)
	public ResponseEntity<Response<Object>> createBrain(@RequestParam(defaultValue = "jarvis") String name) {
		Response<Object> response = brainManager.createBrain(name);
		if (response.getStatus().equalsIgnoreCase("success")) {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/brain", method = RequestMethod.DELETE)
	public ResponseEntity<Response<Object>> destroyBrain(@RequestParam(required = true) String name) {
		Response<Object> response = brainManager.deleteBrain(name);
		if (response.getStatus().equalsIgnoreCase("success")) {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<Response<Object>>(response, HttpStatus.NOT_FOUND);
		}
	}

}
