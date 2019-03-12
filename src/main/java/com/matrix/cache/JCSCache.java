package com.matrix.cache;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matrix.models.Brain;
import com.matrix.models.Dendrite;
import com.matrix.models.Neuron;
import com.matrix.models.Synapse;
import com.matrix.models.Terminal;
import com.matrix.repos.BrainRepository;
import com.matrix.repos.DendriteRepository;
import com.matrix.repos.NeuronRepository;
import com.matrix.repos.SynapseRepository;
import com.matrix.repos.TerminalRepository;

@Component
public class JCSCache {
	
	@Autowired
	BrainRepository brainRepository;
	
	@Autowired
	NeuronRepository neuronRepository;

	@Autowired
	DendriteRepository dendriteRepository;
	
	@Autowired
	TerminalRepository terminalRepository;
	
	@Autowired
	SynapseRepository synapseRepository;
	
	private Logger logger = LogManager.getLogger(JCSCache.class);
	
	private CacheAccess<String, Brain> brainCache;
	private CacheAccess<String, Neuron> neuronCache;
	private CacheAccess<String, Dendrite> dendriteCache;
	private CacheAccess<String, Terminal> terminalCache;
	private CacheAccess<String, Synapse> synapseCache;
	
	public JCSCache() {
		try {
			this.brainCache = JCS.getInstance("brain");
			this.neuronCache = JCS.getInstance("neuron");
			this.dendriteCache = JCS.getInstance("dendrite");
			this.terminalCache = JCS.getInstance("terminal");
			this.synapseCache = JCS.getInstance("synapse");
			logger.info("Brain cache created successfully");
		} catch (Exception e) {
			logger.error("Exception while create brain cache: \n", e);
		}
	}
	
	public Brain getBrain(String id) {
		Brain brain = brainCache.get(id);
		if(brain == null) {
			brain = brainRepository.getBrain(id);
			if(brain != null) {
				brainCache.put(id, brain);
			}
		}
		return brain;
	}
	
	public Neuron getNeuron(String id) {
		Neuron neuron = neuronCache.get(id);
		if(neuron == null) {
			neuron = neuronRepository.getNeuron(id);
			if(neuron != null) {
				neuronCache.put(id, neuron);
			}
		}
		return neuron;
	}
	
	public Dendrite getDendrite(String id) {
		Dendrite dendrite = dendriteCache.get(id);
		if(dendrite == null) {
			dendrite = dendriteRepository.getDendrite(id);
			if(dendrite != null) {
				dendriteCache.put(id, dendrite);
			}
		}
		return dendrite;
	}
	
	public Terminal getTerminal(String id) {
		Terminal terminal = terminalCache.get(id);
		if(terminal == null) {
			terminal = terminalRepository.getTerminal(id);
		}
		return terminal;
	}
	
	public Synapse getSynapse(String id) {
		Synapse synapse = synapseCache.get(id);
		if(synapse == null) {
			synapse = synapseRepository.getSynapse(id);
			if(synapse != null) {
				synapseCache.put(id, synapse);
			}
		}
		return synapse;
	}
	
	public boolean clearCache(String... args) {
		boolean status = false;
		
		try {
			brainCache.clear();
			neuronCache.clear();
			dendriteCache.clear();
			terminalCache.clear();
			synapseCache.clear();
			
			logger.info("Cache successfully cleared");
			status = true;
		} catch(Exception e) {
			logger.error("Cache clearing unsuccessful", e);
		}
		
		return status;
	}
}
