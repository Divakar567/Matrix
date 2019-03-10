package com.matrix.services;

import org.springframework.stereotype.Service;

import com.matrix.models.Brain;
import com.matrix.models.Response;

@Service
public class DefaultBrainConstruction implements BrainConstruction{

	@Override
	public Response<Brain> loadBrain(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<String> saveBrain(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<Brain> createBrain(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<Brain> destroyBrain(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
