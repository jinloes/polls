package com.jinloes.polls.repository;

import com.jinloes.polls.model.Poll;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Mongo {@link Poll} repository.
 */
public interface PollRepository extends ReactiveMongoRepository<Poll, String> {
}
