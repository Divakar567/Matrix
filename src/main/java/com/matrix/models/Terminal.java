package com.matrix.models;

public class Terminal extends ElasticDocument {
	String id;
	String neuronName;
	Synapse synapse;

	public Terminal() {
		super();
	}

	public Terminal(String id, String neuronName, Synapse synapse) {
		super();
		this.id = id;
		this.neuronName = neuronName;
		this.synapse = synapse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNeuronName() {
		return neuronName;
	}

	public void setNeuronName(String neuronName) {
		this.neuronName = neuronName;
	}

	public Synapse getSynapse() {
		return synapse;
	}

	public void setSynapse(Synapse synapse) {
		this.synapse = synapse;
	}
}
