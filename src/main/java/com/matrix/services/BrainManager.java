package com.matrix.services;

import com.matrix.models.Response;

public interface BrainManager {

	public Response<Object> createBrain(String name);
	
	public Response<Object> getBrain(String name);

	public Response<Object> deleteBrain(String name);
	 
}
