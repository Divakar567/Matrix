package com.matrix.models;

public class Dendrite extends ElasticDocument {
	String id;
	Neuron neuron;
	Synapse synapse;

	public Dendrite() {
		super();
	}

	public Dendrite(String id, Neuron neuron, Synapse synapse) {
		super();
		this.id = id;
		this.neuron = neuron;
		this.synapse = synapse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Neuron getNeuronName() {
		return neuron;
	}

	public void setNeuronName(Neuron neuron) {
		this.neuron = neuron;
	}

	public Synapse getSynapse() {
		return synapse;
	}

	public void setSynapse(Synapse synapse) {
		this.synapse = synapse;
	}
}
