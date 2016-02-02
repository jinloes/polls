package com.jinloes.polls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Created by jinloes on 1/28/16.
 */
@Entity
@Table(name = "polls")
public class Poll extends AbstractEntity {

    private final String name;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<PollChoice> choices;

    private Poll() {
        this(null, null, null);
    }

    @JsonCreator
    private Poll(@JsonProperty("name") String name,
                 @JsonProperty("choices") List<PollChoice> choices) {
        this(null, name, choices);
    }

    public Poll(UUID id, String name, List<PollChoice> choices) {
        super(id);
        this.name = name;
        this.choices = choices;
    }

    public String getName() {
        return name;
    }

    public List<PollChoice> getChoices() {
        return choices;
    }
}
