package com.jinloes.polls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by jinloes on 1/28/16.
 */
@Entity
@Table(name = "polls")
public class Poll extends AbstractEntity {

    private final String name;

    private Poll() {
        this(null, null);
    }

    @JsonCreator
    private Poll(@JsonProperty("name") String name) {
        this(null, name);
    }

    public Poll(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
