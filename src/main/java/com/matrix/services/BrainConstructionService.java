package com.matrix.services;

import org.springframework.stereotype.Service;

import com.matrix.models.Brain;
import com.matrix.models.Response;

@Service
public interface BrainConstructionService {

	public Response<Brain> loadBrain(String name);

	public Response<String> saveBrain(String name);
	
	public Response<Brain> createBrain(String name);
	
	public Response<Brain> destroyBrain(String name);

}
