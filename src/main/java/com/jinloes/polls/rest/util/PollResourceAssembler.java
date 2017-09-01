package com.jinloes.polls.rest.util;

import com.jinloes.polls.model.Poll;
import com.jinloes.polls.rest.model.PollResource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class PollResourceAssembler implements ResourceAssembler<Poll, PollResource> {
	@Override
	public PollResource toResource(Poll entity) {
		return PollResource.builder()
				.pollId(entity.getId())
				.startDate(entity.getStartDate())
				.endDate(entity.getEndDate())
				.name(entity.getName())
				.choices(entity.getChoices())
				.build();
	}
}
