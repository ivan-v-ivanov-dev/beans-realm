package com.personal.additives.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tags")
@NoArgsConstructor
@Getter
@Setter
public class Tag {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
}
