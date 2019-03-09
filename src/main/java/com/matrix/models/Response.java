package com.matrix.models;

public class Response<T> {
	String status;
	String message;
	T response;

	public Response() {
		super();
	}

	public Response(String status, String message, T response) {
		super();
		this.status = status;
		this.message = message;
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
}
