package com.jinloes.polls.model;

import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by jinloes on 1/28/16.
 */
@MappedSuperclass
public class AbstractEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private final UUID id;

    public AbstractEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
