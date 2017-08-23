package com.jinloes.polls.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class ApiValidationError extends ApiSubError {
	private final String object;
	private final String field;
	private final Object rejectedValue;
	private final String message;
}
