package com.matrix.cache;

import org.apache.commons.jcs.JCS;
import org.apache.commons.jcs.access.CacheAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
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
		if (brain == null) {
			brain = brainRepository.getBrain(id);
			if (brain != null) {
				brainCache.put(id, brain);
			}
		}
		return brain;
	}

	public Neuron getNeuron(String id) {
		Neuron neuron = neuronCache.get(id);
		if (neuron == null) {
			neuron = neuronRepository.getNeuron(id);
			if (neuron != null) {
				neuronCache.put(id, neuron);
			}
		}
		return neuron;
	}

	public Dendrite getDendrite(String id) {
		Dendrite dendrite = dendriteCache.get(id);
		if (dendrite == null) {
			dendrite = dendriteRepository.getDendrite(id);
			if (dendrite != null) {
				dendriteCache.put(id, dendrite);
			}
		}
		return dendrite;
	}

	public Terminal getTerminal(String id) {
		Terminal terminal = terminalCache.get(id);
		if (terminal == null) {
			terminal = terminalRepository.getTerminal(id);
			if (terminal != null) {
				terminalCache.put(id, terminal);
			}
		}
		return terminal;
	}

	public Synapse getSynapse(String id) {
		Synapse synapse = synapseCache.get(id);
		if (synapse == null) {
			synapse = synapseRepository.getSynapse(id);
			if (synapse != null) {
				synapseCache.put(id, synapse);
			}
		}
		return synapse;
	}

	@Async
	public void updateBrain(Brain brain) {
		if (brainRepository.saveBrain(brain)) {
			brainCache.put(brain.getId(), brain);
		}
	}

	@Async
	public void updateNeuron(Neuron neuron) {
		if (neuronRepository.saveNeuron(neuron)) {
			neuronCache.put(neuron.getId(), neuron);
		}
	}

	@Async
	public void updateDendrite(Dendrite dendrite) {
		if (dendriteRepository.saveDendrite(dendrite)) {
			dendriteCache.put(dendrite.getId(), dendrite);
		}
	}

	@Async
	public void updateTerminal(Terminal terminal) {
		if (terminalRepository.saveTerminal(terminal)) {
			terminalCache.put(terminal.getId(), terminal);
		}
	}

	@Async
	public void updateSynapse(Synapse synapse) {
		if (synapseRepository.saveSynapse(synapse)) {
			synapseCache.put(synapse.getId(), synapse);
		}
	}

	public Brain deleteBrain(String id) {
		Brain brain = brainCache.get(id);
		if (brain == null) {
			brain = brainRepository.getBrain(id);
			if (brain != null) {
				brainCache.remove(id);
				brainRepository.deleteBrain(id);
			}
		}
		return brain;
	}

	public Neuron deleteNeuron(String id) {
		Neuron neuron = neuronCache.get(id);
		if (neuron == null) {
			neuron = neuronRepository.getNeuron(id);
			if (neuron != null) {
				neuronCache.remove(id);
				neuronRepository.deleteNeuron(id);
			}
		}
		return neuron;
	}

	public Dendrite deleteDendrite(String id) {
		Dendrite dendrite = dendriteCache.get(id);
		if (dendrite == null) {
			dendrite = dendriteRepository.getDendrite(id);
			if (dendrite != null) {
				dendriteCache.remove(id);
				dendriteRepository.deleteDendrite(id);
			}
		}
		return dendrite;
	}

	public Terminal deleteTerminal(String id) {
		Terminal terminal = terminalCache.get(id);
		if (terminal == null) {
			terminal = terminalRepository.getTerminal(id);
			if (terminal != null) {
				terminalCache.remove(id);
				terminalRepository.deleteTerminal(id);
			}
		}
		return terminal;
	}

	public Synapse deleteSynapse(String id) {
		Synapse synapse = synapseCache.get(id);
		if (synapse == null) {
			synapse = synapseRepository.getSynapse(id);
			if (synapse != null) {
				synapseCache.remove(id);
				synapseRepository.deleteSynapse(id);
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
		} catch (Exception e) {
			logger.error("Cache clearing unsuccessful", e);
		}

		return status;
	}
}
