package com.jinloes.polls.rest;

import com.jinloes.polls.model.ApiError;
import com.jinloes.polls.model.ApiSubError;
import com.jinloes.polls.model.ApiValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class WebExceptionHandler {
	@ExceptionHandler(WebExchangeBindException.class)
	public Mono<ResponseEntity<ApiError>> handleException(WebExchangeBindException e) {
		List<ApiSubError> validationErrors = e.getFieldErrors().stream()
				.map(this::convert)
				.collect(Collectors.toList());
		return Mono.just(ResponseEntity.badRequest()
				.body(ApiError.builder()
						.status(HttpStatus.BAD_REQUEST)
						.message("Validation failed.")
						.debugMessage(e.getLocalizedMessage())
						.subErrors(validationErrors)
						.build()));
	}

	private ApiValidationError convert(FieldError error) {
		return ApiValidationError.builder()
				.message(error.getDefaultMessage())
				.field(error.getField())
				.object(error.getObjectName())
				.rejectedValue(error.getRejectedValue())
				.build();
	}
}
