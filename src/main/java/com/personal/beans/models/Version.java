package com.personal.beans.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "versions")
@NoArgsConstructor
@Getter
@Setter
public class Version extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "content")
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "bean_id")
    private Bean bean;

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
