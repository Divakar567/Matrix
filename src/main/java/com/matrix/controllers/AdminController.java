package com.matrix.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matrix.cache.JCSCache;
import com.matrix.models.Response;
import com.matrix.repos.common.RepositoryAdministration;

@Controller
public class AdminController {

	@Autowired
	RepositoryAdministration repoAdmin;

	@Autowired
	JCSCache jcsCache;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response<Object>> test() {
		Response<Object> response = new Response<>();
		response.setStatus("Success");
		response.setMessage("Server working fine");
		response.setResponse("pong");
		return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/matrix/index", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response<Object>> createIndex(
			@RequestParam(defaultValue = "matrix") String name) {

		Response<Object> response = new Response<>();

		boolean status = repoAdmin.createIndex(name);
		if (status) {
			response.setStatus("Success");
			response.setMessage("Index successfully created");
			response.setResponse(name.toUpperCase() + " created with default settings");

			return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
		} else {
			response.setStatus("Failed");
			response.setMessage("Index creation failed");
			response.setResponse(name.toUpperCase() + " creation failed");

			return new ResponseEntity<Response<Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/matrix/clear", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response<Object>> clearCache() {
		Response<Object> response = new Response<>();

		boolean status = jcsCache.clearCache(new String[] {});
		if (status) {
			response.setStatus("Success");
			response.setMessage("JCS cache cleared");
			response.setResponse("Matrix cleard successfully");

			return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
		} else {
			response.setStatus("Failed");
			response.setMessage("JCS cache clearing failed");
			response.setResponse("Matrix clearing failed");

			return new ResponseEntity<Response<Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
