package com.jinloes.polls.rest;

import com.jinloes.polls.model.Poll;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

/**
 * Created by jinloes on 1/28/16.
 */
public interface PollRepository extends PagingAndSortingRepository<Poll, UUID> {
}
