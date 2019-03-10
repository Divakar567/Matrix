package com.matrix.configs;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.matrix.models.Brain;
import com.matrix.models.Response;
import com.matrix.services.BrainConstruction;

import io.netty.util.internal.StringUtil;

@Configuration
public class BrainConfig {

	private Logger logger = LogManager.getLogger(BrainConfig.class);

	@Value("${matrix.brain-name}")
	String BRAIN_NAME;

	@Autowired
	BrainConstruction brainConstructService;
	
	private Brain brain;

	@Bean
	public Brain brain() {
		BRAIN_NAME = StringUtil.isNullOrEmpty(BRAIN_NAME) ? "matrix-default" : BRAIN_NAME;
		Response<Brain> response = brainConstructService.loadBrain(BRAIN_NAME);
		logger.info(response.getMessage());
		brain = response.getResponse();
		return brain;
	}

	@PreDestroy
	public void saveBrain() {
		if(brain != null) {
			Response<Brain> response = brainConstructService.saveBrain(brain);
			logger.info(response.getMessage());
		}
	}
}
