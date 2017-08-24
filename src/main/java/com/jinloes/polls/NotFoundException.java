package com.jinloes.polls;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode(callSuper = false)
public class NotFoundException extends RuntimeException {
	private final String id;
}
