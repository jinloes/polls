package com.jinloes.polls.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Data
public class ApiError {
	private HttpStatus status;
	private Instant timestamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;


	@Builder
	public ApiError(HttpStatus status, Instant timestamp, String message, String debugMessage,
			List<ApiSubError> subErrors) {
		this.status = status;
		this.timestamp = Optional.ofNullable(timestamp).orElse(Instant.now());
		this.message = message;
		this.debugMessage = debugMessage;
		this.subErrors = subErrors;
	}
}
