package com.matrix.dao;

import java.util.Map;

import com.matrix.models.Synapse;

public interface SynapseRepository {

	public Synapse getSynapse(String id);
	
	public boolean saveSynapse(Synapse synapse);
	
	public Synapse updateSynapse(String id, Map<String, Object> synapseUpdates);
	
	public boolean deleteSynapse(String id);

}
