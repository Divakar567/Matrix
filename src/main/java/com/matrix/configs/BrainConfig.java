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
import com.matrix.services.BrainConstructionService;

import io.netty.util.internal.StringUtil;

@Configuration
public class BrainConfig {

	private Logger logger = LogManager.getLogger(BrainConfig.class);

	@Value("${matrix.brain-name}")
	String BRAIN_NAME;

	@Autowired
	BrainConstructionService brainConstructService;

	@Bean
	public Brain brain() {
		BRAIN_NAME = StringUtil.isNullOrEmpty(BRAIN_NAME) ? "matrix" : BRAIN_NAME;
		Response<Brain> response = brainConstructService.loadBrain(BRAIN_NAME);
		logger.info(BRAIN_NAME + "===> Loading brain completed\n"+ response.getMessage());
		return response.getResponse();
	}

	@PreDestroy
	public void saveBrain() {
		BRAIN_NAME = StringUtil.isNullOrEmpty(BRAIN_NAME) ? "matrix" : BRAIN_NAME;
		Response<String> response = brainConstructService.saveBrain(BRAIN_NAME);
		logger.info(BRAIN_NAME + "===> Saving brain completed\n" + response.getMessage());
	}
}
