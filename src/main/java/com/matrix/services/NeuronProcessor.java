package com.matrix.services;

import com.matrix.models.Dendrite;
import com.matrix.models.Neuron;
import com.matrix.models.Signal;
import com.matrix.models.Terminal;

public interface NeuronProcessor {
	
	public void process(Neuron neuron, Signal signal);
	
	public Dendrite createDendrite(Neuron neuron);
	
	public Terminal createTerminal(Neuron neuron);
}
