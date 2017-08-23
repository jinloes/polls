package com.jinloes.polls;

import com.jinloes.polls.model.Poll;
import com.jinloes.polls.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class PollService {
	private final PollRepository pollRepository;

	@Autowired
	public PollService(PollRepository pollRepository) {
		this.pollRepository = pollRepository;
	}

	public Mono<Poll> save(Poll poll) {
		return pollRepository.save(poll);
	}
}
