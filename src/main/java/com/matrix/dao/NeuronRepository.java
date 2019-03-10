package com.matrix.dao;

import com.matrix.models.Neuron;

public interface NeuronRepository {

	public Neuron getNeuron(String id);
	
	public boolean saveNeuron(Neuron neuron);
	
	public Neuron updateNeuron(Neuron neuron);
	
	public boolean deleteNeuron(String id);

}
