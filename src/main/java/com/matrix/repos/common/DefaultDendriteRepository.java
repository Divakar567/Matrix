package com.matrix.repos.common;

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
import com.matrix.models.Dendrite;
import com.matrix.repos.DendriteRepository;
import com.matrix.statics.MatrixUtils;

@Component
public class DefaultDendriteRepository implements DendriteRepository {

	@Autowired
	TransportClient elasticClient;

	private Logger logger = LogManager.getLogger(DefaultDendriteRepository.class);

	@Value("${spring.data.elasticsearch.properties.index-name}")
	private String ELASTIC_INDEX_NAME;

	private String DOCUMENT_TYPE = "_doc";

	@Override
	public Dendrite getDendrite(String id) {
		Dendrite dendrite = null;
		try {
			GetResponse response = elasticClient.prepareGet(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, id).get();
			if (response.isExists()) {
				String source = response.getSourceAsString();
				dendrite = MatrixUtils.mapToObject(source, new TypeReference<Dendrite>() {
				});
				logger.info(id.toUpperCase() + " successfully retrieved from Elasticsearch");
			} else {
				logger.info(id.toUpperCase() + " is not found in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to get " + id.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return dendrite;
	}

	@Override
	public boolean saveDendrite(Dendrite dendrite) {
		boolean status = false;
		try {
			String source = MatrixUtils.maptToJson(dendrite);
			IndexResponse response = elasticClient.prepareIndex(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, dendrite.getId())
					.setSource(source).get();
			if (response.getResult().toString().contentEquals("CREATED")) {
				status = true;
				logger.info(dendrite.getId() + " successfully saved in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to update " + dendrite.getId() + " in Elasticsearch:\n", e);
		}
		return status;
	}

	@Override
	public Dendrite updateDendrite(String id, Map<String, Object> dendriteUpdates) {
		Dendrite dendrite = null;
		try {
			UpdateResponse response = elasticClient.prepareUpdate(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, id)
					.setDoc(dendriteUpdates).setFetchSource(true).get();
			if (response.getResult().toString().contentEquals("UPDATED")) {
				String source = response.getGetResult().sourceAsString();
				dendrite = MatrixUtils.mapToObject(source, new TypeReference<Dendrite>() {
				});
				logger.info(id.toUpperCase() + " successfully updated in Elasticsearch");
			} else if (response.getResult().toString().contentEquals("NOOP")) {
				String source = response.getGetResult().sourceAsString();
				dendrite = MatrixUtils.mapToObject(source, new TypeReference<Dendrite>() {
				});
				logger.info(id.toUpperCase() + " is already up to date in Elasticsearch");
			} else if (response.getResult().toString().contentEquals("NOT_FOUND")) {
				logger.info(id.toUpperCase() + " is not found in Elasticsearch");
			} else {
				logger.info(id.toUpperCase() + ": Unknown response from Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to update " + id.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return dendrite;
	}

	@Override
	public boolean deleteDendrite(String id) {
		boolean status = false;
		try {
			DeleteResponse response = elasticClient.prepareDelete(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, id).get();
			if (response.getResult().toString().contentEquals("DELETED")) {
				status = true;
				logger.info(id.toUpperCase() + " successfully deleted in Elasticsearch");
			} else if (response.getResult().toString().contentEquals("NOT_FOUND")) {
				logger.info(id.toUpperCase() + " is not found in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to delete " + id.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return status;
	}

}
