package com.matrix.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.matrix.services.BrainProcessor;

public class Brain {

	@Autowired
	BrainProcessor brainProcessor;

	private String id;
	private List<String> neurons;

	public Brain(String id) {
		super();
		this.id = id;
		this.neurons = new ArrayList<>();
	}

	public Brain(String id, List<String> neurons) {
		super();
		this.id = id;
		this.neurons = neurons;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<String> neurons) {
		this.neurons = neurons;
	}
}
