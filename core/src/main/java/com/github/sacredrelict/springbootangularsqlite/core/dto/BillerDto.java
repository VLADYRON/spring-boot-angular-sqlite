package com.github.sacredrelict.springbootangularsqlite.core.dto;

import javax.validation.constraints.NotNull;

/**
 * Created by Oleg on 14.05.2017.
 */
public class BillerDto {

    private Long id;

    @NotNull
    private String name;

    public BillerDto() {
    }

    public BillerDto(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillerDto{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
