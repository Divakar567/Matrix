package com.matrix.models;

import java.util.Date;

public class ElasticDocument {
	
	private String id;
	private String type;
	private Date createdDate;
	private Date updatedDate;

	public ElasticDocument() {
		super();
	}

	public ElasticDocument(String id, String type, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.type = type;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
