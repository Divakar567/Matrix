package com.matrix.models;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import com.matrix.processors.DefaultSignalProcessor;
import com.matrix.processors.SignalProcessor;
import com.matrix.services.NeuronProcessor;

public class Neuron {
	
	@Autowired
	NeuronProcessor neuronProcessor;
	
	String id;
	String brainName;
	String type;
	Map<String, Dendrite> dendrites;
	Map<String, Terminal> terminals;
	SignalProcessor processor;

	public Neuron(String id, String brainName, String type) {
		super();
		this.id = id;
		this.brainName = brainName;
		this.type = type;
		dendrites = new LinkedHashMap<>();
		terminals = new LinkedHashMap<>();
		processor = new DefaultSignalProcessor();
	}

	public Neuron(String name, String type, SignalProcessor processor) {
		super();
		this.id = name;
		this.type = type;
		dendrites = new LinkedHashMap<>();
		terminals = new LinkedHashMap<>();
	}

	public Neuron(Map<String, Dendrite> dendrites, Map<String, Terminal> terminals) {
		super();
		this.dendrites = dendrites;
		this.terminals = terminals;
	}

	public Map<String, Dendrite> getDendrites() {
		return dendrites;
	}

	public void setDendrites(Map<String, Dendrite> dendrites) {
		this.dendrites = dendrites;
	}

	public Map<String, Terminal> getTerminals() {
		return terminals;
	}

	public void setTerminals(Map<String, Terminal> terminals) {
		this.terminals = terminals;
	}
	
	@Async
	public void process(Signal signal) {
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
	
	public Dendrite createDendrite() {
		Dendrite dendrite = new Dendrite();
		return dendrite;
	}
	
	public Terminal createTerminal() {
		Terminal terminal = new Terminal();
		return terminal;
	}
	
	
}
