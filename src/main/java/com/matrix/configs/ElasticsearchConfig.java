package com.matrix.configs;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticsearchConfig {
	
	@Value("${spring.data.elasticsearch.host-name}")
	String elsticsearchHostName;
	
	@Value("${spring.data.elasticsearch.cluster-name}")
	String elsticsearchClusterName;
	

	private Logger logger = LogManager.getLogger(ElasticsearchConfig.class);
	
	private TransportClient transportClient;
	
	private RestClient restClient;
	
	private RestHighLevelClient restHighClient;
	
	@SuppressWarnings("resource")
	@Bean("elasticClient")
	public TransportClient transportClient() throws UnknownHostException {
		transportClient = new PreBuiltTransportClient(
				  Settings.builder().put("client.transport.sniff", true)
				                    .put("cluster.name",elsticsearchClusterName).build()) 
				  .addTransportAddress(new TransportAddress(InetAddress.getByName(elsticsearchHostName), 9300));
		logger.info("Elasticsearch Transport client successfully conncted to cluster");
		return transportClient;
	}
	
	@Bean("restClient")
	public RestClient restClient() {
		RestClientBuilder clientBuilder = RestClient.builder(
				new HttpHost(elsticsearchHostName, 9200, "http")
			);
		Header[] defaultHeaders = new Header[]{new BasicHeader("ContentType", "application/json")};
		clientBuilder.setDefaultHeaders(defaultHeaders);
		restClient = clientBuilder.build();
		logger.info("Elasticsearch Rest client successfully conncted to cluster");
		return restClient;
	}
	
	@Bean("elasticHighClient")
	public RestHighLevelClient restHighClient() {
		restHighClient = new RestHighLevelClient(
		        RestClient.builder(
		                new HttpHost(elsticsearchHostName, 9200, "http")));
		logger.info("Elasticsearch Rest High Level client successfully conncted to cluster");
		return restHighClient;
	}
	
	@Bean("elasticAdmin")
	public AdminClient adminClient() {
		AdminClient adminClient = transportClient.admin();
		return adminClient;
	}
	
	@PreDestroy
	public void onDestroy() throws IOException {
		if(transportClient != null) {
			transportClient.close();
			logger.info("Elasticsearch Transport client successfully closed");
		}
		
		if(restClient != null) {
			restClient.close();
			logger.info("Elasticsearch Rest client successfully closed");
		}
		
		if(restHighClient != null) {
			restHighClient.close();
			logger.info("Elasticsearch High Level Rest client successfully closed");
		}
	}
}
