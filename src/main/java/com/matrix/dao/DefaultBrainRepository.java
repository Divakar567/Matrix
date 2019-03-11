package com.matrix.dao;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.matrix.models.Brain;
import com.matrix.statics.MatrixUtils;

@Component
public class DefaultBrainRepository implements BrainRepository {

	@Autowired
	TransportClient elasticClient;

	private Logger logger = LogManager.getLogger(DefaultBrainRepository.class);

	@Value("${spring.data.elasticsearch.properties.index-name}")
	private String ELASTIC_INDEX_NAME;

	private String DOCUMENT_TYPE = "_brain";

	@Override
	public Brain getBrain(String name) {
		Brain brain = null;
		try {
			GetResponse response = elasticClient.prepareGet(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, name).get();
			if (response.isExists()) {
				String source = response.getSourceAsString();
				brain = MatrixUtils.mapToObject(source, new TypeReference<Brain>() {
				});
				logger.info(name.toUpperCase() + " successfully retrieved from Elasticsearch");
			} else {
				logger.info(name.toUpperCase() + " is not found in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to get " + name.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return brain;
	}

	@Override
	public boolean saveBrain(Brain brain) {
		boolean status = false;
		try {
			String source = MatrixUtils.maptToJson(brain);
			IndexResponse response = elasticClient.prepareIndex(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, brain.getId())
					.setSource(source).get();
			if (response.getResult().toString().contentEquals("CREATED")) {
				status = true;
				logger.info(brain.getId() + " successfully saved in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to update " + brain.getId() + " in Elasticsearch:\n", e);
		}
		return status;
	}

	@Override
	public Brain updateBrain(String name, Map<String, Object> brainUpdates) {
		Brain brain = null;
		try {
			UpdateResponse response = elasticClient.prepareUpdate(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, name).setDoc(brainUpdates).setFetchSource(true).get();
			if (response.getResult().toString().contentEquals("UPDATED")) {
				String source = response.getGetResult().sourceAsString();
				brain = MatrixUtils.mapToObject(source, new TypeReference<Brain>() {
				});
				logger.info(name.toUpperCase() + " successfully updated in Elasticsearch");
			} else if(response.getResult().toString().contentEquals("NOOP")) {
				String source = response.getGetResult().sourceAsString();
				brain = MatrixUtils.mapToObject(source, new TypeReference<Brain>() {
				});
				logger.info(name.toUpperCase() + " is already up to date in Elasticsearch");
			} else if(response.getResult().toString().contentEquals("NOT_FOUND")) {
				logger.info(name.toUpperCase() + " is not found in Elasticsearch");
			} else {
				logger.info(name.toUpperCase() + ": Unknown response from Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to update " + name.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return brain;
	}

	@Override
	public boolean deleteBrain(String name) {
		boolean status = false;
		try {
			DeleteResponse response = elasticClient.prepareDelete(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, name).get();
			if (response.getResult().toString().contentEquals("DELETED")) {
				status = true;
				logger.info(name.toUpperCase() + " successfully deleted in Elasticsearch");
			} else if (response.getResult().toString().contentEquals("NOT_FOUND")) {
				logger.info(name.toUpperCase() + " is not found in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to delete " + name.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return status;
	}

}
