package com.matrix.dao;

import java.util.Map;

import com.matrix.models.Neuron;

public interface NeuronRepository {

	public Neuron getNeuron(String id);
	
	public boolean saveNeuron(Neuron neuron);
	
	public Neuron updateNeuron(String id, Map<String, Object> neuronUpdates);
	
	public boolean deleteNeuron(String id);

}
