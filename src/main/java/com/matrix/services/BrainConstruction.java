package com.matrix.services;

import org.springframework.stereotype.Service;

import com.matrix.models.Brain;
import com.matrix.models.Response;

@Service
public interface BrainConstruction {

	public Response<Brain> loadBrain(String name);

	public Response<Brain> saveBrain(Brain brain);
	
	public Response<Brain> createBrain(String name);
	
	public Response<Brain> destroyBrain(Brain brain);

}
