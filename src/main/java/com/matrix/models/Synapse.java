package com.matrix.models;

public class Synapse {
	private String id;
	private Dendrite dendriteName;
	private Terminal terminalName;

	public Synapse() {
		super();
	}

	public Synapse(String id, Dendrite dendriteName, Terminal terminalName) {
		super();
		this.id = id;
		this.dendriteName = dendriteName;
		this.terminalName = terminalName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dendrite getDendriteName() {
		return dendriteName;
	}

	public void setDendriteName(Dendrite dendriteName) {
		this.dendriteName = dendriteName;
	}

	public Terminal getTerminalName() {
		return terminalName;
	}

	public void setTerminalName(Terminal terminalName) {
		this.terminalName = terminalName;
	}
}
