package com.matrix.dao;

import java.util.Map;

import com.matrix.models.Brain;

public interface BrainRepository {

	public Brain getBrain(String name);
	
	public boolean saveBrain(Brain brain);
	
	public Brain updateBrain(String name, Map<String, Object> brain);
	
	public boolean deleteBrain(String name);

}
