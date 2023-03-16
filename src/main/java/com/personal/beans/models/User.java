package com.personal.beans.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "admin")
    private boolean admin;

    @Transient
    private int uploadedBeans;
}
