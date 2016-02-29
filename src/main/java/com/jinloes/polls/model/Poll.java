package com.jinloes.polls.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by jinloes on 1/28/16.
 */
@Entity
@Table(name = "polls")
public class Poll extends AbstractEntity {

    private final String name;
    private final ZonedDateTime startDate;
    private final ZonedDateTime endDate;

    @OneToMany(cascade = CascadeType.ALL)
    private final List<PollChoice> choices;

    private Poll() {
        this(null, null, null, null);
    }

    @JsonCreator
    private Poll(@JsonProperty("name") String name,
                 @JsonProperty("choices") List<PollChoice> choices, @JsonProperty("start_date") ZonedDateTime startDate,
                 @JsonProperty("end_date") ZonedDateTime endDate) {
        this(null, name, choices, startDate, endDate);
    }

    public Poll(UUID id, String name, List<PollChoice> choices, ZonedDateTime startDate, ZonedDateTime endDate) {
        super(id);
        this.name = name;
        this.choices = choices;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public List<PollChoice> getChoices() {
        return choices;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }
}
