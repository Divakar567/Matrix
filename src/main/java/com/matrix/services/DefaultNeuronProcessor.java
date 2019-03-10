package com.matrix.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matrix.models.Dendrite;
import com.matrix.models.Neuron;
import com.matrix.models.Signal;
import com.matrix.models.Terminal;

@Service
public class DefaultNeuronProcessor implements NeuronProcessor {

	@Autowired
	NeuronProcessor neuronProcessor;
	

	@Override
	public void process(Neuron neuron, Signal signal) {
		List<String> exciteNeurons = new ArrayList<>();
		List<String> inhibitNeurons = new ArrayList<>();

		// TODO: Yet to be implemented...

		this.excite(exciteNeurons, signal);
		this.inhibit(inhibitNeurons, signal);
	}

	private void excite(List<String> neurons, Signal signal) {
		// TODO: Yet to be implemented...
	}

	private void inhibit(List<String> neurons, Signal signal) {
		// TODO: Yet to be implemented...
	}

	@Override
	public Dendrite createDendrite(Neuron neuron) {
		Dendrite dendrite = new Dendrite();
		return dendrite;
	}

	@Override
	public Terminal createTerminal(Neuron neuron) {
		Terminal terminal = new Terminal();
		return terminal;
	}
}
