package com.jinloes.polls.data.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryResult<T> {
	private final int page;
	private final int size;
	private final long totalCount;
	private final List<T> results;
}
