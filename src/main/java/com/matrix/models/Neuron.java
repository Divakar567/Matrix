package com.matrix.models;

import java.util.ArrayList;
import java.util.List;

import com.matrix.processors.DefaultSignalProcessor;
import com.matrix.processors.SignalProcessor;
import com.matrix.services.NeuronManager;

public class Neuron extends ElasticDocument {

	private String id;
	private String brainName;
	private String type;
	private List<String> dendrites;
	private List<String> terminals;
	private SignalProcessor signalProcessor;

	public Neuron(String id, String brainName, String type) {
		super();
		this.id = id;
		this.brainName = brainName;
		this.type = type;
		dendrites = new ArrayList<>();
		terminals = new ArrayList<>();
		signalProcessor = new DefaultSignalProcessor();
	}

	public Neuron(String id, String brainName, String type, SignalProcessor signalProcessor) {
		super();
		this.id = id;
		this.brainName = brainName;
		this.type = type;
		this.signalProcessor = signalProcessor;
		dendrites = new ArrayList<>();
		terminals = new ArrayList<>();
	}

	public Neuron(NeuronManager neuronProcessor, String id, String brainName, String type,
			List<String> dendrites, List<String> terminals, SignalProcessor signalProcessor) {
		super();
		this.id = id;
		this.brainName = brainName;
		this.type = type;
		this.dendrites = dendrites;
		this.terminals = terminals;
		this.signalProcessor = signalProcessor;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrainName() {
		return brainName;
	}

	public void setBrainName(String brainName) {
		this.brainName = brainName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public SignalProcessor getSignalProcessor() {
		return signalProcessor;
	}

	public void setSignalProcessor(SignalProcessor signalProcessor) {
		this.signalProcessor = signalProcessor;
	}

	public List<String> getDendrites() {
		return dendrites;
	}

	public void setDendrites(List<String> dendrites) {
		this.dendrites = dendrites;
	}

	public List<String> getTerminals() {
		return terminals;
	}

	public void setTerminals(List<String> terminals) {
		this.terminals = terminals;
	}

}
