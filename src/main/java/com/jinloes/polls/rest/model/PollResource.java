package com.jinloes.polls.rest.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jinloes.polls.model.PollChoice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "polls")
public class PollResource extends ResourceSupport {
	@JsonProperty("id")
	private final String pollId;
	@NotEmpty
	private final String name;
	private final Instant startDate;
	private final Instant endDate;

	private final List<PollChoice> choices;

	@JsonCreator
	public PollResource(@JsonProperty("name") String name, @JsonProperty("start_date") Instant startDate,
			@JsonProperty("end_date") Instant endDate, @JsonProperty("choices") List<PollChoice> choices) {
		this(null, name, startDate, endDate, choices);
	}
}
