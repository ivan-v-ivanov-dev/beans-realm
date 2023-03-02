package com.personal.beans.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "beans")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Bean extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToOne
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

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @JsonIgnore
    @Column(name = "total_voters")
    private int totalVoters;

    @JsonIgnore
    @Column(name = "total_score")
    private int totalScore;

    @Transient
    private int totalDownloads;
}
