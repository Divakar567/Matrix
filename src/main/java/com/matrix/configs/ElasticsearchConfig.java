package com.matrix.configs;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
	
	private Logger logger = LogManager.getLogger(ElasticsearchConfig.class);
	
	@Value("${spring.data.elasticsearch.cluster-name}")
	String elsticsearchClusterName;
	
	private TransportClient esClient;
	
	@SuppressWarnings("resource")
	@Bean
	public TransportClient client() throws UnknownHostException {
		esClient = new PreBuiltTransportClient(
				  Settings.builder().put("client.transport.sniff", true)
				                    .put("cluster.name",elsticsearchClusterName).build()) 
				  .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
		logger.info("Elasticsearch Transport client successfully conncted to cluster");
		return esClient;
	}
	
	@PreDestroy
	public void onDestroy() {
		if(esClient != null) {
			esClient.close();
			logger.info("Elasticsearch Transport client successfully closed");
		}
	}
}
