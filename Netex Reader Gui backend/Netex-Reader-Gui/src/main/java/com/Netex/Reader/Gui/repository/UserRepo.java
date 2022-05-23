package com.Netex.Reader.Gui.repository;

import com.Netex.Reader.Gui.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;

public interface UserRepo extends JpaRepository<Users, Long> {
    @Query("select s from Users s where s.email like %?1% or s.firstName like %?1% or " +
            "s.lastName like %?1%")
    Page<Users> findByName(String name, Pageable pageable);

    Users findByEmail(String email);

    Users findById(long id);
}
