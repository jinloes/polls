package com.jinloes.polls.data.model;

import com.jinloes.polls.model.AbstractEntity;
import lombok.AllArgsConstructor;

import java.time.Instant;

public class Question extends AbstractEntity<String> {
	private final String title;
	private final QuestionType questionType;

	public Question(String id, Instant createdAt, Instant updatedAt, String title, QuestionType questionType) {
		super(id, createdAt, updatedAt);
		this.title = title;
		this.questionType = questionType;
	}
}
