package com.matrix.dao;

import com.matrix.models.Dendrite;

public interface DendriteRepository {

	public Dendrite getDendrite(String id);
	
	public boolean saveDendrite(Dendrite dendrite);
	
	public Dendrite updateDendrite(Dendrite dendrie);
	
	public boolean deleteDendrite(String id);

}
