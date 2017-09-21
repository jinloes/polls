package com.jinloes.polls.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum QuestionType {
	MULTIPLE_CHOICE;

	@JsonCreator
	public static QuestionType fromString(String str) {
		return str == null ? null : valueOf(str.toUpperCase());
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
