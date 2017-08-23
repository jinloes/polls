package com.jinloes.polls.rest;

import com.jinloes.polls.PollService;
import com.jinloes.polls.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * Created by jinloes on 1/13/16.
 */
@RestController
@RequestMapping("/polls")
public class PollController {
	private final PollService pollService;

	@Autowired
	public PollController(PollService pollService) {
		this.pollService = pollService;
	}

	public String index() {
		System.out.println("test");
		return "Greetings from Spring Boot!";
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Poll> create(@Valid @RequestBody Poll poll) {
		return pollService.save(poll);
	}
}
