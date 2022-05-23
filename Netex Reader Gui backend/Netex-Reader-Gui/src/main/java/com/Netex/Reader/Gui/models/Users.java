package com.Netex.Reader.Gui.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private long phoneNumber;

    private String password;

    @ManyToMany(fetch = EAGER)
    private Set<Role> roles = new HashSet<>();

    /*@OneToMany(mappedBy = "users", fecth = EAGER)*/
    @OneToMany(fetch = EAGER, mappedBy = "users")
    List<File> files=new ArrayList<File>();

}
