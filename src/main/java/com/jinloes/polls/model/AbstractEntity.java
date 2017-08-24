package com.jinloes.polls.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.Instant;

/**
 * Created by jinloes on 1/28/16.
 */
@Data
public class AbstractEntity<ID> {
	@Id
	private final ID id;
	private Instant createdAt;
	private Instant updatedAt;

	public AbstractEntity(ID id, Instant createdAt, Instant updatedAt) {
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
}
