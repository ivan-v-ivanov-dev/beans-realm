package com.personal.additives.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "versions")
@NoArgsConstructor
@Getter
@Setter
public class Version {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "additive_id")
    private Additive additive;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "upload_date")
    private Date uploadDate;

    @Column(name = "modifications")
    private String modifications;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "sha256_checksum")
    private String sha256Checksum;

    @Column(name = "download_count")
    private int downloadCount;
}
