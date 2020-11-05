package com.qualitas.mx.security.model;

public class ResponseMessage {
	private String date;
	private int status;
	private Usuarios user;
	private String token;
	private String message;
	private String detailMessage;
	private Object dataExtra;
	
	public ResponseMessage(String date, int status, Usuarios user, String token, String message, String detailMessage,
			Object dataExtra) {
		this.date = date;
		this.status = status;
		this.user = user;
		this.token = token;
		this.message = message;
		this.detailMessage = detailMessage;
		this.dataExtra = dataExtra;
	}


	public ResponseMessage() {
		super();
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public Usuarios getUser() {
		return user;
	}


	public void setUser(Usuarios user) {
		this.user = user;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDetailMessage() {
		return detailMessage;
	}


	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}


	public Object getDataExtra() {
		return dataExtra;
	}


	public void setDataExtra(Object dataExtra) {
		this.dataExtra = dataExtra;
	}
	
}
