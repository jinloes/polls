package com.jinloes.polls;

import com.jinloes.polls.data.model.QueryResult;
import com.jinloes.polls.model.Poll;
import com.jinloes.polls.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class PollService {
	private final PollRepository pollRepository;
	private final ReactiveMongoTemplate template;

	@Autowired
	public PollService(PollRepository pollRepository, ReactiveMongoTemplate template) {
		this.pollRepository = pollRepository;
		this.template = template;
	}

	public Mono<Poll> save(Poll poll) {
		poll.setCreatedAt(Instant.now());
		poll.setUpdatedAt(Instant.now());
		return pollRepository.save(poll);
	}

	public Mono<Poll> get(String id) {
		return pollRepository.findById(id);
	}

	public Mono<QueryResult<Poll>> get(Pageable pageable) {
		Query query = new Query()
				.with(pageable)
				.skip(pageable.getOffset())
				.limit(pageable.getPageSize());
		QueryResult.QueryResultBuilder<Poll> builder = QueryResult.<Poll>builder()
				.page(pageable.getPageNumber())
				.size(pageable.getPageSize());
		return Mono.when(template.find(query, Poll.class).collectList(),
				template.count(query, Poll.class),
				(polls, totalCount) -> builder.results(polls)
						.totalCount(totalCount)
						.build());
	}
}
