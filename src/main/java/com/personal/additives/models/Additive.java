package com.personal.additives.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "additives")
@NoArgsConstructor
@Getter
@Setter
public class Additive {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private byte[] image;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "additives_versions",
            joinColumns = @JoinColumn(name = "additive_id"),
            inverseJoinColumns = @JoinColumn(name = "version_id"))
    private Set<Version> version;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @OneToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @OneToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @Column(name = "description")
    private String description;
}
