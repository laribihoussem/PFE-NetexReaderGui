package com.Netex.Reader.Gui.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.AUTO;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users implements UserDetails , Serializable {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private long phoneNumber;

    private String password;

    Boolean isEnabled=true;

    @ManyToMany(fetch = EAGER)
    private Set<Role> roles = new HashSet<>();

    /*@OneToMany(mappedBy = "users", fecth = EAGER)*/
    @OneToMany(fetch = EAGER, mappedBy = "users")
    List<File> files=new ArrayList<File>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream()
                .map(role->new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
