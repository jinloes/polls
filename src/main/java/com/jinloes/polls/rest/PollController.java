package com.jinloes.polls.rest;

import com.jinloes.polls.NotFoundException;
import com.jinloes.polls.PollService;
import com.jinloes.polls.model.Poll;
import com.jinloes.polls.rest.model.PollResource;
import com.jinloes.polls.rest.util.PollResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.PagedResources;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

/**
 * Created by jinloes on 1/13/16.
 */
@RestController
@RequestMapping("/polls")
public class PollController {
	private final PollService pollService;
	private final PollResourceAssembler resourceAssembler;

	@Autowired
	public PollController(PollService pollService, PollResourceAssembler resourceAssembler) {
		this.pollService = pollService;
		this.resourceAssembler = resourceAssembler;
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
	public Mono<PagedResources<PollResource>> getPolls(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {
		return pollService.get(PageRequest.of(page - 1, size))
				.map(queryResult -> {
					List<PollResource> resources = queryResult.getResults().stream()
							.map(resourceAssembler::toResource)
							.collect(Collectors.toList());
					return new PagedResources<>(resources, new PagedResources.PageMetadata(queryResult.getSize(),
							queryResult.getPage(), queryResult.getTotalCount()), new ArrayList<>());
				});
	}
}
