package com.matrix.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.matrix.models.Response;

@Controller
public class TestController {

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Response<Object>> test() {
		Response<Object> response = new Response<>();
		response.setStatus("Success");
		response.setMessage("Server working fine");
		response.setResponse("pong");
		return new ResponseEntity<Response<Object>>(response, HttpStatus.OK);
	}

}
