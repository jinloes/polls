package com.jinloes.polls.rest;

import com.jinloes.polls.NotFoundException;
import com.jinloes.polls.PollService;
import com.jinloes.polls.model.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<Poll> get(@PathVariable("id") String id) {
		return pollService.get(id)
				.switchIfEmpty(Mono.error(NotFoundException.of(id)));
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Mono<PagedResources<Resource<Poll>>> getPolls(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return pollService.get(PageRequest.of(page - 1, size))
				.collectList()
				.map(polls -> PagedResources.wrap(polls, new PagedResources.PageMetadata(size, page, 100)));
	}
}
