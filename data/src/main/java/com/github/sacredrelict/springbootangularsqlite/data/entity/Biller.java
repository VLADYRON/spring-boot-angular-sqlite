package com.github.sacredrelict.springbootangularsqlite.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Oleg on 14.05.2017.
 */
@Entity
@Table(name = "biller")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Biller implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

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
        return "Biller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
