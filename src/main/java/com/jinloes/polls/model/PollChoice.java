package com.jinloes.polls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jinloes on 1/29/16.
 */
@Entity
@Table(name = "poll_choices")
public class PollChoice extends AbstractEntity {
    private final String text;

    private PollChoice() {
        this(null, null);
    }

    @JsonCreator
    private PollChoice(@JsonProperty("text") String text) {
        this(null, text);
    }

    public PollChoice(UUID id, String text) {
        super(id);
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
