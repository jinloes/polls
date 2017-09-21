package com.jinloes.polls.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionResource {
	private final String id;
	private final String text;
	private final String type;

	@JsonCreator
	public QuestionResource(@JsonProperty("id") String id, @JsonProperty("text") String text,
			@JsonProperty("type") String type) {
		this.id = id;
		this.text = text;
		this.type = type;
	}
}
