package com.matrix.repos.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryAdministration {

	@Autowired
	TransportClient elasticClient;
	
	private Logger logger = LogManager.getLogger(RepositoryAdministration.class);

	private AdminClient elasticAdmin = elasticClient.admin();

	public boolean createIndex(String indexName, Builder settings) {
		boolean status = false;
		try {
			CreateIndexResponse response = elasticAdmin.indices().prepareCreate(indexName).setSettings(settings).get();
			if(response.isAcknowledged()) {
				logger.info(indexName+": Index successfully created");
			} else {
				logger.info(indexName+": Index creation failed");
			}
		} catch (Exception e) {
			logger.error("Unable to create index:\n", e);
		}
		return status;
	}

	public boolean createIndex(String indexName) {
		return this.createIndex(indexName, RepositorySettings.getDefaultSettings());
	}

	
	
	private static class RepositorySettings {
		
		public static Builder getDefaultSettings() {
			Builder settings = Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2);
			return settings;
		}
	}

}
