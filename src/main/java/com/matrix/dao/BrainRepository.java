package com.matrix.dao;

import com.matrix.models.Brain;

public interface BrainRepository {

	public Brain getBrain(String name);
	
	public boolean saveBrain(Brain brain);
	
	public Brain updateBrain(Brain brain);
	
	public boolean deleteBrain(String name);

}
