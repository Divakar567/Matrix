package com.matrix.dao;

import com.matrix.models.Synapse;

public interface SynapseRepository {

	public Synapse getSynapse(String id);
	
	public boolean saveSynapse(Synapse synapse);
	
	public Synapse updateSynapse(Synapse synapse);
	
	public boolean deleteSynapse(String id);

}
