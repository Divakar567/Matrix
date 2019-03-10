package com.matrix.models;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.matrix.functions.BrainFunction;
import com.matrix.monitors.Monitor;
import com.matrix.processors.SignalProcessor;
import com.matrix.protectors.Protector;
import com.matrix.services.BrainProcessor;

public class Brain {
	
	@Autowired
	BrainProcessor brainProcessor;
	
	String name;
	Map<String, Neuron> neurons;
	Map<String, BrainFunction> functions;
	Map<String, SignalProcessor> processors;
	Map<String, Monitor> brainMonitors;
	Map<String, Protector> brainProtectors;

	public Brain(String name) {
		super();
		this.name = name;
		this.neurons = new LinkedHashMap<>();
		this.functions = new LinkedHashMap<>();
		this.processors = new LinkedHashMap<>();
		this.brainMonitors = new LinkedHashMap<>();
		this.brainProtectors = new LinkedHashMap<>();
	}

	public Brain(String name, Map<String, Neuron> neurons, Map<String, BrainFunction> functions,
			Map<String, SignalProcessor> processors, Map<String, Monitor> brainMonitors,
			Map<String, Protector> brainProtectors) {
		super();
		this.name = name;
		this.neurons = neurons;
		this.functions = functions;
		this.processors = processors;
		this.brainMonitors = brainMonitors;
		this.brainProtectors = brainProtectors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Neuron> getNeurons() {
		return neurons;
	}

	public void setNeurons(Map<String, Neuron> neurons) {
		this.neurons = neurons;
	}

	public Map<String, BrainFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(Map<String, BrainFunction> functions) {
		this.functions = functions;
	}

	public Map<String, SignalProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(Map<String, SignalProcessor> processors) {
		this.processors = processors;
	}

	public Map<String, Monitor> getBrainMonitors() {
		return brainMonitors;
	}

	public void setBrainMonitors(Map<String, Monitor> brainMonitors) {
		this.brainMonitors = brainMonitors;
	}

	public Map<String, Protector> getBrainProtectors() {
		return brainProtectors;
	}

	public void setBrainProtectors(Map<String, Protector> brainProtectors) {
		this.brainProtectors = brainProtectors;
	}
}
