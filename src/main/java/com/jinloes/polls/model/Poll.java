package com.jinloes.polls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.Instant;
import java.util.List;

/**
 * Created by jinloes on 1/28/16.
 */
@Data
public class Poll extends AbstractEntity<String> {

	@NotEmpty
	private final String name;
	private final Instant startDate;
	private final Instant endDate;

	private final List<PollChoice> choices;

	private Poll() {
		this(null, null, null, null);
	}

	@Builder
	@JsonCreator
	private Poll(@JsonProperty("name") String name,
			@JsonProperty("choices") List<PollChoice> choices, @JsonProperty("start_date") Instant startDate,
			@JsonProperty("end_date") Instant endDate) {
		this(null, name, choices, startDate, endDate);
	}

	public Poll(String id, String name, List<PollChoice> choices, Instant startDate, Instant endDate) {
		super(id);
		this.name = name;
		this.choices = choices;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}
