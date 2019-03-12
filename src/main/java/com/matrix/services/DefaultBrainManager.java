package com.matrix.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.cache.JCSCache;
import com.matrix.constants.State;
import com.matrix.models.Brain;
import com.matrix.models.Response;
import com.matrix.repos.BrainRepository;

@Service
public class DefaultBrainManager implements BrainManager {

	@Autowired
	BrainRepository brainRepository;

	@Autowired
	JCSCache matrixCache;

	@Override
	public Response<Object> createBrain(String name) {

		Response<Object> response = new Response<>();

		Brain brain = matrixCache.getBrain(name);
		if (brain == null) {
			try {
				brain = DefaultBrainManager.getBrainInstance(name);
				brainRepository.saveBrain(brain);

				response.setStatus("Success");
				response.setMessage(name.toUpperCase() + " successfully created");
				response.setResponse(brain);
			} catch (Exception e) {
				response.setStatus("Failed");
				response.setMessage(name.toUpperCase() + " creation failed");
				response.setResponse(e.getMessage());
			}
		} else {
			response.setStatus("Success");
			response.setMessage(name.toUpperCase() + " already exists");
			response.setResponse(brain);
		}

		return response;
	}

	@Override
	public Response<Object> getBrain(String name) {
		
		Response<Object> response = new Response<>();

		Brain brain = matrixCache.getBrain(name);
		if (brain != null) {
			response.setStatus("Success");
			response.setMessage(name.toUpperCase() + " successfully retrieved");
			response.setResponse(brain);
		} else {
			response.setStatus("Failed");
			response.setMessage("Unable to retrieve the brain: " + name.toUpperCase());
			response.setResponse(name.toUpperCase() + " not exists");
		}

		return response;
	}

	private static Brain getBrainInstance(String name) {
		Brain brain = new Brain(name);
		brain.setType("_brain");
		brain.setState(State.AWAKE);
		brain.setCreatedDate(new Date(System.currentTimeMillis()));
		brain.setUpdatedDate(new Date(System.currentTimeMillis()));
		return brain;
	}

	@Override
	public Response<Object> deleteBrain(String name) {

		Response<Object> response = new Response<>();

		Brain brain = matrixCache.getBrain(name);
		if (brain != null) {
			try {
				brainRepository.deleteBrain(name);

				response.setStatus("Success");
				response.setMessage(name.toUpperCase() + " successfully deleted");
				response.setResponse(brain);
			} catch (Exception e) {
				response.setStatus("Failed");
				response.setMessage(name.toUpperCase() + " deletion failed");
				response.setResponse(e.getMessage());
			}
		} else {
			response.setStatus("Failed");
			response.setMessage("Unable to retrieve the brain: " + name.toUpperCase());
			response.setResponse(name.toUpperCase() + " not exists");
		}

		return response;
	}

}
