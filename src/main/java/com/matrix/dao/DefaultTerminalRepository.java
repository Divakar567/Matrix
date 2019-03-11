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
import com.matrix.models.Terminal;
import com.matrix.statics.MatrixUtils;

@Component
public class DefaultTerminalRepository implements TerminalRepository {

	@Autowired
	TransportClient elasticClient;

	private Logger logger = LogManager.getLogger(DefaultTerminalRepository.class);

	@Value("${spring.data.elasticsearch.properties.index-name}")
	private String ELASTIC_INDEX_NAME;

	private String DOCUMENT_TYPE = "_terminal";
	
	@Override
	public Terminal getTerminal(String id) {
		Terminal neuron = null;
		try {
			GetResponse response = elasticClient.prepareGet(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, id).get();
			if (response.isExists()) {
				String source = response.getSourceAsString();
				neuron = MatrixUtils.mapToObject(source, new TypeReference<Terminal>() {
				});
				logger.info(id.toUpperCase() + " successfully retrieved from Elasticsearch");
			} else {
				logger.info(id.toUpperCase() + " is not found in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to get " + id.toUpperCase() + " from Elasticsearch:\n", e);
		}
		return neuron;
	}

	@Override
	public boolean saveTerminal(Terminal terminal) {
		boolean status = false;
		try {
			String source = MatrixUtils.maptToJson(terminal);
			IndexResponse response = elasticClient.prepareIndex(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, terminal.getId())
					.setSource(source).get();
			if (response.getResult().toString().contentEquals("CREATED")) {
				status = true;
				logger.info(terminal.getId() + " successfully saved in Elasticsearch");
			}
		} catch (Exception e) {
			logger.error("Unable to update " + terminal.getId() + " in Elasticsearch:\n", e);
		}
		return status;
	}

	@Override
	public Terminal updateTerminal(String id, Map<String, Object> terminalUpdates) {
		Terminal terminal = null;
		try {
			UpdateResponse response = elasticClient.prepareUpdate(ELASTIC_INDEX_NAME, DOCUMENT_TYPE, id)
					.setDoc(terminalUpdates).setFetchSource(true).get();
			if (response.getResult().toString().contentEquals("UPDATED")) {
				String source = response.getGetResult().sourceAsString();
				terminal = MatrixUtils.mapToObject(source, new TypeReference<Terminal>() {
				});
				logger.info(id.toUpperCase() + " successfully updated in Elasticsearch");
			} else if (response.getResult().toString().contentEquals("NOOP")) {
				String source = response.getGetResult().sourceAsString();
				terminal = MatrixUtils.mapToObject(source, new TypeReference<Terminal>() {
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
		return terminal;
	}

	@Override
	public boolean deleteTerminal(String id) {
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
