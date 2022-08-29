package com.bridgelabz.bookstoreapp_backend.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	private String message;
	private Object data;

	public ResponseDTO() {
		super();

	}

	public ResponseDTO(String message, Object data) {
		this.message = message;
		this.data = data;
	}

}