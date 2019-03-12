package com.matrix.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.matrix.constants.State;

public class Brain extends ElasticDocument {

	private String id;
	private State state;
	private List<String> neurons;
	@JsonProperty("brain_relations")
	private Map<String, String> brainRelations;

	public Brain() {
		super();
	}

	public Brain(String id, String type, Date createdDate, Date updatedDate) {
		super(id, type, createdDate, updatedDate);
		// TODO Auto-generated constructor stub
	}

	public Brain(String id) {
		super();
		this.id = id;
		this.state = State.AWAKE;
		this.neurons = new ArrayList<>();
		this.brainRelations = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("name", "brain");
			}
		};
	}

	public Brain(String id, List<String> neurons) {
		super();
		this.id = id;
		this.state = State.AWAKE;
		this.neurons = neurons;
		this.brainRelations = new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;

			{
				put("name", "brain");
			}
		};
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public List<String> getNeurons() {
		return neurons;
	}

	public void setNeurons(List<String> neurons) {
		this.neurons = neurons;
	}

	public Map<String, String> getBrainRelations() {
		return brainRelations;
	}

	public void setBrainRelations(Map<String, String> brainRelations) {
		this.brainRelations = brainRelations;
	}
}
