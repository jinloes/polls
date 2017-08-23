package com.jinloes.polls.model;

import org.springframework.data.annotation.Id;

/**
 * Created by jinloes on 1/28/16.
 */
public class AbstractEntity<ID> {
	@Id
	private final ID id;

	public AbstractEntity(ID id) {
		this.id = id;
	}

	public ID getId() {
		return id;
	}
}
