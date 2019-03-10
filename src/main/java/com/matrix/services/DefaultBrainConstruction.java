package com.matrix.services;

import org.springframework.stereotype.Service;

import com.matrix.models.Brain;
import com.matrix.models.Response;

@Service
public class DefaultBrainConstruction implements BrainConstruction{

	@Override
	public Response<Brain> loadBrain(String name) {
		Brain brain = new Brain(name);
		// TODO Auto-generated method stub
		
		Response<Brain> response = new Response<>();
		response.setStatus("Success");
		response.setMessage(name.toUpperCase()+" successfully loaded");
		response.setResponse(brain);
		return response;
	}

	@Override
	public Response<Brain> saveBrain(Brain brain) {
		// TODO Auto-generated method stub

		Response<Brain> response = new Response<>();
		response.setStatus("Success");
		response.setMessage(brain.getId().toUpperCase()+" successfully saved");
		response.setResponse(brain);
		return response;
	}

	@Override
	public Response<Brain> createBrain(String name) {
		Brain brain = new Brain(name);
		Response<Brain> response = new Response<>();
		response.setStatus("Success");
		response.setMessage(name.toUpperCase()+" successfully created");
		response.setResponse(brain);
		return response;
	}

	@Override
	public Response<Brain> destroyBrain(Brain brain) {
		// TODO Auto-generated method stub
		
		Response<Brain> response = new Response<>();
		response.setStatus("Success");
		response.setMessage(brain.getId().toUpperCase()+" successfully destroyed");
		response.setResponse(brain);
		return response;
	}

}
