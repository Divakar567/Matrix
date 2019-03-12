package com.matrix.repos.common;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.Settings.Builder;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryAdministration {

	private Logger logger = LogManager.getLogger(RepositoryAdministration.class);

	@Autowired
	private AdminClient elasticAdmin;

	public boolean createIndex(String indexName, Builder settings, XContentBuilder mapping) {
		boolean status = false;
		try {
			CreateIndexResponse response = elasticAdmin.indices().prepareCreate(indexName).setSettings(settings)
					.addMapping("_doc", mapping).get();
			if (response.isAcknowledged()) {
				logger.info(indexName + ": Index successfully created");
				status = true;
			} else {
				logger.info(indexName + ": Index creation failed");
			}
		} catch (Exception e) {
			logger.error("Unable to create index:\n", e);
		}
		return status;
	}

	public boolean createIndex(String indexName) {
		boolean status = false;
		try {
			status = this.createIndex(indexName, RepositorySettings.getDefaultSettings(),
					RepositorySettings.getDefaultRelations());
		} catch (Exception e) {
			logger.error("Unable to get default settings to create index " + indexName + ":\n", e);
		}
		return status;
	}

	private static class RepositorySettings {

		public static Builder getDefaultSettings() {
			Builder settings = Settings.builder().put("index.number_of_shards", 3).put("index.number_of_replicas", 2);
			return settings;
		}

		public static XContentBuilder getDefaultRelations() throws IOException {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
				builder.startObject("properties");
				{
					builder.startObject("brain_relations");
					{
						builder.field("type", "join");
						builder.startObject("relations");
						{
							builder.field("brain", "neuron");
							builder.array("neuron", new String[] { "dendrite", "terminal" });
						}
						builder.endObject();
					}
					builder.endObject();
				}
				builder.endObject();
			}
			builder.endObject();

			return builder;
		}
	}

}
