package com.matrix.dao;

import java.util.Map;

import com.matrix.models.Dendrite;

public interface DendriteRepository {

	public Dendrite getDendrite(String id);
	
	public boolean saveDendrite(Dendrite dendrite);
	
	public Dendrite updateDendrite(String id, Map<String, Object> dendriteUpdates);
	
	public boolean deleteDendrite(String id);

}
